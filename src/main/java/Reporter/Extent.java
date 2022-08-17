package Reporter;

import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

        /* OB: extentTestMap holds the information of thread ids and ExtentTest instances.
                ExtentReports instance created by calling getReporter() method from ExtentManager.
                At startTest() method, an instance of ExtentTest created and put into extentTestMap with current thread id.
                At endTest() method, test ends and ExtentTest instance got from extentTestMap via current thread id.
                At getTest() method, return ExtentTest instance in extentTestMap by using current thread id.
         */

public class Extent {
    static Map extentTestMap = new HashMap();
     private static ExtentTest test;


   /* static ExtentReports extent = ExtentManager.getReporter();*/

    public static  ExtentTest getTest() {
        return test/*(ExtentTest)extentTestMap.get((int) (long) (Thread.currentThread().getId()))*/;
    }

    public static  void endTest() {
      //  extent.endTest((ExtentTest)extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    }

    public static void setTest(ExtentTest test) {
        Extent.test = test;

        //extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        //return test;
    }
}