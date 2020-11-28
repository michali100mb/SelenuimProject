import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewCustomerPage extends PageWithSideBar {
    @FindBy(name = "name")
    WebElement customerNameBox;

    @FindBy(css = "[value='m']")
    WebElement genderMaleRadioButton;

    @FindBy(css = "[value='f']")
    WebElement genderFemaleRadioButton;

    @FindBy(css = "[type='date']")
    WebElement dateOfBirthBox;

    @FindBy(name = "addr")
    WebElement addressBox;

    @FindBy(name = "city")
    WebElement cityBox;

    @FindBy(name = "state")
    WebElement stateBox;

    @FindBy(name = "pinno")
    WebElement pinNumberBox;

    @FindBy(name = "telephoneno")
    WebElement mobileNumberBox;

    @FindBy(name = "emailid")
    WebElement eMailBox;

    @FindBy(name = "password")
    WebElement passwordBox;

    @FindBy(name = "sub")
    WebElement submitButton;

    NewCustomerPage() {
        super();
        scrollToElement();
    }

    NewCustomerPage(WebDriver driver) {
        super(driver);
        scrollToElement();
    }

    public void scrollToElement() {
        super.scrollToElementImpl(addressBox, driver);
    }

    public PageAfterCostumerCreated createNewCustomer (Object[] customerData) {
        customerNameBox.sendKeys((CharSequence) customerData[0]);
        if (customerData[1] == "2") {
            genderFemaleRadioButton.click();
        }
        dateOfBirthBox.sendKeys((CharSequence) customerData[2]);
        addressBox.sendKeys((CharSequence) customerData[3]);
        cityBox.sendKeys((CharSequence) customerData[3]);
        stateBox.sendKeys((CharSequence) customerData[4]);
        pinNumberBox.sendKeys((CharSequence) customerData[5]);
        mobileNumberBox.sendKeys((CharSequence) customerData[6]);
        eMailBox.sendKeys(Util.emailGenerator());
        passwordBox.sendKeys((CharSequence) customerData[7]);
        submitButton.click();
        return new PageAfterCostumerCreated(driver);
    }



}
