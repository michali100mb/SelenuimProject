import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerPageAfterMiniStatement extends CustomerPageWithSideBar {
    @FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[6]/td[2]")
    WebElement cAccountNoInfo;

    @FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[16]/td[2]")
    WebElement cBalanceInfo;

    CustomerPageAfterMiniStatement() {
        super();
    }

    CustomerPageAfterMiniStatement(WebDriver driver) {
        super(driver);
    }
}
