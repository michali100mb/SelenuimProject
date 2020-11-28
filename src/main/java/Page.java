import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public abstract class Page {
    String url;
    WebDriver driver;

    public abstract void scrollToElement();

    Page() {
        setWebDriver();
        waitForElements();
    }

    Page(WebDriver driver) {
        this.driver = driver;
        waitForElements();
    }

    private void setWebDriver() {
        this.driver = Util.CHROME_DRIVER;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void closeBrowser() {
        driver.quit();
    }

    public void waitForElements() {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public void waitUntil(WebElement elementToWait, int secondsToWait) {
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, secondsToWait);
        PageFactory.initElements(factory, this);
    }

    public void windowSnip(String testName) throws IOException {
        ScreenShot.takeSnapShot(driver, testName);
    }

    protected void scrollToElementImpl(WebElement elementToScrollBy, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        //This will scroll the page till the element is found
    js.executeScript("arguments[0].scrollIntoView();", elementToScrollBy);
    }

    //*****Handle Alerts*****
    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }
}
