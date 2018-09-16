package dao;

import java.io.EOFException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import entity.Account;
import entity.Customer;
/**  
* <p>Title: accountDao</p>  
* <p>Description: methods of read/write account information</p>  
* @author Yujie Yang 
*/  
public class accountDao {	
	/**  
	
	 * <p>Title: saveData</p>  
	
	 * <p>Description: store account information to the customer's own directory </p>  
	
	 * @param c specific customer who creates the account
	 * @param acc the account object created by specific customer
	 * @throws IOException  
	
	 */  
	public static void saveData(Customer c,Account acc) throws IOException{
		String name=c.getName();
		File accfile=new File(constant.CUSTOMER_DATA_FILE_PATH + name, constant.CUSTOMER_ACCOUNTS_FILENAME);
		if(!accfile.exists()){
			File directory= new File(constant.CUSTOMER_DATA_FILE_PATH + name);
	        directory.mkdirs();
	        accfile.createNewFile();
		}
		FileOutputStream fileOutputStream = new FileOutputStream(accfile,true);  //set write pointer to the end of file
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(acc);
	    objectOutputStream.close();
	    fileOutputStream.close();
	    System.out.println("Account ID: " + acc.getAccID() + " Data Saved\n");
	}
	/**  
	
	 * <p>Title: readAllData</p>  
	
	 * <p>Description: read all the accounts information of a specific customer </p>  
	
	 * @param name cutomer's name
	 * @return Arraylist which includes all the accounts created by the customer
	 * @throws IOException
	 * @throws ClassNotFoundException  
	
	 */  
	@SuppressWarnings("finally")
	public static ArrayList<Account> readAllData(String name) throws IOException, ClassNotFoundException {
	    File file = new File(constant.CUSTOMER_DATA_FILE_PATH + name,constant.CUSTOMER_ACCOUNTS_FILENAME);
	    File directory = new File(constant.CUSTOMER_DATA_FILE_PATH + name);
	    ArrayList<Account> accList=new ArrayList<Account>();
	    Account accBuf;
	    //old consumer
	    if(directory.exists() && file.exists()){
	        FileInputStream fileInputStream = new FileInputStream(file);
	        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	     try{
		        while(true) {
		        	accBuf=(Account) objectInputStream.readObject();
		        	accList.add(accBuf);
		        	System.out.println("Account Information loaded...");
		        }
	        }catch(EOFException e){ 
	        	System.out.println("Account Information loaded finish!\n");
	        }
	        finally{
	            objectInputStream.close();
		        fileInputStream.close();      
		        System.out.println("account list length: "+accList.size());
		        return accList;
	          }
	    }
	    //new consumer
		return null;	    
	}
}
