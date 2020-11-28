import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BalanceEnquiryPage extends PageWithSideBar {
    @FindBy(name = "accountno")
    WebElement accountNoBox;

    @FindBy(name = "AccSubmit")
    WebElement submitButton;

    BalanceEnquiryPage() {
        super();
        scrollToElement();
    }

    BalanceEnquiryPage(WebDriver driver) {
        super(driver);
        scrollToElement();
    }

    public void scrollToElement() {
        super.scrollToElementImpl(submitButton, driver);
    }

    public PageAfterBalanceEnquiry makeEnquiry(String accountNo, boolean accountValid, boolean authorized) {
        accountNoBox.sendKeys(accountNo);
        submitButton.click();
        if (!accountValid) {
            Assert.assertEquals("Account does not exist", getAlertText());
            acceptAlert();
        }
        else if (authorized) {
            return new PageAfterBalanceEnquiry(driver);
        }
        return null;
    }
}
