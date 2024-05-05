package AutomationTesting.TestCompoents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import AutomationTesting.resources.ExtentReporterNG;


public class Listeeners extends BaseTest implements ITestListener {
ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReporterobject();
	ThreadLocal<ExtentTest>extentTest=new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //unique thread id (ErrorValidationTest)-->
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test Pass");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().fail(result.getThrowable());
	String filePath = null;
	
	try {
		driver=(WebDriver)result.getTestClass().getRealClass().getField("driver")
		.get(result.getInstance());
	} catch (IllegalArgumentException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IllegalAccessException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (NoSuchFieldException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (SecurityException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	
	try {
		filePath = getScreenShort(result.getMethod().getMethodName(),driver);
		
	} 
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
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
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		//flush report html if missed that it won't genrate
		extent.flush();
	}

}
