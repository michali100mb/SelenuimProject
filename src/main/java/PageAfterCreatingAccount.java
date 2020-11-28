import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageAfterCreatingAccount extends PageWithSideBar {
    @FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[4]/td[2]")
    WebElement accountNumber;

    PageAfterCreatingAccount() {
        super();
        scrollToElement();
    }

    PageAfterCreatingAccount(WebDriver driver) {
        super(driver);
        scrollToElement();
    }

    public void scrollToElement() {
        super.scrollToElementImpl(accountNumber, driver);
    }


}
