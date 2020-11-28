import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteCustomerPage extends PageWithSideBar{
    @FindBy(name = "cusid")
    WebElement customerIdBox;

    @FindBy(name = "AccSubmit")
    WebElement submitButton;

    DeleteCustomerPage() {
        super();
        scrollToElement();
    }

    DeleteCustomerPage(WebDriver driver) {
        super(driver);
        scrollToElement();
    }

    public void scrollToElement() {
        super.scrollToElementImpl(submitButton, driver);
    }

    public void submitCustomerId(String customerID) {
        customerIdBox.sendKeys(customerID);
        submitButton.click();
    }

    public WelcomePage deleteCustomerCheck(String customerID, boolean checkPopUp)  {
        submitCustomerId(customerID);
        if (checkPopUp == true){
            Assert.assertEquals("Do you really want to delete this Customer?", getAlertText());
        }
        acceptAlert();
        if (checkPopUp == false){
            Assert.assertEquals("Customer deleted Successfully",getAlertText());
        }
        acceptAlert();
        return new WelcomePage(driver);
    }

    public WelcomePage deleteCustomerFullCheck(String customerID, boolean exists) {
        return deleteCustomerFullCheck(customerID ,exists ,false);
    }

    public WelcomePage deleteCustomerFullCheck(String customerID, boolean exists, boolean activeAccounts) {
        submitCustomerId(customerID);
        Assert.assertEquals("Do you really want to delete this Customer?", getAlertText());
        acceptAlert();
        if (exists) {
            if (activeAccounts) {
                Assert.assertEquals("Customer could not be deleted!!. First delete all accounts of this customer then delete the customer", getAlertText());
                acceptAlert();
                return null;
            }
            Assert.assertEquals("Customer deleted Successfully",getAlertText());
        }
        else {
            Assert.assertEquals("Customer does not exist!!",getAlertText());
            acceptAlert();
            return null;
        }
        acceptAlert();
        return new WelcomePage(driver);
    }

}
