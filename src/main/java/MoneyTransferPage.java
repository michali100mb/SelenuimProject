import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MoneyTransferPage extends PageWithSideBar {
    public static enum TRANSFERTYPE {Valid, InvalidSameAccount, InvalidNotEnoughMoney}
    @FindBy(name = "payersaccount")
    WebElement fromAccountBox;

    @FindBy(name = "payeeaccount")
    WebElement toAccountBox;

    @FindBy(name = "ammount")
    WebElement amountBox;

    @FindBy(name = "desc")
    WebElement descriptionBox;

    @FindBy(name = "AccSubmit")
    WebElement submitButton;

    MoneyTransferPage() {
        super();
    }

    MoneyTransferPage(WebDriver driver) {
        super(driver);
    }

    public void scrollToElement() {
        super.scrollToElementImpl(descriptionBox, driver);
    }

    public PageAfterMoneyTransfer transferMoney(String fromAccount, String toAccount, String amount, String description, TRANSFERTYPE valid_or_invalid) {
        fromAccountBox.sendKeys(fromAccount);
        toAccountBox.sendKeys(toAccount);
        amountBox.sendKeys(amount);
        descriptionBox.sendKeys(description);
        submitButton.click();
        if (valid_or_invalid == TRANSFERTYPE.InvalidSameAccount) {
            Assert.assertEquals("Payers account No and Payees account No Must Not be Same!!!", getAlertText());
            acceptAlert();
            return null;
        }
        else if (valid_or_invalid == TRANSFERTYPE.InvalidNotEnoughMoney) {
            Assert.assertEquals("Transfer Failed. Account Balance low!!", getAlertText());
            acceptAlert();
            return null;
        }
        return new PageAfterMoneyTransfer(driver);
    }
}
