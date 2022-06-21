package ChocAn;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class MemberTest {

    @Test
    public void testSave() throws IOException {
        Member testMember = new Member("012345","Doe, John", "420 SW 69th St", "Nice", "FL", "19283", 0);
        BufferedWriter testBufferedWriter = new BufferedWriter(new FileWriter("test.txt"));
        testMember.save(testBufferedWriter);
        testBufferedWriter.close();
        BufferedReader testBufferedReader = new BufferedReader(new FileReader("test.txt"));
        String testStr = testBufferedReader.readLine();
        testBufferedReader.close();
        Assert.assertEquals("Member, 012345, Doe,/ John, 420 SW 69th St, Nice, FL, 19283, 0", testStr);
    }

    @Test
    public void testLoadEmpty() throws IOException {
        Member testMember = new Member();
        BufferedWriter testBufferedWriter = new BufferedWriter(new FileWriter("test.txt"));
        testBufferedWriter.close();
        BufferedReader testBufferedReader = new BufferedReader(new FileReader("test.txt"));
        Assert.assertFalse(testMember.load(testBufferedReader));
        testBufferedReader.close();
    }

    @Test
    public void testLoad() throws IOException {
        Member testMember = new Member("012345","Doe, John", "420 SW 69th St", "Nice", "FL", "19283", 0);
        BufferedWriter testBufferedWriter = new BufferedWriter(new FileWriter("test.txt"));
        testMember.save(testBufferedWriter);
        testBufferedWriter.close();
        BufferedReader testBufferedReader = new BufferedReader(new FileReader("test.txt"));
        Member testMember2 = new Member();
        testMember2.load(testBufferedReader);
        testBufferedReader.close();
        Assert.assertEquals(testMember.MemberName(), testMember2.MemberName());
    }
}