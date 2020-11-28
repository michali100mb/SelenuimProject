import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteAccountPage extends PageWithSideBar {
    public static enum VALIDATIONTYPE {full, popUp, verification};
    @FindBy(name = "accountno")
    WebElement accountNumberBox;

    @FindBy(name = "AccSubmit")
    WebElement submitButton;

    DeleteAccountPage() {
        super();
        scrollToElement();
    }

    DeleteAccountPage(WebDriver driver) {
        super(driver);
        scrollToElement();
    }

    public void scrollToElement() {
        super.scrollToElementImpl(submitButton, driver);
    }

    public WelcomePage deleteAccount(String accountNumber, VALIDATIONTYPE typeEnum ) {
        submitAccountNumber(accountNumber);
        if (typeEnum == VALIDATIONTYPE.full || typeEnum == VALIDATIONTYPE.popUp) {
            Assert.assertEquals("Do you really want to delete this Account?", getAlertText());
        }
        acceptAlert();
        if (typeEnum == VALIDATIONTYPE.full || typeEnum == VALIDATIONTYPE.verification) {
            Assert.assertEquals("Account Deleted Sucessfully", getAlertText());
        }
        acceptAlert();
        return new WelcomePage(driver);
    }

    public WelcomePage deleteAccountCleanForNext(String accountNumber) {
        submitAccountNumber(accountNumber);
        acceptAlert();
        acceptAlert();
        return new WelcomePage(driver);
    }

    private void submitAccountNumber(String accountNumber) {
        accountNumberBox.sendKeys(accountNumber);
        submitButton.click();
    }
}
