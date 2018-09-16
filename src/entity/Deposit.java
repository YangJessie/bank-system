package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
/**  
* <p>Title: Deposit</p>  
* <p>Description:a subclass of transaction </p>  
* @author Yujie Yang  
*/  
public class Deposit extends Transaction  implements Serializable{
	private boolean cleared;
	public Deposit(double a, Date d) {
		super(a, d);
		this.CreatRandomID();
		// TODO Auto-generated constructor stub
	}
	public boolean isCleared() {
		return cleared;
	}
	
	public void CreatRandomID(){		
		this.setTransID("Deposit".concat(UUID.randomUUID().toString().replace("-", "")));
	}
	/**  
	 * <p>Title: setCleared</p>  
	 * <p>Description: if customer choose to pay the deposit in cash, the money will be cleared in the account, but if he choose cheque, the money will not be cleared.</p>  
	 * @param i  customer's choice between cash/cheque
	 */  
	public void setCleared(int i) {
		// TODO Auto-generated method stub
		if(i==1)       this.cleared = true;
		else if(i==2)  this.cleared= false;
	}
}
