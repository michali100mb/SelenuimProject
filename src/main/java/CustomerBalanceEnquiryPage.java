import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CustomerBalanceEnquiryPage extends CustomerPageWithSideBar {
    @FindBy(name = "accountno")
    WebElement cAccountNoDropDown;

    @FindBy(name = "AccSubmit")
    WebElement cSubmitButton;

    CustomerBalanceEnquiryPage() {
        super();
    }

    CustomerBalanceEnquiryPage(WebDriver driver) {
        super(driver);
    }

    public CustomerPageAfterBalanceEnquiry makeEnquiry(String accountNo) {
        Select drpCountry = new Select(cAccountNoDropDown);
        drpCountry.selectByVisibleText(accountNo);
        cSubmitButton.click();
        return new CustomerPageAfterBalanceEnquiry(driver);
    }
}
