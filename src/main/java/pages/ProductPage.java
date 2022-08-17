package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import testClasses.testcontext.TestContext;

public class ProductPage extends PageElement {
    WebDriver pageDriver;

    public ProductPage(TestContext testContext) {
        super(testContext);
        pageDriver = testContext.driver;
    }

    /*Step9
      Assert that “About this item” section is present and log this section text to console/report
      */
    public ProductPage verifyAboutSectionVisibility() {
        WebElement aboutSection = getPageElement("about.section");
        Assert.assertTrue(aboutSection.isDisplayed());
        System.out.println(aboutSection.getText());
        return this;
    }
}
