package pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testClasses.testcontext.TestContext;

import java.util.Set;

public class HomePage extends PageElement {
    WebDriver pageDriver;
    public TestContext testContext;
    public ExtentTest test;

    public HomePage(TestContext testContext) {
        super(testContext);
        this.testContext = testContext;
        pageDriver = testContext.driver;
        test=testContext.test;
    }

    /*Step 2
     * Click on the hamburger menu in the top left corner.
     */
    public HomePage clickHamburgMenu() {
        test.log(Status.INFO,"Click on Hamburger");
        WebElement weMenu = getPageElement("hamburger.menu");
        this.click(weMenu);
        return this;
    }

    /*Step 3
    Scroll down and then Click on the TV, Appliances and Electronics link under Shop by Department section.
     */
    public HomePage selectMenuType(String menuItem) {
        test.log(Status.INFO,"Select MEnu Type");
        WebElement menuType = getPageElement("menu.type", menuItem);
        this.moveToAndClick(menuType);
        return this;
    }

    /*Step 4
     Then click on Televisions under Tv, Audio & Cameras sub section.
            */
    public HomePage selectSubMenuType(String subMenuItem) {
        WebElement menuSubType = getPageElement("subMenu.type", subMenuItem);
        this.moveToAndClick(menuSubType);
        return this;
    }

    /*Step 5
      Scroll down and filter the results by Brand ‘Samsung’.*/
    public HomePage filterBy(String filterType, String filterValue) {
        WebElement filterBy = getPageElement("filer.type", filterType, filterValue);
        this.scrollTo(filterBy);
        this.moveToAndClick(filterBy);
        return this;
    }

    /*Step 6
     Sort the Samsung results with price High to Low.
        */
    public HomePage sortResultsBy(String sortByValue) {
        WebElement sortBy = getPageElement("sortBy");
        this.select(sortBy, sortByValue);
        return this;
    }

    /*Step 7,8
      Click on the second highest priced item (whatever that maybe at the time of automating).
      Switch the Window
            */
    public ProductPage selectResultItem(int itemIndex) {
        WebElement resultItem = getPageElement("result.item", String.valueOf(itemIndex));
        this.click(resultItem);
        //Switch the Window
        String currentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String newWindow : handles) {
            if (!newWindow.equalsIgnoreCase(currentWindow)) {
                //switching to the opened tab
                driver.switchTo().window(newWindow);
            }
        }
        return new ProductPage(testContext);
    }
}
