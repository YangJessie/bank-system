package JUnitTest;

import static org.junit.Assert.*;
import java.util.GregorianCalendar;
import org.junit.Test;

import entity.Customer;

public class CustomerTest {

	@Test
	public void testCustomer() {
		Customer c=new Customer("Jessie", "Xituchen Rd", new GregorianCalendar(1983,1,28));
		assertEquals("Jessie",c.getName());
		assertEquals("Xituchen Rd",c.getAddress());
		assertEquals(new GregorianCalendar(1983,1,28),c.getDob());
		
	}

	@Test
	public void testGetAccounts() {
		
	}

	@Test
	public void testSetAccounts() {
	}

}
