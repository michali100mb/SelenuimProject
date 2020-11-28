import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageAfterBalanceEnquiry extends PageWithSideBar{
    @FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[6]/td[2]")
    WebElement accountNoInfo;

    @FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[16]/td[2]")
    WebElement balanceInfo;

    PageAfterBalanceEnquiry() {
        super();
        scrollToElement();
    }

    PageAfterBalanceEnquiry(WebDriver driver) {
        super(driver);
        scrollToElement();
    }

    public void scrollToElement() {
        super.scrollToElementImpl(balanceInfo, driver);
    }

    public void validatePage(String accountNo) {
        Assert.assertEquals(accountNo, accountNoInfo.getText());
    }

    public String getBalance() {
        return balanceInfo.getText();
    }

}
