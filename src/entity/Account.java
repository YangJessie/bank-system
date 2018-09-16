package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**  
* <p>Title: Account</p>  
* <p>Description: </p>  
* @author Yujie Yang account information includes unique account ID, password, customer who owns the account, transaction details and so on.
*/  
public abstract class Account implements Serializable{
   
	private static final long serialVersionUID = 1L;
	private String accID;
    private String PIN;
    private Customer custm;
    private ArrayList<Transaction> trans;
    private double balance;    
    private boolean IsSuspended=false;  
    private boolean noticeNeeded;
    
    /**  
    * <p>Title:Account</p>  
    * <p>Description:constructor of the Account </p>  
    * @param pin account password set by customer who firstly create the account
    * @param c  specific customer who owns this account
    */  
    public Account(String pin,Customer c){
    	this.accID=UUID.randomUUID().toString().replace("-", "");
    	this.setCustm(c);
    	this.PIN=pin;
    	this.balance=0.0; 
    	this.trans=new ArrayList<Transaction>();
    }
    /**  
    
     * <p>Title: calculateDep</p>  
    
     * <p>Description: calculate the amount of money which has been cleared in the deposit process.</p>  
    
     * @return the refreshed balance which has added with newly deposit
    
     */  
    public void clearDep(double d){
    	double b=this.getBalance();
       	this.setBalance(b+d);
     }
    public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getAccID() {
		return accID;
	}
	public void setAccID(String accID) {
		this.accID = accID;
	}
	public String getPIN() {
		return PIN;
	}
	public void setPIN(String pIN) {
		PIN = pIN;
	}
	public boolean isIsSuspended() {
		return IsSuspended;
	}
	public void setIsSuspended(boolean isSuspended) {
		IsSuspended = isSuspended;
	}
	public boolean isNoticeNeeded() {
		return noticeNeeded;
	}
	public void setNoticeNeeded(boolean noticeNeeded) {
		this.noticeNeeded = noticeNeeded;
	}
	
	/**  
	
	 * <p>Title: addDeposit</p>  
	
	 * <p>Description: add new deposit record into the Arraylist of Transaction</p>  
	
	 * @param i the amount of money which is going to become deposit
	 * @param day the date of the day created the deposit record
	 * @param cash  refers to consumer's choice of whether using cash to pay the deposit
	
	 */  
	public void addDeposit(double i,Date day,int cash){
		if(this.isIsSuspended()) {
			System.out.println("This saving account has been suspended.\n");
		}
		else{
			Deposit d=new Deposit(i,day);
			d.setCleared(cash);
	    	this.getTrans().add(d); 
	    	if(d.isCleared()) this.clearDep(i);
	    	else {
	    		System.out.println("Waiting for the fund to be cleared.");
	    		this.setNoticeNeeded(true);
	    	}
	    	System.out.println("No."+this.getTrans().size()+" transaction record has been stored...");
		}
		
    }
    public void addBalance(double i){
    	this.balance+=i;
    }
    /**  
    
     * <p>Title: addWithdrawal</p>  
    
     * <p>Description: Add withdrawal record into this account only when </p>  
    
     * @param i  the amount of money customer want to withdrawal
    
     */  
    public abstract void addWithdrawal(double i);  	

    public void setSuspended(){
    	this.IsSuspended=true;
    }
    
    public void close(){    	
    }
	public Customer getCustm() {
		return custm;
	}
	public void setCustm(Customer custm) {
		this.custm = custm;
	}
	public ArrayList<Transaction> getTrans() {
		return trans;
	}
	public void setTrans(ArrayList<Transaction> trans) {
		this.trans = trans;
	}
}
