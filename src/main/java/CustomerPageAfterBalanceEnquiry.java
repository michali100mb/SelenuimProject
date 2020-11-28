import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerPageAfterBalanceEnquiry extends CustomerPageWithSideBar {
    @FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[6]/td[2]")
    WebElement cAccountNoInfo;

    @FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[16]/td[2]")
    WebElement cBalanceInfo;

    CustomerPageAfterBalanceEnquiry() {
        super();
    }

    CustomerPageAfterBalanceEnquiry(WebDriver driver) {
        super(driver);
    }


    public void validatePage(String accountNo) {
        Assert.assertEquals(accountNo, cAccountNoInfo.getText());
    }

    public String getBalance() {
        return cBalanceInfo.getText();
    }
}
