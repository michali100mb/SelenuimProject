import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageAfterMoneyTransfer extends PageWithSideBar {
    @FindBy(xpath = "/html/body/table/tbody/tr[1]/td/p")
    WebElement moneyTransferHeadline;

    PageAfterMoneyTransfer() {
        super();
        scrollToElement();
    }

    PageAfterMoneyTransfer(WebDriver driver) {
        super(driver);
        scrollToElement();
    }

    public void scrollToElement() {
        super.scrollToElementImpl(moneyTransferHeadline, driver);
    }

    public void verifyPage() {
        Assert.assertEquals("Fund Transfer Details", moneyTransferHeadline.getText());
    }

}
