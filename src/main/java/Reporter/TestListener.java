package Reporter;

import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("I am in onStart method " + iTestContext.getName());
       // iTestContext.setAttribute("WebDriver", this.driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("I am in onFinish method " + iTestContext.getName());
        //Do tier down operations for extentreports reporting!
        //ExtentTestManager.endTest();
        ExtentTestManager.extent.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("I am in onTestStart method " +  getTestMethodName(iTestResult) + " start");
        //Start operation for extentreports.
        //ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(),"Description");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am in onTestSuccess method " +  getTestMethodName(iTestResult) + " succeed");
        //System.out.println(ExtentTestManager.getTest().getRunStatus());
       // iTestResult.getStatus();
        //iTestResult.st
      //  iTestResult.setStatus(0);
        //Extentreports log operation for passed tests.
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in onTestFailure method " +  getTestMethodName(iTestResult) + " failed");

        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
       // WebDriver webDriver = ((BaseTest) testClass).getDriver();

        //Take base64Screenshot screenshot.
        //String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BASE64);

        //Extentreports log and screenshot operations for failed tests.
       // ExtentTestManager.getTest().log(LogStatus.FAIL,iTestResult.getMethod().getMethodName(),
           //     iTestResult.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("I am in onTestSkipped method "+  getTestMethodName(iTestResult) + " skipped");
        //Ext//entreports log operation for skipped tests.
       // ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}
