import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditCustomerPage extends NewCustomerPage {
    @FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[1]/td/p")
    WebElement pageName;

    EditCustomerPage() {
        super();
    }

    EditCustomerPage(WebDriver driver) {
        super(driver);
    }

    public void validateEditCustomerPage() {
     Assert.assertEquals("Edit Customer", pageName.getText());
    }




}
