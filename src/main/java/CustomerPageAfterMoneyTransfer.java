import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerPageAfterMoneyTransfer extends CustomerPageWithSideBar {
    @FindBy(xpath = "/html/body/table/tbody/tr[1]/td/p")
    WebElement cMoneyTransferHeadline;

    CustomerPageAfterMoneyTransfer() {
        super();
    }

    CustomerPageAfterMoneyTransfer(WebDriver driver) {
        super(driver);
    }

    public void verifyPage(String accountNo) {
        Assert.assertEquals("Fund Transafer Details for Account No: " + accountNo, cMoneyTransferHeadline.getText());
    }
}
