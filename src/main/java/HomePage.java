import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page{
    @FindBy(name="uid")
    WebElement userNameBox;

    @FindBy(name="password")
    WebElement passwordBox;

    @FindBy(name="btnLogin")
    WebElement loginButton;

    HomePage() {
        super();
    }

    HomePage(WebDriver driver) {
        super(driver);
    }

    public void enterUserName (String username) {
        userNameBox.sendKeys(username);
    }

    public void enterPassword (String password) {
        passwordBox.sendKeys(password);
    }


    public WelcomePage login (String username, String password) {
        return login(username, password, true);
    }

    public WelcomePage login (String username, String password, boolean valid) {
        enterUserName(username);
        enterPassword(password);
        loginButton.click();
        if (valid) {
            return new WelcomePage(driver);
        }
        Assert.assertEquals("User or Password is not valid", getAlertText());
        acceptAlert();
        return null;
    }

    public CustomerWelcomePage customerLogin (String username, String password) {
        enterUserName(username);
        enterPassword(password);
        loginButton.click();
        return new CustomerWelcomePage(driver);
    }

    public void navigateToHomePage() {
        driver.get(Util.homePageUrl);
    }

    public void scrollToElement() {
        super.scrollToElementImpl(loginButton, driver);
    }

    public void validatePage() {
        Assert.assertTrue(userNameBox.isEnabled());
    }
}
