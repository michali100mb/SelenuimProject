import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CreateNewAccountPage extends PageWithSideBar {
    public enum ACCOUNTTYPE {
        Current,
        Savings
    };
    @FindBy(name = "cusid")
    WebElement customerIdBox;

    @FindBy(name = "selaccount")
    WebElement selectAccountTypeDropDown;

    @FindBy(name = "inideposit")
    WebElement sumToDepositBox;

    @FindBy(name = "button2")
    WebElement submitButton;

    CreateNewAccountPage() {
        super();
        scrollToElement();
    }

    CreateNewAccountPage(WebDriver driver) {
        super(driver);
        scrollToElement();
    }

    public void scrollToElement() {
        super.scrollToElementImpl(sumToDepositBox, driver);
    }

    public PageAfterCreatingAccount initAccount(String customerId,ACCOUNTTYPE accountType_Current_OR_Savings, float sumToDeposit) {
        customerIdBox.sendKeys(customerId);
        selectAccountType(accountType_Current_OR_Savings);
        sumToDepositBox.sendKeys(Float.toString(sumToDeposit));
        submitButton.click();
        return new PageAfterCreatingAccount(driver);
    }

    public void selectAccountType(ACCOUNTTYPE accountType) {
        Select drpCountry = new Select(selectAccountTypeDropDown);
        drpCountry.selectByVisibleText(String.valueOf(accountType));
    }
}
