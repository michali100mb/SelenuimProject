import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WithdrawFromAccountPage extends PageWithSideBar {
    @FindBy(name = "accountno")
    WebElement accountNumberBox;

    @FindBy(name = "ammount")
    WebElement amountBox;

    @FindBy(name = "desc")
    WebElement descriptionBox;

    @FindBy(name = "AccSubmit")
    WebElement submitButton;

    WithdrawFromAccountPage() {
        super();
        scrollToElement();
    }

    WithdrawFromAccountPage(WebDriver driver) {
        super(driver);
        scrollToElement();
    }

    public void scrollToElement() {
        super.scrollToElementImpl(descriptionBox, driver);
    }

    public PageAfterWithdrawFromAccountPage withdraw(String accountNumber, String amount, String description) {
        accountNumberBox.sendKeys(accountNumber);
        amountBox.sendKeys(amount);
        descriptionBox.sendKeys(description);
        submitButton.click();
        return new PageAfterWithdrawFromAccountPage(driver);
    }



}
