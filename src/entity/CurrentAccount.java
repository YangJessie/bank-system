package entity;

import java.util.Date;

/**  
* <p>Title: CurrentAccount</p>  
* <p>Description: a subclass of Accout, which is allowed to withdrawal money more than balance but must not exceed overdraft limit</p>  
* @author Yujie Yang 
*/  
public class CurrentAccount extends Account {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double overdraftLimit;
	/**  
	* <p>Title:CurrentAccount</p>  
	* <p>Description:constructor of the CurrentAccount </p>  
	* @param pin account password set by customer who firstly create the account
	* @param c specific customer who create the account 
	* @param o  the overdraft limit
	*/  
	public CurrentAccount(String pin,Customer c,double o) {
		// TODO Auto-generated constructor stub
		super(pin,c);
        this.setAccID("CurrentAcc"+this.getAccID());
        this.overdraftLimit=o;
	}
	
	@Override
	public void addWithdrawal(double i) {
		// TODO Auto-generated method stub
		if(this.isIsSuspended()) {
			System.out.println("This saving account has been suspended.\n");
		}
		else {
			double j=this.getBalance();
			if(i<=(j+this.overdraftLimit)){
				this.setBalance(j-i);
	     		this.getTrans().add(new withdrawal(i,new Date()));
	     		System.out.println("No."+this.getTrans().size()+" transaction record has been stored...");
			}
		}
		
	}
}
