package vtiger_crm_generic_utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class consists of methods related to property file
 */
public class PropertiesFileUtility 
{
	/**
	 * This method is used to read data from properties file, if key is passed.
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String toReadDataFromPropertiesFile(String key) throws IOException 
	{
		// Step_1: Create a object FileInputStream
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\testData.properties");
		
		//Step_2: Create object properties file
		Properties prop = new Properties();
		
		//Call methods
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;
	}
	
}
