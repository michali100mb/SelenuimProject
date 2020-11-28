import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageWithSideBar extends Page {

    public String alertWhenLoggedOut = "You Have Succesfully Logged Out!!";

    @FindBy(css = "[align='center']")
    WebElement userNameBox;

    @FindBy(css = "[href='Managerhomepage.php']")
    WebElement welcomePage;

    @FindBy(css = "[href='addcustomerpage.php']")
    WebElement createNewCustomerPage;

    @FindBy(css = "[href='EditCustomer.php']")
    WebElement editCustomerPage;

    @FindBy(css = "[href='DeleteCustomerInput.php']")
    WebElement deleteCustomerPage;

    @FindBy(css = "[href='addAccount.php']")
    WebElement createNewAccountPage;

    @FindBy(css = "[href='editAccount.php']")
    WebElement editAccount;

    @FindBy(css = "[href='deleteAccountInput.php']")
    WebElement deleteAccountPage;

    @FindBy(css = "[href='DepositInput.php']")
    WebElement depositToAccountPage;

    @FindBy(css = "[href='WithdrawalInput.php']")
    WebElement withdrawFromAccountPage;

    @FindBy(css = "[href='FundTransInput.php']")
    WebElement moneyTransferPage;

    @FindBy(css = "[href='PasswordInput.php']")
    WebElement changePasswordPage;

    @FindBy(css = "[href='BalEnqInput.php']")
    WebElement balanceInquiryPage;

    @FindBy(css = "[href='MiniStatementInput.php']")
    WebElement miniStatementPage;

    @FindBy(css = "[href='CustomisedStatementInput.php']")
    WebElement customisedStatementPage;

    @FindBy(css = "[href='Logout.php']")
    WebElement logOutPage;


    PageWithSideBar() {
        super();
    }

    PageWithSideBar(WebDriver driver) {
        super(driver);
    }

    public void scrollToElement() {
        super.scrollToElementImpl(withdrawFromAccountPage, driver);
    }

    //*****Navigate Pages*****
    public WelcomePage backToWelcomePage() {
        welcomePage.click();
        return new WelcomePage(driver);
    }

    public ChangePasswordPage navigateToChangePasswordPage () {
        changePasswordPage.click();
        return new ChangePasswordPage(driver);
    }

    public HomePage performLogOut () {
        logOutPage.click();
        Assert.assertEquals(alertWhenLoggedOut, getAlertText());
        acceptAlert();
        return new HomePage(driver);
    }

    public NewCustomerPage navigateToCreateNewCustomerPage() {
        createNewCustomerPage.click();
        return new NewCustomerPage(driver);
    }

    public DeleteCustomerPage navigateToDeleteCustomerPage() {
        deleteCustomerPage.click();
        return new DeleteCustomerPage(driver);
    }

    public PreEditCustomerPage navigateToEditCustomerPage() {
        editCustomerPage.click();
        return new PreEditCustomerPage(driver);
    }

    public CreateNewAccountPage navigateToCreateNewAccountPage() {
        createNewAccountPage.click();
        return new CreateNewAccountPage(driver);
    }

    public DeleteAccountPage navigateToDeleteAccountPage() {
        deleteAccountPage.click();
        return new DeleteAccountPage(driver);
    }

    public WithdrawFromAccountPage navigateToWithdrawFromAccountPage() {
        withdrawFromAccountPage.click();
        return new WithdrawFromAccountPage(driver);
    }

    public MoneyTransferPage navigateToMoneyTransferPage() {
        moneyTransferPage.click();
        return new MoneyTransferPage(driver);
    }

    public BalanceEnquiryPage navigateToBalanceEnquiryPage() {
        balanceInquiryPage.click();
        return new BalanceEnquiryPage(driver);
    }

}
