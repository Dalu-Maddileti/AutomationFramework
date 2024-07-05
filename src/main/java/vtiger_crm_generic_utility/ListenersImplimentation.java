package vtiger_crm_generic_utility;

import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImplimentation implements ITestListener{

	ExtentReports reports = new ExtentReports();
	File file = new File("./extentReports/Report- " + new JavaFileUtility().toGetSystemDateAndTime() + ".html");
	ExtentSparkReporter htmlreport = new ExtentSparkReporter(file);
	ExtentTest test;
	
	@Override
	public void onTestStart(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "---Test Started---");
		test = reports.createTest(methodname); 
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "---Test got passed---");
		test.log(Status.PASS, methodname + "---passed.");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "---Test got failed---");
		WebDriverUtility wutil = new WebDriverUtility();
		JavaFileUtility jutil = new JavaFileUtility();
		String screenshotname = methodname + " " + jutil.toGetSystemDateAndTime();
		try {
			String path = wutil.toTakeScreenshot(BaseClass.sDriver, screenshotname);
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(Status.FAIL, methodname + "---failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "---Test got skipped---");
		test.log(Status.SKIP, methodname + "---skipped");
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("---Suite Execution Started---");
		
		//Extent Reports
		htmlreport.config().setDocumentTitle("Vtiger Execution Reports");
		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setReportName("VTIGER EXECUTION REPORTS");
		
		
		reports.attachReporter(htmlreport);
		reports.setSystemInfo("BaseUrl", "http://localhost:8888/");
		reports.setSystemInfo("Username", "admin");
		reports.setSystemInfo("Password", "password");
		reports.setSystemInfo("ReporterName", "D Maddileti");
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("---Suite Execution Finished---");
		reports.flush();
	}
	
	
}
