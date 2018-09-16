package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**  
* <p>Title: SaverAccount</p>  
* <p>Description: a subclass of Accout, which requires setting a specific notice date and only allowed to withdrawal money after a date</p>  
* @author Yujie Yang 
*/  
public class SaverAccount extends Account {
	private Date noticeDate;
    private double noticeAmount;
	/**  
	* <p>Title:SaverAccount</p>  
	* <p>Description:constructor of the SaverAccount </p>  
	* @param pin account password set by customer who firstly create the account
	* @param c specific customer who owns this account
	* @param d  a minimum notice date before any withdrawal can be given
	*/  
	public SaverAccount(String pin,Customer c,Date d) {
		super(pin,c);
		this.noticeDate=d;
		this.setAccID("SaverAcc"+this.getAccID());
		// TODO Auto-generated constructor stub
	}
    public double getNoticeAmount() {
		return noticeAmount;
	}
	public void setNoticeAmount(double noticeAmount) {
		this.noticeAmount = noticeAmount;
	}
	public Date getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(Date d){
    	this.noticeDate=d;
    }
	/**  	
	 * <p>Title: checkInterval</p>  	
	 * <p>Description: check is the time allowed to withdrawal money</p>  	
	 * @return the day between current time and the notice date
	 * @throws ParseException  	
	 */  
	public long checkInterval() throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar=Calendar.getInstance();
		long nowDate=calendar.getTime().getTime();
		long specialDate=this.noticeDate.getTime();
		long Interval=(nowDate-nowDate)/1000*60*60*24;
		return Interval;
		
	}
	@Override
	public void addWithdrawal(double i) {
		// TODO Auto-generated method stub
		try{
		if(this.isIsSuspended()) {
			System.out.println("This saving account has been suspended.\n");
		}
		else{
			  if(this.checkInterval()<7)  {
				  System.out.println("The interval is too short to withdraw money,Invalid operation!\n");			 
			  }
			  else{
					double j=this.getBalance();
					if(i<=j) {
						this.setBalance(j-i);
						this.getTrans().add(new withdrawal(i,new Date()));	
						System.out.println("No."+this.getTrans().size()+" transaction record has been stored...");
					}
					else  System.out.println("No sufficient funds!\n"); 
				}				
			}
		}catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	     					  
	}
}
