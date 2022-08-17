package testClasses.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import testClasses.testcontext.TestContext;

public class AmazonTests extends TestContext {

    @Test
    @Parameters({"menuItem", "subMenuItem", "filterType", "filterValue", "sortByValue", "itemIndex"})
    public void verifyFilterAndSort(String menuItem, String subMenuItem, String filterType, String filterValue, String sortByValue, int itemIndex) {

        HomePage homePage = new HomePage(this);
        homePage
                .clickHamburgMenu();
                /*.selectMenuType(menuItem)
                .selectSubMenuType(subMenuItem)
                .filterBy(filterType, filterValue)
                .sortResultsBy(sortByValue)
                .selectResultItem(itemIndex)
                .verifyAboutSectionVisibility();*/
        ///
    }

    @Test
    @Parameters({"menuItem", "subMenuItem", "filterType", "filterValue", "sortByValue", "itemIndex"})
    public void verifyFilterAndSort2(String menuItem, String subMenuItem, String filterType, String filterValue, String sortByValue, int itemIndex) {

        //More configs could be added here

        // ExtentTest test= ExtentManager.getReportInstance().createTest("My Test");
/////////////
        //  ExtentTest test= ExtentManager.getReportInstance().createTest("My first test");

        ////////////
        HomePage homePage = new HomePage(this);
        //  Extent.getTest().log(Status.INFO, "Print the action");
        homePage
                .clickHamburgMenu()
                .selectMenuType(menuItem);
                /*.selectSubMenuType(subMenuItem)
                .filterBy(filterType, filterValue)
                .sortResultsBy(sortByValue)
                .selectResultItem(itemIndex)
                .verifyAboutSectionVisibility();*/
        ///
        //    Extent.getTest().log(Status.FAIL, "Log failed");
        //    Extent.getTest().pass("");

        ///////////save the report
        //  report.flush();
    }
}
