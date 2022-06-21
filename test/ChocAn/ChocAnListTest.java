package ChocAn;

import ChocAn.ChocAnList;
import ChocAn.Member;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ChocAnListTest {

    @Test
    public void testSave() throws IOException {

        //Test
        ChocAnList testList = new ChocAnList();
        BufferedWriter testBufferedWriter = new BufferedWriter(new FileWriter("test.txt"));
        Assert.assertEquals(true, testList.save(testBufferedWriter));
        testBufferedWriter.close();
    }


    @Test
    public void testLoad() throws IOException {

        Member testMember = new Member("012345","Doe, John", "420 SW 69th St", "Nice", "FL", "19283", 0);
        BufferedWriter testBufferedWriter = new BufferedWriter(new FileWriter("test.txt"));
        testMember.save(testBufferedWriter);
        testMember.save(testBufferedWriter);
        testMember.save(testBufferedWriter);
        testMember.save(testBufferedWriter);
        testBufferedWriter.close();

        ChocAnList testList = new ChocAnList();
        BufferedReader testBufferedReader = new BufferedReader(new FileReader("test.txt"));
        Assert.assertEquals(true, testList.load(testBufferedReader));
        testBufferedReader.close();
    }

    @Test
    public void testVarious() throws IOException {
        ChocAnList testList = new ChocAnList();

        LocalDateTime time1 = LocalDateTime.now();
        LocalDateTime time2 = LocalDateTime.now();
        LocalDateTime time3 = LocalDateTime.now();

        time3 = time3.minusDays(8);

        //Fake records to add to list
        Service testService = new Service("598470", "Forced Vegetable Feeding", 5000);
        Member testMember = new Member("012345","Doe, John", "420 SW 69th St", "Nice", "FL", "19283", 0);
        Provider testProvider = new Provider("564738","Eat Butter, Inc", "204 19th Ave", "Sacramento", "CA", "43618");
        ServiceRecord testRecord = new ServiceRecord(time1,time2,"123456","Shock The Hunger Away",
                "654321", "Hungry, Hungry, Hippo",
                "999999999", "This guy was gross");
        ServiceRecord testRecord2 = new ServiceRecord(time3,time2,"456123","Jabba's CoCo Kingdom",
                "442244", "Luke Skywalker",
                "999999999", "Yeah, just give up on that diet... DO IT");


        //Add records to list
        testList.add(testService);
        testList.add(testRecord);
        testList.add(testRecord2);
        testList.add(testMember);
        testList.add(testProvider);

        //Checking match functions:
        Assert.assertEquals(testList.match("598470"), testService);
        Assert.assertEquals(testList.match("012345"), testMember);
        Assert.assertEquals(testList.match("564738"), testProvider);
        Assert.assertEquals(testList.match("123456"), testRecord);
        Assert.assertEquals(testList.match("456123"), testRecord2);

        //Testing match with thisWeek as well
        Assert.assertTrue(testList.match("123456").thisWeek());
        Assert.assertFalse(testList.match("456123").thisWeek());

        //Attempting to save.
        BufferedWriter testBufferedWriter = new BufferedWriter(new FileWriter("test.txt"));
        Assert.assertTrue(testList.save(testBufferedWriter));
        testBufferedWriter.close();

        //Attempting to load into new list.
        ChocAnList loadedList = new ChocAnList();
        BufferedReader testBufferedReader = new BufferedReader(new FileReader("test.txt"));
        Assert.assertTrue(loadedList.load(testBufferedReader));

        //Checking to see if loaded data contains items matching the ones I loaded in.
        Assert.assertNotNull(loadedList.match("598470"));
        Assert.assertNotNull(loadedList.match("012345"));
        Assert.assertNotNull(loadedList.match("564738"));
        Assert.assertNotNull(loadedList.match("123456"));
        Assert.assertNotNull(loadedList.match("456123"));

        //Check to see if the dates match the loaded ones
        Assert.assertTrue(loadedList.match("123456").thisWeek());
        Assert.assertFalse(loadedList.match("456123").thisWeek());

    }
}