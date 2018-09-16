package entity;

import java.util.Calendar;
import java.util.Date;

/**  
* <p>Title: JuniorAccount</p>  
* <p>Description: a subclass of Account, only customer under age 16 can create this kind of account </p>  
* @author Yujie Yang 
*/  
public class JuniorAccount extends Account {
	
	/**  
	* <p>Title:JuniorAccount</p>  
	* <p>Description:constructor of JuniorAccount </p>  
	* @param pin password of this account
	* @param c customer who owns the account 
	*/  
	public JuniorAccount(String pin,Customer c) {
		super(pin,c);
		this.setAccID("JuniorAcc"+this.getAccID());
		// TODO Auto-generated constructor stub
	}
	@Override
	public void addWithdrawal(double i) {
		// TODO Auto-generated method stub
		if(this.isIsSuspended()) {
			System.out.println("This saving account has been suspended.\n");
		}
		else {
			double j=this.getBalance();
			if(i<=j) {
				this.setBalance(j-i);
				this.getTrans().add(new withdrawal(i,new Date()));	
			}
			else System.out.println("No sufficient funds!\n"); 
		}
		
	}

}
