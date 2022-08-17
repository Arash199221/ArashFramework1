package testClasses.testcontext;

import Reporter.ExtentTestManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class TestContext {
    public WebDriver driver;

    public ExtentTest test;

    @BeforeTest
    public void browserSetup(ITestContext result) {
        System.setProperty("webdriver.chrome.driver", getBrowserLocation());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/");
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //extent
        //ExtentTest test=ExtentManager.getReportInstance().createTest(result.getName());
        test=  ExtentTestManager.startTest(result.getName(),"Description");

      //  test= ExtentTestManager.getTest();
       test.log(Status.INFO,"Inside before test");
        System.out.println(result.getName());
       // Extent.setTest(test);
    }

    private String getBrowserLocation() {
        String loc;
        if (System.getProperty("os.name").contains("Mac")) {
            loc = "//src//main//resources//drivers//mac//chromedriver";
        } else {
            loc = "//src//main//resources//drivers//chromedriver.exe";
        }
        return (System.getProperty("user.dir") + loc);
    }

    @AfterTest
    public void browserShutDown() {
        driver.quit();
       // ExtentManager.getReportInstance().flush();

    }


    @AfterSuite
    public void teardown() {
       //
    }
}
