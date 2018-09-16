package JUnitTest;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import entity.CurrentAccount;
import entity.Customer;
import entity.JuniorAccount;
import entity.SaverAccount;

public class AccountTest {
    private Customer c;
    private SaverAccount Sacc;
    private JuniorAccount Jacc;
    private CurrentAccount Cacc;
	@Before
	public void setUp() throws Exception {
		Date noticeDay = new SimpleDateFormat("yyyy-MM-dd").parse("2017-05-20");
		c=new Customer("Jessie", "Xituchen Rd", new GregorianCalendar(1983,1,28));
	    Sacc=new SaverAccount("softeng100",c,noticeDay);
	    Cacc=new CurrentAccount("InterApp100",c,100.0);
	    if(c.CheckJunior()) Jacc=new JuniorAccount("Law100",c);   	    
	}

	@Test
	public void test() {
		assertEquals("softeng100",Sacc.getPIN());
		assertEquals(c,Cacc.getCustm());
		assertFalse(Sacc.isIsSuspended());
		assertNotNull(Cacc.getPIN());
		
	}

}
