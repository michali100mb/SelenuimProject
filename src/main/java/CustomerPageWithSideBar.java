import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerPageWithSideBar extends Page {
    public String alertWhenLoggedOut = "You Have Succesfully Logged Out!!";

    @FindBy(linkText = "Customer")
    WebElement cWelcomePage;

    @FindBy(linkText = "Balance Enquiry")
    WebElement cBalanceInquiryPage;

    @FindBy(linkText = "Fund Transfer")
    WebElement cMoneyTransferPage;

    @FindBy(linkText = "Changepassword")
    WebElement cChangePasswordPage;

    @FindBy(linkText = "Mini Statement")
    WebElement cMiniStatementPage;

    @FindBy(linkText = "Customised Statement")
    WebElement cCustomisedStatementPage;

    @FindBy(css = "[href='Logout.php']")
    WebElement cLogOutPage;


    CustomerPageWithSideBar() {
        super();
        scrollToElement();
    }

    CustomerPageWithSideBar(WebDriver driver) {
        super(driver);
        scrollToElement();
    }

    public void scrollToElement() {
        super.scrollToElementImpl(cLogOutPage, driver);
    }

    //*****Navigate Pages*****
    public CustomerWelcomePage backToWelcomePage() {
        cWelcomePage.click();
        return new CustomerWelcomePage(driver);
    }

    public HomePage performLogOut () {
        cLogOutPage.click();
        Assert.assertEquals(alertWhenLoggedOut, getAlertText());
        acceptAlert();
        return new HomePage(driver);
    }

    public CustomerChangePasswordPage navigateToCustomerChangePasswordPage() {
        cChangePasswordPage.click();
        return new CustomerChangePasswordPage(driver);
    }

    public CustomerBalanceEnquiryPage navigateToCustomerBalanceEnquiryPage() {
        cBalanceInquiryPage.click();
        return new CustomerBalanceEnquiryPage(driver);
    }

    public CustomerMoneyTransferPage navigateToCustomerMoneyTransferPage() {
        cMoneyTransferPage.click();
        return new CustomerMoneyTransferPage(driver);
    }

    public CustomerMiniStatementPage navigateToCustomerMiniStatementPage() {
        cCustomisedStatementPage.click();
        return new CustomerMiniStatementPage(driver);
    }

    public CustomerCustomizedStatementPage navigateToCustomerCustomizedStatementPage() {
        cCustomisedStatementPage.click();
        return new CustomerCustomizedStatementPage(driver);
    }
}
