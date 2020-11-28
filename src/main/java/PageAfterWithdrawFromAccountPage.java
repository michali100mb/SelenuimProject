import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageAfterWithdrawFromAccountPage extends PageWithSideBar {
    @FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[7]/td[2]")
    WebElement accountNumberInfo;

    @FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[23]/td[2]")
    WebElement balanceInfo;

    PageAfterWithdrawFromAccountPage() {
        super();
        scrollToElement();
    }

    PageAfterWithdrawFromAccountPage(WebDriver driver) {
        super(driver);
        scrollToElement();
    }

    public void scrollToElement() {
        super.scrollToElementImpl(accountNumberInfo, driver);
    }

    public void checkWithdraw(String accountNo) {
        Assert.assertEquals(accountNo, accountNumberInfo.getText());
    }

    public void validateForDelete(String account1) {
        String balance = balanceInfo.getText();
        if (!balance.equalsIgnoreCase("0")) {
            PageWithSideBar tempPage = new PageWithSideBar(this.driver);
            WithdrawFromAccountPage withdrawFromAccountPage = tempPage.navigateToWithdrawFromAccountPage();
            PageAfterWithdrawFromAccountPage pageAfterWithdrawFromAccountPage = withdrawFromAccountPage.withdraw(account1, "balance", "deletingAccount");
            pageAfterWithdrawFromAccountPage.checkWithdraw(account1);
        }
    }
}
