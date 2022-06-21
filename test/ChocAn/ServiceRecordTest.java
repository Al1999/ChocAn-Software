package ChocAn;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ServiceRecordTest {

    @Test
    public void testSave() throws IOException {
        LocalDateTime time1 = LocalDateTime.now();
        LocalDateTime time2 = LocalDateTime.now();

        ServiceRecord testRecord = new ServiceRecord(time1,time2,"123456","Shock The Hunger Away",
                "654321", "Hungry, Hungry, Hippo",
                "999999999", "This guy was gross");

        BufferedWriter testBufferedWriter = new BufferedWriter(new FileWriter("test.txt"));
        testRecord.save(testBufferedWriter);
        testBufferedWriter.close();
        BufferedReader testBufferedReader = new BufferedReader(new FileReader("test.txt"));
        String testStr = testBufferedReader.readLine();
        testBufferedReader.close();
        Assert.assertEquals("ServiceRecord, " + time1 + ", " + time2 + ", 123456, Shock The Hunger Away, 654321, Hungry,/ Hungry,/ Hippo, 999999999, This guy was gross", testStr);

    }

    @Test
    public void testLoadEmpty() throws IOException {
        ServiceRecord testRecord = new ServiceRecord();
        BufferedWriter testBufferedWriter = new BufferedWriter(new FileWriter("test.txt"));
        testBufferedWriter.close();
        BufferedReader testBufferedReader = new BufferedReader(new FileReader("test.txt"));
        Assert.assertFalse(testRecord.load(testBufferedReader));
        testBufferedReader.close();
    }

    @Test
    public void load() throws IOException {
        LocalDateTime time1 = LocalDateTime.now();
        LocalDateTime time2 = LocalDateTime.now();

        ServiceRecord testRecord = new ServiceRecord(time1,time2,"123456","Shock The Hunger Away",
                "654321", "Hungry, Hungry, Hippo",
                "999999999", "This guy was gross");
        BufferedWriter testBufferedWriter = new BufferedWriter(new FileWriter("test.txt"));
        testRecord.save(testBufferedWriter);
        testBufferedWriter.close();

        BufferedReader testBufferedReader = new BufferedReader(new FileReader("test.txt"));
        ServiceRecord testRecord2 = new ServiceRecord();
        testRecord2.load(testBufferedReader);
        testBufferedReader.close();
        Assert.assertEquals(testRecord.ProviderName(), testRecord2.ProviderName());
        Assert.assertEquals(testRecord.CreationDateTime().toString(), testRecord2.CreationDateTime().toString());
        Assert.assertEquals(testRecord.ServiceDateTime().toString(), testRecord2.ServiceDateTime().toString());
        Assert.assertEquals(testRecord.Comments(), testRecord2.Comments());
        Assert.assertEquals(testRecord.MemberName(), testRecord2.MemberName());
    }

    @Test
    public void testThisWeek() {
        LocalDateTime time1 = LocalDateTime.now();
        LocalDateTime time2 = LocalDateTime.now();

        //Should return true for this week.
        time1 = time1.minusSeconds(1);
        ServiceRecord testRecord = new ServiceRecord(time1,time2,"123456","Shock The Hunger Away",
                "654321", "Hungry, Hungry, Hippo",
                "999999999", "This guy was gross");
        Assert.assertTrue(testRecord.thisWeek());


        //Should return false for this week
        time1 = time1.minusDays(8);
        testRecord = new ServiceRecord(time1,time2,"123456","Shock The Hunger Away",
                "654321", "Hungry, Hungry, Hippo",
                "999999999", "This guy was gross");
        Assert.assertFalse(testRecord.thisWeek());
    }
}