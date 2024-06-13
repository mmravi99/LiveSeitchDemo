package testcomponents;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;

public class Listeners extends BaseTest implements ITestListener{
	

	public Listeners() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void onTestStart(ITestResult result) {
		System.out.println("Test Started");

	}
	public void onTestSuccess(ITestResult result) {
		
		extent.flush();
	}
	public void onTestFailure(ITestResult result) {
		
		extent.flush();
	}
	public void onStart(ITestContext context) {
		System.out.println("Suite Started");
		try {
			Runtime.getRuntime().exec("taskkill /f /fi");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			extent = setUp();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	public void onFinish(ITestContext context) {
		System.out.println("Suit Finished");
	}
}
