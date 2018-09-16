package GUI;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import dao.accountDao;
import dao.customerDao;
import entity.Account;
import entity.CurrentAccount;
import entity.Customer;
import entity.Deposit;
import entity.JuniorAccount;
import entity.SaverAccount;
import entity.Transaction;

/**  
* <p>Title: BankGUI</p>  
* <p>Description: The most important GUI class </p>  
* @author Yujie Yang 
*/  
public class BankGUI {
	ArrayList<Account> accountsBuf=new ArrayList<Account>();   //only store arraylist into file once every time open the system
	Scanner scanner=new Scanner(System.in);
	/**  
	 * <p>Title: homepage </p>  
	 * <p>Description: The homepage of the system, in main method it will present in endless loop.</p>  
	 * @return  
	 */  
	public int homepage(){
		
		System.out.println("-------------------------Bank System----------------------------\n\n");      
	    System.out.println("                     [1]  REGISTER                              \n");
	    System.out.println("                     [2]   LOGIN                                 \n");
	    System.out.println("                     [3] CHECK ACCOUNT                          \n");
	    System.out.println("                     [4] CREAT ACCOUNT                          \n");
	    System.out.println("                  Please enter your choice                      \n");
	    System.out.println("                 Press other number to exit.                    \n");
	    
	    int choose=this.scanner.nextInt();   
	    return choose;
	}
	/**  
	 * <p>Title: register</p>  
	 * <p>Description: User enters name, address, date of birth to register </p>  
	 * @throws ParseException
	 * @throws IOException  
	 */  
	public void register() throws ParseException, IOException{
	   //Scanner scanner=new Scanner(System.in);
	   System.out.println("---------------------------REGISTER------------------------------\n");
 	   System.out.println("Please enter your name:  ");
 	   String name=this.scanner.next(); 	   
 	   System.out.println("Please enter your address: ");
 	   String addr=this.scanner.next();
 	   String blank=this.scanner.nextLine();
 	   System.out.println("Please enter your birthday(in yyyy-mm-dd): ");
 	   String birthday=this.scanner.next();
 	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
 	   Calendar birth=Calendar.getInstance();
 	   birth.setTime(sdf.parse(birthday));
 	   //scanner.close();
 	   Customer bufc=new Customer(name,addr,birth);
 	   if(bufc.isCreditStatus())  customerDao.saveData(bufc);
 	   else          System.out.println("Cannot register due to poor credit status.  ");            
	}
	/**  
	 * <p>Title: Login</p>  
	 * <p>Description: After user registers, enter user's name to login the system. </p>  
	 * @return customer object whose name is as same as you entered
	 * @throws ClassNotFoundException
	 * @throws IOException  
	 */  
	public Customer Login() throws ClassNotFoundException, IOException{
		//Scanner scanner=new Scanner(System.in);
		System.out.println("---------------------------LOGIN------------------------------\n");
		System.out.println("Please enter user name: ");
		String name=this.scanner.next();
		Customer c=customerDao.readData(name);	
		//scanner.close();
		return c;
					
	}
	/**  
	 * <p>Title: AddAccount</p>  
	 * <p>Description: User create account from 3 choices:current,saver,junior account and add this account into the arraylist in customer object  </p>  
	 * @param c customer object which is going to create account
	 * @throws IOException  
	 */  
	public void AddAccount(Customer c) throws IOException{
		//Scanner scanner=new Scanner(System.in);
		System.out.println("---------------------------CREAT ACCOUNT------------------------------\n");
		System.out.println("                         [1]Current Account                           \n");
		System.out.println("                          [2]Saver Account                            \n");
		System.out.println("                   [3]Junior Account(Only under 16)                   \n\n");
		System.out.println("*Please enter your choice \n");
		int choose=this.scanner.nextInt();
		System.out.println("*Please set account password\n");
		String pin=this.scanner.next();
		switch(choose){
		case 1:	
			System.out.println("*Please set an overdraft limit:");	
			double over=this.scanner.nextDouble();	
			c.getAccounts().add(new CurrentAccount(pin,c,over));
			break;
		case 2:	
			Date date=null;
			System.out.println("*Please set a notice day:");		
			String str = scanner.next() ;
			try{
				date = new SimpleDateFormat("yyyy-MM-dd").parse(str) ;
			}catch(Exception e){
				e.printStackTrace();
			}			
			c.getAccounts().add(new SaverAccount(pin,c,date));
			break;
		case 3:
			if(c.CheckJunior()) c.getAccounts().add(new JuniorAccount(pin,c));
			else   System.out.println("Sorry, Invalid operation. Junior account only provides for customer under 16! \n");   
			break;
		
		}
	    customerDao.saveData(c);
	}
	/**  
	 * <p>Title: checkAccList</p>  
	 * <p>Description: List all of a user's account </p>  
	 * @param c customer who is going to check account list
	 * @return the number of account user want to check in detail
	 * @throws ClassNotFoundException
	 * @throws IOException  
	 */  
	public int checkAccList(Customer c) throws ClassNotFoundException, IOException{
		ArrayList<Account> accBufList=c.getAccounts();
		if(accBufList==null) {
			System.out.println("You have no accounts, please ensure log in and creat accounts successfully first.\n");
			return 0;
		}
		System.out.println("               Customer "+c.getName()+"  Accounts List                ");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("No.|Account ID                                | Account Balance");
		int i,j;
		for(i=0;i<accBufList.size();i++) {
			Account accBuf=accBufList.get(i);
			 System.out.println((i+1)+"   "+accBuf.getAccID()+"  "+accBuf.getBalance());
		}		
		System.out.println("-----------------------------------------------------------------\n");
		System.out.println("        Choose one to check in detail. Press 0 to exit.           \n");
		i=this.scanner.nextInt();
		if (i==0) ;
		else{
			for(j=0;j<3;j++){
				System.out.println("          Please enter your password. Press 0 to exit.             \n");
				String pin=this.scanner.next();
				if(pin.equals(accBufList.get(i-1).getPIN())){
					System.out.println("Correct Password!                    \n");
					break;
				}
				else  {
					System.out.println(" InCorrect Password!You have "+(3-j)+" chances to enter correct password.");
					i=0;
				}
		    }	
	    }
		return i;
	}
	/**  
	 * <p>Title: check1Acc</p>  
	 * <p>Description: present detail of one specific account </p>  
	 * @param i the number of account the user is going to check
	 * @param c customer who is going to check the account 
	 * @throws IOException  
	 */  
	public void check1Acc(int i, Customer c) throws IOException{		
		if(i==0) return ;
		else{	
			//Account changingAcc=c.getAccounts().get(i-1);
			while(true){
				Account changingAcc=c.getAccounts().get(i-1);
				System.out.println("-----------------------------------------------------------------\n");
				System.out.println("Account ID: "+ changingAcc.getAccID());
				System.out.println("Customer: "+ changingAcc.getCustm().getName());
				System.out.println("Balance: "+ changingAcc.getBalance());
				System.out.println("suspended status: "+ changingAcc.isIsSuspended());
				System.out.println("Need notice: "+ changingAcc.isNoticeNeeded());
				System.out.println("-----------------------------------------------------------------\n");
				System.out.println("                        [1] Deposit money                        \n");
				System.out.println("                       [2] Withdrawal money                      \n");
				System.out.println("                      [3] Transaction Detail                     \n");	
				System.out.println("                    [4] Suspend/Activate Account                 \n");	
				System.out.println("                        [5] Close Account                        \n");	
				System.out.println("             Please enter your choice. Press 0 to exit.          \n");
				int k=this.scanner.nextInt();  
			switch(k){
				case 1:
					changingAcc=this.depositMoney(c.getAccounts().get(i-1));
					customerDao.saveData(c);		
					break;
				case 2:
					changingAcc=this.withdrawalMoney(c.getAccounts().get(i-1));
					customerDao.saveData(c);				
					break;
				case 3:
					changingAcc=this.checkTransList(c.getAccounts().get(i-1));
					customerDao.saveData(c);
					break;
				case 4:
					System.out.println("Press 0 to retrack your request.");
					if(this.scanner.nextInt()!=0){
						if(changingAcc.isIsSuspended()==true){
							changingAcc.setIsSuspended(false);	
							System.out.println("Activating account...");
						}
						else {
							changingAcc.setIsSuspended(true);
							System.out.println("Suspending account...");
						}
						customerDao.saveData(c);
					}
					else System.out.println("Invalid operation...");
					break;				
				case 5: 
					if(c.getAccounts().get(i-1).getBalance()>=0) {
						System.out.println("Press 0 to retrack your request to close account.");
						if(this.scanner.nextInt()!=0){
							c.getAccounts().remove(i-1);
							System.out.println("Closing account...");
							customerDao.saveData(c);
							return ;
						}					
					}
					else System.out.println("Unsuccessful closing account...");
					break;										
			    default:			    	
			    	customerDao.saveData(c);
			    	return ;
			 }	
			}
		}	
	}
	/**  
	 * <p>Title: depositMoney</p>  
	 * <p>Description: Deposit money from account after entering amount of money and way of payment </p>  
	 * @param a account which is going to deposit money
	 * @return  account which has stored the deposit record
	 */  
	public Account depositMoney(Account a) {
		// TODO Auto-generated method stub
		if(a.isIsSuspended()) System.out.println("This saving account has been suspended.\n");
		else{
			System.out.println("-----------------------------------------------------------------\n");
			System.out.println("Please enter the amount you want to deposit:");
			double DepAmount=this.scanner.nextDouble();		
			System.out.println("Pay in [1] cash or by [2] cheque? ");
			int clean = this.scanner.nextInt();
			if(clean==1||clean==2) {
				a.addDeposit(DepAmount,new Date(),clean);
			}
			else                   System.out.println("Failed transaction.");	
		}
		
		return a;
	}
	/**  
	 * <p>Title: withdrawalMoney</p>  
	 * <p>Description: Withdrawal money by entering the amount of money</p>  
	 * @param a account which is going to withdrawal money 
	 * @return  account which has stored the withdrawal record
	 */  
	public Account withdrawalMoney(Account a){
		if(a.isIsSuspended()) System.out.println("This saving account has been suspended.\n");
		else{
			System.out.println("-----------------------------------------------------------------\n");
			System.out.println("Please enter the amount you want to withdrawal:");
			double DepAmount=this.scanner.nextDouble();	
			a.addWithdrawal(DepAmount);
		}
		return a;
	}
	/**  
	 * <p>Title: checkTransList</p>  
	 * <p>Description: List all the transaction records in detail and clear funds</p>  
	 * @param a account which is going to check transaction records
	 * @return  account which has been refreshed
	 */  
	public Account checkTransList(Account a){
		if(a.getTrans().size()<=0) {
			System.out.println("Sorry, you have no transaction records now! ");				
		}
		else{
			System.out.println("Tansaction list: ");
			for(int j=0;j<a.getTrans().size();j++){
				 Transaction tranBuf=a.getTrans().get(j);
				System.out.println("-----------------------------------------------------------------\n");
				System.out.println("No."+(j+1));
				System.out.println("Transaction ID: "+tranBuf.getTransID());
				System.out.println("Transaction amount: "+tranBuf.getAmount());
				System.out.println("Transcation Begin Date: "+tranBuf.getBeginDate());
				System.out.println("Transcation End Date: "+tranBuf.getEndDate());
				if(tranBuf.getTransID().startsWith("D")){
					System.out.println("Is cleared? "+tranBuf.isCleared());
					if(tranBuf.isCleared());
					else  {
						System.out.println("Press 9 to clear the fund now, press 1 to check other transaction.");
						switch(this.scanner.nextInt()){
						    case 1:
						    	break;
						    case 9:
						    	a.clearDep(tranBuf.getAmount());
						    	a.getTrans().get(j).setCleared(1);
						    	a.setNoticeNeeded(false);
						    	break;
						}
					}
				}			
			}
		return a;	
		}
		return a;
	}
	public static void main(String[] args) throws ParseException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub	  
	   BankGUI ui=new BankGUI();
	   Customer buf=new Customer(null,null,null);
	   for(;;)
       switch(ui.homepage()){
         case 1:
    	    ui.register();break;
         case 2:
            buf=ui.Login();break;
         case 3: 
        	 if(buf.getName()==null) System.out.println("Please log in or register first.\n");
        	 else {
        		 int i = ui.checkAccList(buf);
        		 ui.check1Acc(i, buf);
        	 }
        	 break;
         case 4:
        	 if(buf.getName()==null) System.out.println("Please log in or register first.\n");
        	 else                    ui.AddAccount(buf);  
        	 break;
         default:
        	 System.out.println("----------------The system is turning off. Ciao!------------------\n ");
        	 System.exit(0);
       }         
	}
}
