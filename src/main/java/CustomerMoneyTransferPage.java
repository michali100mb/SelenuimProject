import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerMoneyTransferPage extends CustomerPageWithSideBar {
    public static enum TRANSFERTYPE {Valid, InvalidSameAccount, InvalidNotEnoughMoney, InvalidNotAuthorized, InvalidReceiverNotExist}
    @FindBy(name = "payersaccount")
    WebElement cFromAccountBox;

    @FindBy(name = "payeeaccount")
    WebElement cToAccountBox;

    @FindBy(name = "ammount")
    WebElement cAmountBox;

    @FindBy(name = "desc")
    WebElement cDescriptionBox;

    @FindBy(name = "AccSubmit")
    WebElement cSubmitButton;

    CustomerMoneyTransferPage() {
        super();
    }

    CustomerMoneyTransferPage(WebDriver driver) {
        super(driver);
    }

    public CustomerPageAfterMoneyTransfer transferMoney(String fromAccount, String toAccount, String amount, String description, CustomerMoneyTransferPage.TRANSFERTYPE valid_or_invalid) {
        cFromAccountBox.sendKeys(fromAccount);
        cToAccountBox.sendKeys(toAccount);
        cAmountBox.sendKeys(amount);
        cDescriptionBox.sendKeys(description);
        cSubmitButton.click();
        if (valid_or_invalid == CustomerMoneyTransferPage.TRANSFERTYPE.InvalidSameAccount) {
            Assert.assertEquals("Payers account No and Payees account No Must Not be Same!!!", getAlertText());
            acceptAlert();
            return null;
        }
        else if (valid_or_invalid == CustomerMoneyTransferPage.TRANSFERTYPE.InvalidNotEnoughMoney) {
            Assert.assertEquals("Transfer Failed. Account Balance low!!", getAlertText());
            acceptAlert();
            return null;
        }
        else if (valid_or_invalid == CustomerMoneyTransferPage.TRANSFERTYPE.InvalidNotAuthorized) {
            Assert.assertEquals("You are not authorize to Transfer Funds from this account!!", getAlertText());
            acceptAlert();
            return null;
        }
        else if (valid_or_invalid == TRANSFERTYPE.InvalidReceiverNotExist) {
            Assert.assertEquals("Account " + toAccount + "does not exist!!!", getAlertText());
            acceptAlert();
            return null;
        }
        return new CustomerPageAfterMoneyTransfer(driver);
    }
}
