package vtiger_crm_generic_utility;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class is consists of methods related to retrieving the excel file data.
 */

public class ExcelFileUtility 
{
	/**
	 * This method is used to fetch the data from excel file.
	 * @param sheet_name
	 * @param row
	 * @param cell
	 * @return
	 * @throws Exception
	 */
	public String toReadDataFromExcelFile(String sheetname, int row, int cell) throws Exception 
	{
		// To get the data from Excel File
		FileInputStream efis = new FileInputStream(".\\src\\test\\resources\\testData.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		String data = wb.getSheet(sheetname).getRow(row).getCell(cell).toString();
		return data;
	}
}
