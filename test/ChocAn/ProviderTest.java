package ChocAn;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ProviderTest {

    @Test
    public void save() throws IOException {
        Provider testProvider = new Provider("564738","Eat Butter, Inc", "204 19th Ave", "Sacramento", "CA", "43618");
        BufferedWriter testBufferedWriter = new BufferedWriter(new FileWriter("test.txt"));
        testProvider.save(testBufferedWriter);
        testBufferedWriter.close();
        BufferedReader testBufferedReader = new BufferedReader(new FileReader("test.txt"));
        String testStr = testBufferedReader.readLine();
        testBufferedReader.close();
        Assert.assertEquals("Provider, 564738, Eat Butter,/ Inc, 204 19th Ave, Sacramento, CA, 43618", testStr);
    }

    @Test
    public void testLoadEmpty() throws IOException {
        Provider testProvider = new Provider();
        BufferedWriter testBufferedWriter = new BufferedWriter(new FileWriter("test.txt"));
        testBufferedWriter.close();
        BufferedReader testBufferedReader = new BufferedReader(new FileReader("test.txt"));
        Assert.assertFalse(testProvider.load(testBufferedReader));
        testBufferedReader.close();
    }

    @Test
    public void load() throws IOException {
        Provider testProvider = new Provider("564738","Eat Butter, Inc", "204 19th Ave", "Sacramento", "CA", "43618");
        BufferedWriter testBufferedWriter = new BufferedWriter(new FileWriter("test.txt"));
        testProvider.save(testBufferedWriter);
        testBufferedWriter.close();
        BufferedReader testBufferedReader = new BufferedReader(new FileReader("test.txt"));
        Provider testProvider2 = new Provider();
        testProvider2.load(testBufferedReader);
        testBufferedReader.close();
        Assert.assertEquals(testProvider.ProviderName(), testProvider2.ProviderName());
    }
}