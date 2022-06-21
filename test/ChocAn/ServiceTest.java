package ChocAn;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ServiceTest{

    @Test
    public void testSave() throws IOException {
        Service testService = new Service("598470", "Forced Vegetable Feeding", 5000);
        BufferedWriter testBufferedWriter = new BufferedWriter(new FileWriter("test.txt"));
        testService.save(testBufferedWriter);
        testBufferedWriter.close();
        BufferedReader testBufferedReader = new BufferedReader(new FileReader("test.txt"));
        String testStr = testBufferedReader.readLine();
        testBufferedReader.close();
        Assert.assertEquals("Service, 598470, Forced Vegetable Feeding, 5000", testStr);
    }

    @Test
    public void testLoadEmpty() throws IOException {
        Service testService = new Service();
        BufferedWriter testBufferedWriter = new BufferedWriter(new FileWriter("test.txt"));
        testBufferedWriter.close();
        BufferedReader testBufferedReader = new BufferedReader(new FileReader("test.txt"));
        Assert.assertFalse(testService.load(testBufferedReader));
        testBufferedReader.close();
    }

    @Test
    public void testLoad() throws IOException {
        Service testService = new Service("598470", "Forced Vegetable Feeding", 5000);
        BufferedWriter testBufferedWriter = new BufferedWriter(new FileWriter("test.txt"));
        testService.save(testBufferedWriter);
        testBufferedWriter.close();
        BufferedReader testBufferedReader = new BufferedReader(new FileReader("test.txt"));
        Service testService2 = new Service();
        testService2.load(testBufferedReader);
        testBufferedReader.close();
        Assert.assertEquals(testService.Name(), testService2.Name());
        Assert.assertEquals(testService.Code(), testService2.Code());
        Assert.assertEquals(testService.Price(), testService2.Price());
    }

}