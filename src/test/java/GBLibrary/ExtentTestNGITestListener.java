package GBLibrary;

import java.io.IOException;

import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;

public class ExtentTestNGITestListener extends Base implements ITestListener {

	private static ExtentReports extent = ExtentManager.CreateInstance(".//target/extent/ExtentReport.html");
	private static ThreadLocal parentTest = new ThreadLocal();
	private static ThreadLocal test = new ThreadLocal();
			
	@Override
	public void onFinish(ITestContext arg0) {
		extent.flush();
		
	}
	@Override
	public void onStart(ITestContext context) {
		ExtentTest parent = extent.createTest(getClass().getName());
		parentTest.set(parent);
		
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// Do nothing
	}
	@Override
	public void onTestFailure(ITestResult result) {
		((ExtentTest)test.get()).fail("Test failed");
		String currentTime = libray.getCurrentTime();
		String currentDir = System.getProperty("user.dir");
		libray.captureScreenshotForExtent("screenshot" + currentTime, "target/extent/screenshots/");
		try {
			((ExtentTest)test.get()).fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(currentDir + "/target/extent/screenshots/screenshot" + currentTime + ".png").build());
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	@Override
	public void onTestSkipped(ITestResult result) {
		((ExtentTest)test.get()).skip("Test skipped");
		((ExtentTest)test.get()).skip(result.getThrowable());		
	}
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest child = ((ExtentTest) parentTest.get()).createNode(result.getMethod().getMethodName());
		test.set(child);		
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		((ExtentTest)test.get()).pass("Test passed");		
	}
	
	
	
}













