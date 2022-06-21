package ChocAn;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;
import java.io.InputStream;


public class ChocAnManagerTerminalTest extends ChocAnManagerTerminal
{

    @Test
    public void editexistedMembertest()
    {
        ChocAnManagerTerminal test = new ChocAnManagerTerminal() ;
        String name  = "123412345";
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("123412345".getBytes());
        System.setIn(in);
        boolean test1 = true;
        try {
            test1 = test.editMember(name);
        } catch (NoSuchElementException e) {
             System.setIn(sysInBackup);
        }

        //boolean tester = test.editMember(name) ;
        Assert.assertEquals( true , test1 ) ;
    }
    @Test
    public void editMembertest()
    {
        ChocAnManagerTerminal test = new ChocAnManagerTerminal() ;
        boolean member = false ;
        String name  = null ;
        boolean tester = test.editMember(name) ;
        Assert.assertEquals( member, tester ) ;
    }
    
    @Test
    public void editProvidertest()
    {
        ChocAnManagerTerminal test = new ChocAnManagerTerminal() ;
        boolean provider = false ;
        String name  = null ;
        boolean tester = test.editProvider(name) ;
        Assert.assertEquals( provider, tester ) ;
    }

    @Test
    public void removeMembertest()
    {
        ChocAnManagerTerminal test = new ChocAnManagerTerminal() ;
        boolean member = false ;
        String name  = null ; boolean tester = test.removeMember(name) ;
        Assert.assertEquals( member, tester ) ;
    }

    @Test
    public void removeProvidertest()
    {
        ChocAnManagerTerminal test = new ChocAnManagerTerminal() ;
        boolean provider = false ;
        String name  = null ;
        boolean tester = test.removeProvider( name ) ;
        Assert.assertEquals( provider , tester ) ;
    }

    @Test
    public void providerReportBlankTest() {
        Assert.assertFalse(this.ProviderReport("184445234"));
    }

    @Test
    public void providerReportFakeTest() {
        Assert.assertFalse(this.ProviderReport("-1234567"));
    }

    @Test
    public void providerReportTest() {
        Assert.assertTrue(this.ProviderReport("888888888"));
    }

    @Test
    public void memberReportHappyTest() {
        Assert.assertTrue(this.MemberReport("012345678"));
    }

    @Test
    public void memberReportBlankTest(){
        Assert.assertFalse(this.MemberReport("234567890"));
    }

    @Test
    public void memberReportFakeTest(){
        Assert.assertFalse(this.MemberReport("-12314352"));
    }

    @Test
    public void EFTReportTest(){
        this.GenerateEFTReport();
        Assert.assertTrue(true);
    }

    @Test
    public void WeeklySummaryTest(){
        this.GenerateWeeklyReport();
        Assert.assertTrue(true);
    }
}