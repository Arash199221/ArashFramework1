package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import testClasses.testcontext.TestContext;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class PageElement {
    public WebDriver driver;
    private Wait<WebDriver> fluentWait;
    Properties properties = new Properties();

    public PageElement(TestContext testContext) {
        driver = testContext.driver;
    }

    public WebElement getPageElement(String elementName, String... replace) {
        By by = buildWebElement(elementName, replace);
        fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofMillis(6000))
                .pollingEvery(Duration.ofMillis(200));
        try {
            WebElement webElement = fluentWait.until(driver -> driver.findElement(by));
            return webElement;
        } catch (Exception exception) {
            /* handle exception while finding an element*/
            fluentWait.until(ExpectedConditions.elementToBeClickable(by));
            return driver.findElement(by);
        }
    }

    public PageElement click(WebElement webElement) {
        try {
            webElement.click();
        } catch (Exception e) {
            /* alternative for click could be implemented here*/
        }
        return this;
    }

    public PageElement moveToAndClick(WebElement webElement) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).build().perform();
            webElement.click();
        } catch (Exception e) {
            /* alternative for click could be implemented here*/
        }
        return this;
    }

    public PageElement select(WebElement webElement, String optionText) {
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(optionText);
        } catch (Exception e) {
            /* alternative for select could be implemented here*/
        }
        return this;
    }

    public PageElement scrollTo(WebElement webElement) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,3000)");
            js.executeScript("arguments[0].scrollIntoView(true);", webElement);
        } catch (Exception e) {
            /* alternative for scroll could be implemented here*/
        }
        return this;
    }

    private String loadPageElements(String elementName) {
        properties = new Properties();
        String locatorValue = "";
        try {
            properties.load(new FileInputStream(System.getProperty("user.dir") + "//src//main//resources//elementRepository//" + getClass().getSimpleName() + ".properties"));
            locatorValue = properties.getProperty(elementName);
        } catch (Exception e) {
            System.out.println("Some issue finding or loading file....!!! " + e.getMessage());
        }
        return locatorValue;
    }

    private String replaceVariables(String input, String... replace) {
        if (replace.length > 0 && replace.length != 1) {
            for (String text : replace) {
                input = input.replaceFirst("%s", text);
            }
        } else if (replace.length > 0 && replace.length == 1) {
            input = input.replace("%s", replace[0]);
        }
        return input;
    }

    private By buildWebElement(String elementName, String... replace) {
        String locatorValue = loadPageElements(elementName);
        String locator[] = locatorValue.split(";");
        String finalLocator = replaceVariables(locator[0], replace);
        By locatorByValue;
        switch (locator[1]) {
            case "CSS":
                locatorByValue = By.cssSelector(finalLocator);
                break;
            case "XPATH":
                locatorByValue = By.xpath(finalLocator);
                break;
            case "CLASSNAME":
                locatorByValue = By.className(finalLocator);
                break;
            case "TAGNAME":
                locatorByValue = By.tagName(finalLocator);
                break;
            case "LINKTEXT":
                locatorByValue = By.linkText(finalLocator);
                break;
            case "PARTIALLINKTEXT":
                locatorByValue = By.partialLinkText(finalLocator);
                break;
            case "ID":
                locatorByValue = By.id(finalLocator);
                break;
            case "NAME":
                locatorByValue = By.name(finalLocator);
                break;
            default:
                locatorByValue = null;
        }
        return locatorByValue;
    }
}