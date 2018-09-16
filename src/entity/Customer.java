package entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**  
* <p>Title: Customer</p>  
* <p>Description: customer who use the system</p>  
* @author Yujie Yang 
* @date 2018Äê5ÔÂ29ÈÕ  
*/  
public class Customer implements Serializable {
   /**
	 * 
	 */
   private static final long serialVersionUID = 1L;
   private String name;
   private String address;
   private Calendar dob;            //date of birth
   private boolean creditStatus;
   ArrayList<Account> accounts=new ArrayList<Account>();
   /**  
* <p>Title:Customer</p>  
* <p>Description:constructor of the Customer, when user register the system, they need to enter their name, address and date of birth</p>  
* @param n 
* @param addr
* @param d  
*/  
public Customer(String n,String addr,Calendar d){
	   this.name=n;
	   this.address=addr;
	   this.dob=d;
	   this.setCreditStatus();
   }
    public String getName() {
	    return name;
    }
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Calendar getDob() {
		return dob;
	}
	public void setDob(Calendar dob) {
		this.dob = dob;
	}
	public boolean isCreditStatus() {
		return creditStatus;
	}
	public void setCreditStatus() {
		double random=Math.random();
		if(random<0.8) this.creditStatus = true;
		else           this.creditStatus = false;
		
	}
	public void confirmCreditStatus(){
		   
	   }
    public boolean CheckJunior(){
    	Calendar now=Calendar.getInstance(); now.setTime(new Date());
		int age=now.get(Calendar.YEAR)-this.dob.get(Calendar.YEAR);
		if(age>0&&age<=16) 	return true;
		else return false;
	}
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}
   
}
