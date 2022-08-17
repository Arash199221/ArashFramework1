package Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    static ExtentReports report;

    public synchronized static ExtentReports getReportInstance() {
        if (report == null) {
            //Set HTML reporting file location
            //String reportLocation = "ExtentReports.html";
            return createInstance();
        }
       return report;
    }

    private static ExtentReports createInstance(/*String reportLocation*/) {
        ExtentSparkReporter spark = new ExtentSparkReporter("spark.html");
        ExtentReports report = new ExtentReports();
        //More configs could be added here

        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("My Test report");
        spark.config().setEncoding("utf-8");
        spark.config().setReportName("Report Name");
        report.attachReporter(spark);
        return report;
    }
}
