import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageAfterCostumerCreated extends PageWithSideBar {
    @FindBy(xpath = "//*[@id=\"customer\"]/tbody/tr[4]/td[2]")
    WebElement costumerIdNumber;

    PageAfterCostumerCreated() {
        super();
        scrollToElement();
    }

    PageAfterCostumerCreated(WebDriver driver) {
        super(driver);
        scrollToElement();
    }


    public void scrollToElement() {
        super.scrollToElementImpl(costumerIdNumber, driver);
    }
}
