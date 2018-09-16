package entity;

import java.io.Serializable;
import java.util.Date;
 /**  
* <p>Title: Transaction</p>  
* <p>Description: information of transaction(withdrawal and deposit money) </p>  
* @author yujie Yang  
*/  
public abstract class Transaction implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String TransID;
	private double amount;
    private Date BeginDate;
    private Date EndDate;
    
    /**  
    * <p>Title:Transaction </p>  
    * <p>Description: constructor of abstract class-transaction  </p>  
    * @param a amount of money to withdrawal/deposit
    * @param d the date of the day the transaction created
    */  
    public Transaction(double a,Date d){
    	this.amount=a;
    	this.BeginDate=d;
    }
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getBeginDate() {
		return BeginDate;
	}
	public void setBeginDate(Date beginDate) {
		BeginDate = beginDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	public String getTransID() {
		return TransID;
	}
	public void setTransID(String transID) {
		TransID = transID;
	}
	public void CreatRandomID(){}
	public boolean isCleared() {
		// TODO Auto-generated method stub
		return true;
	}
	/**  
	 * <p>Title: setcleared</p>  s
	 * <p>Description: </p>  
	 * @param i  
	 */
	/**  
	 * <p>Title: setCleared</p>  
	 * <p>Description: </p>  
	 * @param i  
	 */  
	public abstract void setCleared(int i);
		// TODO Auto-generated method stub
		

	
		
	
}
