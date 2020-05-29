package listeners;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utilities.DriverManager;
import utilities.Log;
import utilities.TestUtil;


public class CustomListener implements ITestListener,ISuiteListener {
	
	static String messageBody;
	ThreadLocal<ExtentTest> testReport=DriverManager.testReport;
	ExtentReports extent=DriverManager.extent;
	
	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Mail sent to the recipients");
		/*
		 * MonitoringMail mail = new MonitoringMail();
		 * 
		 * try { messageBody = "http://" + InetAddress.getLocalHost().getHostAddress() +
		 * ":8080/job/DataDrivenLiveProject/Extent_Reports/"; } catch
		 * (UnknownHostException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * try { mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to,
		 * TestConfig.subject, messageBody); } catch (AddressException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch (MessagingException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
	}

	
	@Override
	public void onTestStart(ITestResult result) {
		
		ExtentTest test=extent.createTest("Test started for Test: "+result.getTestClass().getName()+" and Method: "+result.getMethod().getMethodName());
		testReport.set(test);
		Log.startTestCase(result.getTestClass().getName()+"......"+result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Markup m=MarkupHelper.createLabel(result.getMethod().getMethodName().toUpperCase()+" is Passed", ExtentColor.GREEN);
		testReport.get().pass(m);
		Reporter.log(result.getMethod().getMethodName().toUpperCase()+" is Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver=null;
     	try {
			driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		Markup m=MarkupHelper.createLabel(result.getMethod().getMethodName().toUpperCase()+" is Failed", ExtentColor.RED);
		testReport.get().fail(m);
		TestUtil.captureScreenshot(driver,result.getMethod().getMethodName());
		testReport.get().log(Status.FAIL, result.getThrowable().getMessage());
		String exceptionMessage=Arrays.toString(result.getThrowable().getStackTrace());
		
		testReport.get().fail("<details> <summary> <font color=red> Exception Occured : Click to see </font></summary> "+exceptionMessage.replaceAll(",", "<br>")+"</details> ");
		try {
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure  : " + "</font>" + "</b>", (MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.screenshotPathExtent).build()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Reporter.log(result.getMethod().getMethodName().toUpperCase()+" is Failed");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=200></img></a>");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Markup m=MarkupHelper.createLabel(result.getMethod().getMethodName().toUpperCase()+" is Skipped", ExtentColor.ORANGE);
		testReport.get().skip(m);
		testReport.get().log(Status.SKIP, result.getThrowable().getMessage());
String exceptionMessage=Arrays.toString(result.getThrowable().getStackTrace());
		
		testReport.get().skip("<details> <summary> <font color=orange> Exception Occured : Click to see </font></summary> "+exceptionMessage.replaceAll(",", "<br>")+"</details> ");
		Reporter.log(result.getMethod().getMethodName().toUpperCase()+" is Skipped");
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
		if (extent != null) {

			extent.flush();
		}
		
		Log.endTestCase(context.getName());
	}

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

}
