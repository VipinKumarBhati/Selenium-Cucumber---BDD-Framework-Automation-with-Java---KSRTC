package reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import pageObjects.BasePage;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reporter.ExtentReportManager;

public class Listeners implements ITestListener {
	 
    //Extent Report Declarations
    public static ExtentReports extent = ExtentReportManager.createInstance();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public static ExtentTest parentNode;
    public static String previousClassName = "";
    public static String currentClassName = "";
 
    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Cucumber BDD Framework Test Suite started!");
    }
 
    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println("Cucumber BDD Framework Test Suite is ending!");
        extent.flush();
    }
 
    @Override
    public synchronized void onTestStart(ITestResult result) {
    	String parameterList = null;
        System.out.println((result.getMethod().getMethodName() + " started!"));
        if (result.getParameters().length > 0) {
        	for (int iterator = 0; iterator < result.getParameters().length ; iterator++) {
        		if (iterator ==0)
        			parameterList = result.getParameters()[iterator].toString();
        		else
        			parameterList = parameterList + " ; " + result.getParameters()[iterator].toString();
        	}
        	System.out.println("Parameters : " + parameterList);
        }
        System.out.println(("Class: " + result.getMethod().getRealClass().getSimpleName()));
//        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
//        test.set(extentTest);
        currentClassName = result.getMethod().getRealClass().getSimpleName();
        if (!previousClassName.equals(currentClassName)){
        	test.set(extent.createTest(result.getMethod().getRealClass().getSimpleName()));
        }
        parentNode = test.get();
        if (parameterList == null)
        	test.set(test.get().createNode(result.getMethod().getMethodName(),result.getMethod().getDescription()));
        else
        	test.set(test.get().createNode(result.getMethod().getMethodName() + " [ " + parameterList + " ]",result.getMethod().getDescription()));
    }
    
    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " passed!"));
        test.get().log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
        previousClassName = currentClassName;
        test.set(parentNode);
    }
 
    @Override
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " failed!"));
        test.get().log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
        try {
			test.get().addScreenCaptureFromPath(BasePage.getScreenshot(result.getName()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        test.get().fail(result.getThrowable());
        previousClassName = currentClassName;
        test.set(parentNode);
    }
 
    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " skipped!"));
        test.get().log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
        test.get().skip(result.getThrowable());
        previousClassName = currentClassName;
        test.set(parentNode);
    }
 
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }
}
