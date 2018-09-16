package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**  
* <p>Title: withdrawal</p>  
* <p>Description: a subclass of transaction</p>  
* @author Yujie Yang 
*/  
public class withdrawal extends Transaction implements Serializable {

	public withdrawal(double a, Date d) {
		super(a, d);
		this.CreatRandomID();
		// TODO Auto-generated constructor stub
	}
	public void CreatRandomID(){		
		this.setTransID("Withdrawal".concat(UUID.randomUUID().toString().replace("-", "")));
	}
	/* (non-Javadoc)  
	
	 * <p>Title: setCleared</p>  
	
	 * <p>Description: </p>  
	
	 * @param i  
	
	 * @see entity.Transaction#setCleared(int)  
	
	 */
	@Override
	public void setCleared(int i) {
		// TODO Auto-generated method stub
		
	}
	
}
