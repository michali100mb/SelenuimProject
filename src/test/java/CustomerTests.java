import org.junit.*;
import org.openqa.selenium.WebDriver;
import java.io.IOException;

public class CustomerTests {
    private static WebDriver driver;
    HomePage homepage;
    WelcomePage welcomePage;
    private String customerID;
    private String account1;
    private String account2;
    private String customerID2;
    private String account3;


    @BeforeClass
    public static void launchBrowser() {
        System.out.println("launching Chrome browser");
        System.setProperty(Util.USE_CHROME, Util.CHROME_PATH);
        driver = Util.CHROME_DRIVER;
    }
    @Before
    public void prepareToTest() throws IOException {
        homepage = new HomePage(driver);
        homepage.navigateToHomePage();
        homepage.scrollToElement();
        welcomePage = homepage.login(Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD);
        //create customer
        NewCustomerPage newCustomerPage = welcomePage.navigateToCreateNewCustomerPage();
        PageAfterCostumerCreated pageAfterCostumerCreated = newCustomerPage.createNewCustomer(Util.createCustomerData);
        customerID = pageAfterCostumerCreated.costumerIdNumber.getText();
        pageAfterCostumerCreated.windowSnip("CustomerTest_customer1Details");
        System.out.println("CustomerTest - customer1 ID is :" + customerID);
        welcomePage = pageAfterCostumerCreated.backToWelcomePage();
        //create account1
        CreateNewAccountPage createNewAccountPage = welcomePage.navigateToCreateNewAccountPage();
        PageAfterCreatingAccount pageAfterCreatingAccount = createNewAccountPage.initAccount(customerID, CreateNewAccountPage.ACCOUNTTYPE.Current, 500 );
        pageAfterCreatingAccount.windowSnip("CustomerTest_account1_Details");
        account1 = pageAfterCreatingAccount.accountNumber.getText();
        System.out.println("CustomerTest - Account Number is :" + account1);
        //create account2
        createNewAccountPage = pageAfterCreatingAccount.navigateToCreateNewAccountPage();
        pageAfterCreatingAccount = createNewAccountPage.initAccount(customerID, CreateNewAccountPage.ACCOUNTTYPE.Current, 500 );
        pageAfterCreatingAccount.windowSnip("CustomerTest_account2Details");
        account2 = pageAfterCreatingAccount.accountNumber.getText();
        System.out.println("CustomerTest -Account2 Number is :" + account2);

        //return to welcome page
        welcomePage = pageAfterCreatingAccount.backToWelcomePage();
    }

    //Old password is not valid. SC1
    @Test
    public void test20() throws IOException {
        //manager log out
        homepage = welcomePage.performLogOut();
        //customer log in
        CustomerWelcomePage customerWelcomePage = homepage.customerLogin(customerID, Util.customerPassword);
        //change password
        CustomerChangePasswordPage customerChangePasswordPage = customerWelcomePage.navigateToCustomerChangePasswordPage();
        customerChangePasswordPage.changePassword("WrongPass", "newPass1!", "newPass1!", false);
    }
    //Check balance. SC4
    @Test
    public void test21() throws IOException {
        //manager log out
        homepage = welcomePage.performLogOut();
        //customer log in
        CustomerWelcomePage customerWelcomePage = homepage.customerLogin(customerID, Util.customerPassword);
        //check balance
        CustomerBalanceEnquiryPage customerBalanceEnquiryPage = customerWelcomePage.navigateToCustomerBalanceEnquiryPage();
        CustomerPageAfterBalanceEnquiry customerPageAfterBalanceEnquiry = customerBalanceEnquiryPage.makeEnquiry(account1);
        customerPageAfterBalanceEnquiry.validatePage(account1);
        Assert.assertEquals("500", customerPageAfterBalanceEnquiry.getBalance());
    }
    //Transfer money to the same account. SC13
    @Test
    public void test22() throws IOException {
        //manager log out
        homepage = welcomePage.performLogOut();
        //customer log in
        CustomerWelcomePage customerWelcomePage = homepage.customerLogin(customerID, Util.customerPassword);
        //money transfer
        CustomerMoneyTransferPage customerMoneyTransferPage = customerWelcomePage.navigateToCustomerMoneyTransferPage();
        customerMoneyTransferPage.transferMoney(account1,
                                                account1,
                                                "100",
                                                "Test22",
                                                CustomerMoneyTransferPage.TRANSFERTYPE.InvalidSameAccount);
    }
    //Money transfer - valid. SC8
    @Test
    public void test23() throws IOException {
        //manager log out
        homepage = welcomePage.performLogOut();
        //customer log in
        CustomerWelcomePage customerWelcomePage = homepage.customerLogin(customerID, Util.customerPassword);
        //money transfer
        CustomerMoneyTransferPage customerMoneyTransferPage = customerWelcomePage.navigateToCustomerMoneyTransferPage();
        CustomerPageAfterMoneyTransfer customerPageAfterMoneyTransfer = customerMoneyTransferPage.transferMoney(account1,
                                                                                                                account2,
                                                                                                                "100",
                                                                                                                "Test22",
                                                                                                                CustomerMoneyTransferPage.TRANSFERTYPE.Valid);
        customerPageAfterMoneyTransfer.verifyPage(account1);
    }
    //Transfer to invalid account. SC12
    @Test
    public void test24() throws IOException {
        //manager log out
        homepage = welcomePage.performLogOut();
        //customer log in
        CustomerWelcomePage customerWelcomePage = homepage.customerLogin(customerID, Util.customerPassword);
        //money transfer
        CustomerMoneyTransferPage customerMoneyTransferPage = customerWelcomePage.navigateToCustomerMoneyTransferPage();
        CustomerPageAfterMoneyTransfer customerPageAfterMoneyTransfer = customerMoneyTransferPage.transferMoney(account1,
                                                                                                                "1000",
                                                                                                                "100",
                                                                                                                "Test22",
                                                                                                                CustomerMoneyTransferPage.TRANSFERTYPE.InvalidReceiverNotExist);
    }
    //Statement invalid account. SC15
    @Test
    public void test25() throws IOException {
        //manager log out
        homepage = welcomePage.performLogOut();
        //customer log in
        CustomerWelcomePage customerWelcomePage = homepage.customerLogin(customerID, Util.customerPassword);
        //get mini statement of account
        CustomerMiniStatementPage customerMiniStatementPage = customerWelcomePage.navigateToCustomerMiniStatementPage();
        customerMiniStatementPage.getMiniStatement("1000", CustomerMiniStatementPage.TESTTYPE.InvalidAccountNotExist);
    }
    //Money transfer from another customer account. SC11
    @Test
    public void test26() throws IOException {
        //extra preparation for current test
        //create customer2
        NewCustomerPage newCustomerPage = welcomePage.navigateToCreateNewCustomerPage();
        PageAfterCostumerCreated pageAfterCostumerCreated = newCustomerPage.createNewCustomer(Util.createCustomerData);
        customerID2 = pageAfterCostumerCreated.costumerIdNumber.getText();
        pageAfterCostumerCreated.windowSnip("customerTest_customer2_Details");
        System.out.println("CustomerTest - customer 2 ID is :" + customerID2);
        welcomePage = pageAfterCostumerCreated.backToWelcomePage();
        //create account3
        CreateNewAccountPage createNewAccountPage = welcomePage.navigateToCreateNewAccountPage();
        PageAfterCreatingAccount pageAfterCreatingAccount = createNewAccountPage.initAccount(customerID2, CreateNewAccountPage.ACCOUNTTYPE.Current, 500 );
        pageAfterCreatingAccount.windowSnip("CustomerTest_account3Details");
        account3 = pageAfterCreatingAccount.accountNumber.getText();
        System.out.println("CustomerTest -Account3 Number is :" + account1);
        //return to welcome page
        welcomePage = pageAfterCreatingAccount.backToWelcomePage();

        //manager log out
        homepage = welcomePage.performLogOut();
        //customer log in
        CustomerWelcomePage customerWelcomePage = homepage.customerLogin(customerID, Util.customerPassword);
        //money transfer
        CustomerMoneyTransferPage customerMoneyTransferPage = customerWelcomePage.navigateToCustomerMoneyTransferPage();
        CustomerPageAfterMoneyTransfer customerPageAfterMoneyTransfer = customerMoneyTransferPage.transferMoney(account3,
                                                                                                                account1,
                                                                                                                "100",
                                                                                                                "Test22",
                                                                                                                CustomerMoneyTransferPage.TRANSFERTYPE.InvalidNotAuthorized);
    }
    //Statement - Unauthorized. SC14
    @Test
    public void test27() throws IOException {
        //extra preparation for current test
        //create customer2
        NewCustomerPage newCustomerPage = welcomePage.navigateToCreateNewCustomerPage();
        PageAfterCostumerCreated pageAfterCostumerCreated = newCustomerPage.createNewCustomer(Util.createCustomerData);
        customerID2 = pageAfterCostumerCreated.costumerIdNumber.getText();
        pageAfterCostumerCreated.windowSnip("customerTest_customer2_Details");
        System.out.println("CustomerTest - customer 2 ID is :" + customerID2);
        welcomePage = pageAfterCostumerCreated.backToWelcomePage();
        //create account3
        CreateNewAccountPage createNewAccountPage = welcomePage.navigateToCreateNewAccountPage();
        PageAfterCreatingAccount pageAfterCreatingAccount = createNewAccountPage.initAccount(customerID2, CreateNewAccountPage.ACCOUNTTYPE.Current, 500 );
        pageAfterCreatingAccount.windowSnip("CustomerTest_account3Details");
        account3 = pageAfterCreatingAccount.accountNumber.getText();
        System.out.println("CustomerTest -Account3 Number is :" + account1);
        //return to welcome page
        welcomePage = pageAfterCreatingAccount.backToWelcomePage();

        //manager log out
        homepage = welcomePage.performLogOut();
        //customer log in
        CustomerWelcomePage customerWelcomePage = homepage.customerLogin(customerID, Util.customerPassword);
        //get mini statement of account
        CustomerMiniStatementPage customerMiniStatementPage = customerWelcomePage.navigateToCustomerMiniStatementPage();
        customerMiniStatementPage.getMiniStatement(account3, CustomerMiniStatementPage.TESTTYPE.InvalidNotAuthorized);

    }
    //Statement to wrong account. SC17
    @Test
    public void test28() throws IOException {
        //manager log out
        homepage = welcomePage.performLogOut();
        //customer log in
        CustomerWelcomePage customerWelcomePage = homepage.customerLogin(customerID, Util.customerPassword);
        //get customized statement
        CustomerCustomizedStatementPage customerCustomizedStatementPage = customerWelcomePage.navigateToCustomerCustomizedStatementPage();
        customerCustomizedStatementPage.getCustomStatement("1000",
                                                            Util.nowDate(),
                                                            Util.nowDate(),
                                                            "0",
                                                            "10",
                                                            CustomerCustomizedStatementPage.TESTTYPE.Invalid_WrongAccount);
    }
    //Date range is minus day. SC18
    @Test
    public void test29() throws IOException {
        //manager log out
        homepage = welcomePage.performLogOut();
        //customer log in
        CustomerWelcomePage customerWelcomePage = homepage.customerLogin(customerID, Util.customerPassword);
        //get customized statement
        CustomerCustomizedStatementPage customerCustomizedStatementPage = customerWelcomePage.navigateToCustomerCustomizedStatementPage();
        customerCustomizedStatementPage.getCustomStatement( account1,
                                                            Util.nowDate(),
                                                            Util.dateBefore(1),
                                                            "0",
                                                            "10",
                                                            CustomerCustomizedStatementPage.TESTTYPE.Invalid_DaysRangeMinus);
    }

    @After
    public void clearForNext() {
        //log in as manager
        homepage.navigateToHomePage();
        homepage.scrollToElement();
        welcomePage = homepage.login(Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD);

        DeleteCustomerPage deleteCustomerPage;
        String[] accounts = {account1, account2, account3};
        for (String account:accounts){
            if (account != null) {
                DeleteAccountPage deleteAccountPage = welcomePage.navigateToDeleteAccountPage();
                deleteAccountPage.deleteAccount(account, DeleteAccountPage.VALIDATIONTYPE.full);;
                System.out.println("****Account no. " + account + " was deleted ****");
            }
        }
        account1 = null;
        account2 = null;
        account3 = null;

        String[] customers = {customerID, customerID2};
        for (String customer:customers) {
            if (customer != null) {
                deleteCustomerPage = welcomePage.navigateToDeleteCustomerPage();
                welcomePage = deleteCustomerPage.deleteCustomerFullCheck(customer, true);
                System.out.println("****Customer no. " + customer + " was deleted ****");
            }
        }
        customerID = null;
        customerID2 = null;
        System.out.println("**** System Is ready for next test ****");
    }

    @AfterClass
    public static void closeBrowser() {
        if (driver != null){
            driver.quit();
            System.out.println("Browser closed");
        }
    }
}



