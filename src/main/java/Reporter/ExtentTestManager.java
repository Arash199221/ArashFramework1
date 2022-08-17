package Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

        /* OB: extentTestMap holds the information of thread ids and ExtentTest instances.
                ExtentReports instance created by calling getReporter() method from ExtentManager.
                At startTest() method, an instance of ExtentTest created and put into extentTestMap with current thread id.
                At endTest() method, test ends and ExtentTest instance got from extentTestMap via current thread id.
                At getTest() method, return ExtentTest instance in extentTestMap by using current thread id.
         */

public class ExtentTestManager {
    static Map extentTestMap = new HashMap();
    static ExtentReports extent = ExtentManager.getReportInstance();

    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        System.out.println((int) (long) (Thread.currentThread().getId()));
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }

    public static synchronized ExtentTest getTest() {
        return (ExtentTest)extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized void endTest() {
        extent.removeTest((ExtentTest)extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    }


}