import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerMiniStatementPage extends CustomerPageWithSideBar {
    public static enum TESTTYPE {Valid, InvalidAccountNotExist, InvalidNotAuthorized}
    @FindBy(name = "accountno")
    WebElement cAccountNoBox;

    @FindBy(name = "AccSubmit")
    WebElement cSubmitButton;

    CustomerMiniStatementPage() {
        super();
    }

    CustomerMiniStatementPage(WebDriver driver) {
        super(driver);
    }

    public CustomerPageAfterMiniStatement getMiniStatement(String accountNo, CustomerMiniStatementPage.TESTTYPE testType) {
        cAccountNoBox.sendKeys(accountNo);
        cSubmitButton.click();
        if (testType == TESTTYPE.InvalidAccountNotExist) {
            Assert.assertEquals("Account does not exist", getAlertText());
            acceptAlert();
            return null;
        }
        else if (testType == TESTTYPE.InvalidNotAuthorized) {
            Assert.assertEquals("You are not authorize to generate statement of this Account!!", getAlertText());
            acceptAlert();
            return null;
        }
        return new CustomerPageAfterMiniStatement(driver);
    }

}
