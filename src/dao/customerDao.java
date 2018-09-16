package dao;

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
* <p>Title: customerDao</p>  
* <p>Description: methods of read/write customer's information</p>  
* @author Yujie Yang  
*/  
public class customerDao {
		/**  
		 * <p>Title: saveData</p>  
		 * <p>Description:store customer's information to the customer's own directory  </p>  
		 * @param c specific customer
		 * @throws IOException  
		 */  
		public static void saveData(Customer c) throws IOException{
			String name=c.getName();
			File cusfile=new File(constant.CUSTOMER_DATA_FILE_PATH + name, constant.CUSTOMER_INFO_FILENAME);
			if(!cusfile.exists()){
				File directory= new File(constant.CUSTOMER_DATA_FILE_PATH+name);
	            directory.mkdirs();
	            cusfile.createNewFile();
			}
			FileOutputStream fileOutputStream = new FileOutputStream(cusfile);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(c);
	        objectOutputStream.close();
	        fileOutputStream.close();
	        System.out.println("Consumer " + c.getName() + " Data Saved\n");
	    }
		/**  
		 * <p>Title: readData</p>  
		 * <p>Description: read information of a specific customer </p>  
		 * @param name customer's name
		 * @return a customer object read in file
		 * @throws IOException
		 * @throws ClassNotFoundException  
		 */  
		public static Customer readData(String name) throws IOException, ClassNotFoundException {
	        File file = new File(constant.CUSTOMER_DATA_FILE_PATH+name,constant.CUSTOMER_INFO_FILENAME);
	        File directory = new File(constant.CUSTOMER_DATA_FILE_PATH+name);
	        //old consumer
	        if(directory.exists() && file.exists()){
	            FileInputStream fileInputStream = new FileInputStream(file);
	            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	            Customer bufC = (Customer) objectInputStream.readObject();
	            objectInputStream.close();
	            fileInputStream.close();
	            System.out.println("Consumer " + bufC.getName() + " Data Loaded \n");
	            return bufC;
	        }
	        //new consumer
	        else {
	            System.out.println("Wrong name, The customer does not exist.\n");
	            return null;
	        }
	    }

}
