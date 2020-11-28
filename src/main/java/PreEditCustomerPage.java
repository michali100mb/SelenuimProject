import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PreEditCustomerPage extends PageWithSideBar {
    @FindBy(name = "cusid")
    WebElement customerIdBox;

    @FindBy(name = "AccSubmit")
    WebElement submitButton;

    PreEditCustomerPage() {
        super();
        scrollToElement();
    }

    PreEditCustomerPage(WebDriver driver) {
        super(driver);
        scrollToElement();
    }


    public void scrollToElement() {
        super.scrollToElementImpl(submitButton, driver);
    }

    public EditCustomerPage enterCustomerToEdit(String customerID) {
        return enterCustomerToEdit(customerID, true);
    }

    public EditCustomerPage enterCustomerToEdit(String customerID, boolean exists) {
        customerIdBox.sendKeys(customerID);
        submitButton.click();
        if (exists) {
            return new EditCustomerPage(driver);
        }
        Assert.assertEquals("Customer does not exist!!",getAlertText());
        acceptAlert();
        return null;
    }
}
