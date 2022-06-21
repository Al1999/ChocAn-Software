package ChocAn;

import ChocAn.ChocAnList;
import ChocAn.Member;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.time.LocalDateTime;

import static org.junit.Assert.*;


public class ChocAnProviderTerminalTest {

    @Test
    public void verifyValidMember(){
        ChocAnProviderTerminal test = new ChocAnProviderTerminal() ;
        String member = "333222111" ;
        Member tester = test.validateMember(member);
        boolean match = tester.verify();
        Assert.assertEquals(true , match ) ;
    }

    @Test
    public void verifyMemberWithFees()
    {
        ChocAnProviderTerminal test = new ChocAnProviderTerminal() ;
        String member = "123412345" ;
        Member tester = test.validateMember(member);

        Assert.assertEquals( null  , tester ) ;
    }

    @Test
    public void verifyNonexistingMember()
    {
        ChocAnProviderTerminal test = new ChocAnProviderTerminal() ;
        String member = "678543211" ;
        Member tester = test.validateMember(member);

        Assert.assertEquals( null  , tester ) ;
    }


}
