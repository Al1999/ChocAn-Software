package ChocAn;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import static java.lang.System.in;
import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void getNumberTest() {
        Utils tester = new Utils();
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("123\n123456g21\n098765432\n".getBytes());
        System.setIn(in);
        String test = null;
        try {
            test = tester.getNumber();
        } catch (NoSuchElementException e) {
        }
        System.setIn(sysInBackup);
        Assert.assertEquals("098765432", test);
    }

    @Test
    public void getZipTest() {
        Utils tester = new Utils();
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("123\n123456g21\n09876\n".getBytes());
        System.setIn(in);
        String test = null;
        try {
            test = tester.getZip();
        } catch (NoSuchElementException e) {
        }
        System.setIn(sysInBackup);
        Assert.assertEquals("09876", test);
    }

    @Test
    public void getIntTest() {
        Utils tester = new Utils();
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("4".getBytes());
        System.setIn(in);
        int test = 0;
        try {
            test = tester.getInt();
        } catch (InputMismatchException e) {
            System.setIn(sysInBackup);
        }

        Assert.assertEquals(4, test);
    }

    @Test
    public void getMenuChoiceTest() {
        Utils tester = new Utils();
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("4".getBytes());
        System.setIn(in);
        int test = 0;
        try {
            test = tester.getMenuChoice(1, 5);
        } catch (InputMismatchException e) {
            System.setIn(sysInBackup);
        }

        Assert.assertEquals(4, test);


    }
}


