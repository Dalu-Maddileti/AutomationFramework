package vtiger_crm_generic_utility;

import java.util.Date;
import java.util.Random;

/**
 * This class consists of methods related to java
 */
public class JavaFileUtility {

	/**
	 * This method is used to generate random numbers from 0 to 1000 range
	 * @return
	 */
	public int toGetRandomNumber() {
		Random r = new Random();
		int random = r.nextInt(1000);
		return random;
	}
/**
 * This method will help us to get system date and time in string format
 * @return
 */
	
	public String toGetSystemDateAndTime() {
		
		Date d = new Date();
		System.out.println(d);
		String date[] = d.toString().split(" ");
		String day = date[0];
		String month = date[1];
		String date1 = date[2];
		String time = date[3].replace(":", "-");
		String year = date[5];
		String finalDate = day + " " + month + " " + date1 + " " + time + " " + year;
		return finalDate;
	}
}
