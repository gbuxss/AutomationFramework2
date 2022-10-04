package gbuxssCreation.ReusableTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import gbuxssCreation.Resources.ExtentReport;

public class Listener extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent=ExtentReport.getReport();
	ThreadLocal<ExtentTest> thread = new ThreadLocal<ExtentTest>();//thread safe
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		test= extent.createTest(result.getMethod().getMethodName());
		thread.set(test);
		}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.PASS, "Test Passed Successfully");
		}

	@Override
	public void onTestFailure(ITestResult result) {
		thread.get().fail(result.getThrowable());
		String path =null;
		try {
			driver =(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			 path=takeScreenshot(result.getMethod().getMethodName(),driver);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(path, result.getMethod().getMethodName());

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();

	}

}
