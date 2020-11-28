import org.junit.*;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import org.junit.Test;

public class Tests {
    private static WebDriver driver;
    HomePage homepage;
    private String customerID;
    private String account1;
    private String account2;


    @BeforeClass
    public static void launchBrowser() {
        System.out.println("launching Chrome browser");
        System.setProperty(Util.USE_CHROME, Util.CHROME_PATH);
        driver = Util.CHROME_DRIVER;
    }
    @Before
    public void prepareToTest() {
        homepage = new HomePage(driver);
        homepage.navigateToHomePage();
        homepage.scrollToElement();
    }


    //Manager login - correct
    @Test
    public void test1() throws IOException {
        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD);
        Assert.assertEquals(Util.MANAGER_USERNAME, welcomePage.validatePage());
        welcomePage.scrollToElement();
        welcomePage.windowSnip("test1");
    }

    //Manager login - Password is wrong
    @Test
    public void test2() {
        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, "invalidPass", false);

    }

    //Manager login - Username is wrong
    @Test
    public void test3() {
        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, "invalidPass", false);
    }

    //Manager login - All details are wrong
    @Test
    public void test4() {
        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, "invalidPass", false);
    }

    //Manager change password - old password is incorrect. SM1
    @Test
    public void test5() throws IOException {
        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD);
        ChangePasswordPage changePasswordPage = welcomePage.navigateToChangePasswordPage();
        changePasswordPage.changePassword("WrongPass", "Aex123!", "test5_EnteredValues");
        Assert.assertEquals(changePasswordPage.alertOldPassWrong, changePasswordPage.getAlertText());
        changePasswordPage.acceptAlert();
        Assert.assertTrue(changePasswordPage.oldPasswordBox.isEnabled());
    }

    //Manager log out. SM35
    @Test
    public void test6() throws IOException, InterruptedException {
        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD);
        homepage = welcomePage.performLogOut();
        homepage.validatePage();
    }
    //Create new customer. SM4
    @Test
    public void test7() throws IOException {
        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD);
        NewCustomerPage newCustomerPage = welcomePage.navigateToCreateNewCustomerPage();
        PageAfterCostumerCreated pageAfterCostumerCreated = newCustomerPage.createNewCustomer(Util.createCustomerData);
        customerID = pageAfterCostumerCreated.costumerIdNumber.getText();
        pageAfterCostumerCreated.windowSnip("test7_customerDetails");
        System.out.println("Test 7 - customer ID is :" + customerID);
    }
    //Check confirmation in deleting process. SM13
    @Test
    public void test8() throws IOException {
        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD);
        NewCustomerPage newCustomerPage = welcomePage.navigateToCreateNewCustomerPage();
        PageAfterCostumerCreated pageAfterCostumerCreated = newCustomerPage.createNewCustomer(Util.createCustomerData);
        customerID = pageAfterCostumerCreated.costumerIdNumber.getText();
        pageAfterCostumerCreated.windowSnip("test8_customerDetails");
        System.out.println("Test 8 - customer ID is :" + customerID);
        welcomePage = pageAfterCostumerCreated.backToWelcomePage();
        DeleteCustomerPage deleteCustomerPage = welcomePage.navigateToDeleteCustomerPage();
        welcomePage = deleteCustomerPage.deleteCustomerCheck(customerID, false);
        customerID = null;

    }
    //Check popUp in deleting process. SM11
    @Test
    public void test9() throws IOException {
        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD);
        NewCustomerPage newCustomerPage = welcomePage.navigateToCreateNewCustomerPage();
        PageAfterCostumerCreated pageAfterCostumerCreated = newCustomerPage.createNewCustomer(Util.createCustomerData);
        customerID = pageAfterCostumerCreated.costumerIdNumber.getText();
        pageAfterCostumerCreated.windowSnip("test9_customerDetails");
        System.out.println("Test 9 - customer ID is :" + customerID);
        welcomePage = pageAfterCostumerCreated.backToWelcomePage();
        DeleteCustomerPage deleteCustomerPage = welcomePage.navigateToDeleteCustomerPage();
        welcomePage = deleteCustomerPage.deleteCustomerCheck(customerID, true);
        customerID = null;
    }
    //Delete not exist customer. SM15
    @Test
    public void test10() throws IOException {
        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD);
        NewCustomerPage newCustomerPage = welcomePage.navigateToCreateNewCustomerPage();
        PageAfterCostumerCreated pageAfterCostumerCreated = newCustomerPage.createNewCustomer(Util.createCustomerData);
        customerID = pageAfterCostumerCreated.costumerIdNumber.getText();
        pageAfterCostumerCreated.windowSnip("test10_customerDetails");
        System.out.println("Test 10 - customer ID is :" + customerID);
        welcomePage = pageAfterCostumerCreated.backToWelcomePage();
        DeleteCustomerPage deleteCustomerPage = welcomePage.navigateToDeleteCustomerPage();
        welcomePage = deleteCustomerPage.deleteCustomerFullCheck(customerID, true);
        deleteCustomerPage = welcomePage.navigateToDeleteCustomerPage();
        deleteCustomerPage.deleteCustomerFullCheck(customerID, false);
        customerID = null;
    }
    //Edit customer. SM23
    @Test
    public void test11() throws IOException {
        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD);
        NewCustomerPage newCustomerPage = welcomePage.navigateToCreateNewCustomerPage();
        PageAfterCostumerCreated pageAfterCostumerCreated = newCustomerPage.createNewCustomer(Util.createCustomerData);
        customerID = pageAfterCostumerCreated.costumerIdNumber.getText();
        pageAfterCostumerCreated.windowSnip("test11_customerDetails");
        System.out.println("Test 11 - customer ID is :" + customerID);
        welcomePage = pageAfterCostumerCreated.backToWelcomePage();
        PreEditCustomerPage preEditCustomerPage = welcomePage.navigateToEditCustomerPage();
        EditCustomerPage editCustomerPage = preEditCustomerPage.enterCustomerToEdit(customerID);
        editCustomerPage.validateEditCustomerPage();
    }
    //Can't edit a deleted customer. SM14
    @Test   //(expected = Exception.class)
    public void test12() throws IOException {
        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD);
        NewCustomerPage newCustomerPage = welcomePage.navigateToCreateNewCustomerPage();
        PageAfterCostumerCreated pageAfterCostumerCreated = newCustomerPage.createNewCustomer(Util.createCustomerData);
        customerID = pageAfterCostumerCreated.costumerIdNumber.getText();
        pageAfterCostumerCreated.windowSnip("test12_customerDetails");
        System.out.println("Test 12 - customer ID is :" +customerID);
        welcomePage = pageAfterCostumerCreated.backToWelcomePage();
        DeleteCustomerPage deleteCustomerPage = welcomePage.navigateToDeleteCustomerPage();
        welcomePage = deleteCustomerPage.deleteCustomerFullCheck(customerID, true);
        PreEditCustomerPage preEditCustomerPage = welcomePage.navigateToEditCustomerPage();
        preEditCustomerPage.enterCustomerToEdit(customerID, false);
        customerID = null;
    }
    //Create account. SM5
    @Test
    public void test13() throws IOException {
        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD);
        //create customer
        NewCustomerPage newCustomerPage = welcomePage.navigateToCreateNewCustomerPage();
        PageAfterCostumerCreated pageAfterCostumerCreated = newCustomerPage.createNewCustomer(Util.createCustomerData);
        customerID = pageAfterCostumerCreated.costumerIdNumber.getText();
        pageAfterCostumerCreated.windowSnip("test13_customerDetails");
        System.out.println("Test 13 - customer ID is :" + customerID);
        welcomePage = pageAfterCostumerCreated.backToWelcomePage();

        //create account
        CreateNewAccountPage createNewAccountPage = welcomePage.navigateToCreateNewAccountPage();
        PageAfterCreatingAccount pageAfterCreatingAccount = createNewAccountPage.initAccount(customerID, CreateNewAccountPage.ACCOUNTTYPE.Current, 500);
        pageAfterCreatingAccount.windowSnip("test13_accountDetails");
        account1 = pageAfterCreatingAccount.accountNumber.getText();
        System.out.println("Test 13 -Account Number is :" + account1);
    }
    //Can't delete customer if there is an active account. SM12
    @Test
    public void test14() throws IOException {
        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD);

        //create customer
        NewCustomerPage newCustomerPage = welcomePage.navigateToCreateNewCustomerPage();
        PageAfterCostumerCreated pageAfterCostumerCreated = newCustomerPage.createNewCustomer(Util.createCustomerData);
        customerID = pageAfterCostumerCreated.costumerIdNumber.getText();
        pageAfterCostumerCreated.windowSnip("test14_customerDetails");
        System.out.println("Test 14 - customer ID is :" + customerID);
        welcomePage = pageAfterCostumerCreated.backToWelcomePage();

        //create account
        CreateNewAccountPage createNewAccountPage = welcomePage.navigateToCreateNewAccountPage();
        PageAfterCreatingAccount pageAfterCreatingAccount = createNewAccountPage.initAccount(customerID, CreateNewAccountPage.ACCOUNTTYPE.Current, 500 );
        pageAfterCreatingAccount.windowSnip("test13_accountDetails");
        account1 = pageAfterCreatingAccount.accountNumber.getText();
        System.out.println("Test 14 -Account Number is :" + account1);

        //Withdraw from account
        WithdrawFromAccountPage withdrawFromAccountPage = pageAfterCreatingAccount.navigateToWithdrawFromAccountPage();
        PageAfterWithdrawFromAccountPage pageAfterWithdrawFromAccountPage = withdrawFromAccountPage.withdraw(account1, "500", "deletingAccount");
        pageAfterWithdrawFromAccountPage.checkWithdraw(account1);
        pageAfterWithdrawFromAccountPage.validateForDelete(account1);

        //delete customer
        DeleteCustomerPage deleteCustomerPage = withdrawFromAccountPage.navigateToDeleteCustomerPage();
        deleteCustomerPage.deleteCustomerFullCheck(customerID, true, true);
    }
    //Delete account until popUp check. SM6
    @Test
    public void test15() throws IOException {
        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD);
        //create customer
        NewCustomerPage newCustomerPage = welcomePage.navigateToCreateNewCustomerPage();
        PageAfterCostumerCreated pageAfterCostumerCreated = newCustomerPage.createNewCustomer(Util.createCustomerData);
        customerID = pageAfterCostumerCreated.costumerIdNumber.getText();
        pageAfterCostumerCreated.windowSnip("test15_customerDetails");
        System.out.println("Test 15 - customer ID is :" + customerID);
        welcomePage = pageAfterCostumerCreated.backToWelcomePage();

        //create account
        CreateNewAccountPage createNewAccountPage = welcomePage.navigateToCreateNewAccountPage();
        PageAfterCreatingAccount pageAfterCreatingAccount = createNewAccountPage.initAccount(customerID, CreateNewAccountPage.ACCOUNTTYPE.Current, 500 );
        pageAfterCreatingAccount.windowSnip("test15_accountDetails");
        account1 = pageAfterCreatingAccount.accountNumber.getText();
        System.out.println("Test 15 -Account Number is :" + account1);

        //Withdraw from account
        WithdrawFromAccountPage withdrawFromAccountPage = pageAfterCreatingAccount.navigateToWithdrawFromAccountPage();
        PageAfterWithdrawFromAccountPage pageAfterWithdrawFromAccountPage = withdrawFromAccountPage.withdraw(account1, "500", "deletingAccount");
        pageAfterWithdrawFromAccountPage.checkWithdraw(account1);
        pageAfterWithdrawFromAccountPage.validateForDelete(account1);

        //delete account
        DeleteAccountPage deleteAccountPage = pageAfterWithdrawFromAccountPage.navigateToDeleteAccountPage();
        welcomePage = deleteAccountPage.deleteAccount(account1 ,DeleteAccountPage.VALIDATIONTYPE.popUp);
        DeleteCustomerPage deleteCustomerPage = welcomePage.navigateToDeleteCustomerPage();
        account1 = null;
    }
    //Delete Account until verification. SM7
    @Test
    public void test16() throws IOException {
        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD);
        //create customer
        NewCustomerPage newCustomerPage = welcomePage.navigateToCreateNewCustomerPage();
        PageAfterCostumerCreated pageAfterCostumerCreated = newCustomerPage.createNewCustomer(Util.createCustomerData);
        customerID = pageAfterCostumerCreated.costumerIdNumber.getText();
        pageAfterCostumerCreated.windowSnip("test16_customerDetails");
        System.out.println("Test 16 - customer ID is :" + customerID);
        welcomePage = pageAfterCostumerCreated.backToWelcomePage();

        //create account
        CreateNewAccountPage createNewAccountPage = welcomePage.navigateToCreateNewAccountPage();
        PageAfterCreatingAccount pageAfterCreatingAccount = createNewAccountPage.initAccount(customerID, CreateNewAccountPage.ACCOUNTTYPE.Current, 500 );
        pageAfterCreatingAccount.windowSnip("test16_accountDetails");
        account1 = pageAfterCreatingAccount.accountNumber.getText();
        System.out.println("Test 16 -Account Number is :" + account1);

        //Withdraw from account
        WithdrawFromAccountPage withdrawFromAccountPage = pageAfterCreatingAccount.navigateToWithdrawFromAccountPage();
        PageAfterWithdrawFromAccountPage pageAfterWithdrawFromAccountPage = withdrawFromAccountPage.withdraw(account1, "500", "deletingAccount");
        pageAfterWithdrawFromAccountPage.checkWithdraw(account1);
        pageAfterWithdrawFromAccountPage.validateForDelete(account1);

        //delete account
        DeleteAccountPage deleteAccountPage = pageAfterWithdrawFromAccountPage.navigateToDeleteAccountPage();
        welcomePage = deleteAccountPage.deleteAccount(account1 ,DeleteAccountPage.VALIDATIONTYPE.verification);
        DeleteCustomerPage deleteCustomerPage = welcomePage.navigateToDeleteCustomerPage();
        account1 = null;
    }
    //Transfer to the same account. SM21
    @Test
    public void test17() throws IOException {
        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD);
        //create customer
        NewCustomerPage newCustomerPage = welcomePage.navigateToCreateNewCustomerPage();
        PageAfterCostumerCreated pageAfterCostumerCreated = newCustomerPage.createNewCustomer(Util.createCustomerData);
        customerID = pageAfterCostumerCreated.costumerIdNumber.getText();
        pageAfterCostumerCreated.windowSnip("test17_customerDetails");
        System.out.println("Test 17 - customer ID is :" + customerID);
        welcomePage = pageAfterCostumerCreated.backToWelcomePage();

        //create account
        CreateNewAccountPage createNewAccountPage = welcomePage.navigateToCreateNewAccountPage();
        PageAfterCreatingAccount pageAfterCreatingAccount = createNewAccountPage.initAccount(customerID, CreateNewAccountPage.ACCOUNTTYPE.Current, 500 );
        pageAfterCreatingAccount.windowSnip("test17_accountDetails");
        account1 = pageAfterCreatingAccount.accountNumber.getText();
        System.out.println("Test 17 - Account Number is :" + account1);

        //transfer amount to the same account
        MoneyTransferPage moneyTransferPage = pageAfterCostumerCreated.navigateToMoneyTransferPage();
        moneyTransferPage.transferMoney(account1, account1, "100", "test", MoneyTransferPage.TRANSFERTYPE.InvalidSameAccount);
    }
    //Balance for deleted account. SM9
    @Test
    public void test18() throws IOException {
        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD);
        //create customer
        NewCustomerPage newCustomerPage = welcomePage.navigateToCreateNewCustomerPage();
        PageAfterCostumerCreated pageAfterCostumerCreated = newCustomerPage.createNewCustomer(Util.createCustomerData);
        customerID = pageAfterCostumerCreated.costumerIdNumber.getText();
        pageAfterCostumerCreated.windowSnip("test18_customerDetails");
        System.out.println("Test 18 - customer ID is :" + customerID);
        welcomePage = pageAfterCostumerCreated.backToWelcomePage();

        //create account
        CreateNewAccountPage createNewAccountPage = welcomePage.navigateToCreateNewAccountPage();
        PageAfterCreatingAccount pageAfterCreatingAccount = createNewAccountPage.initAccount(customerID, CreateNewAccountPage.ACCOUNTTYPE.Current, 500 );
        pageAfterCreatingAccount.windowSnip("test18_accountDetails");
        account1 = pageAfterCreatingAccount.accountNumber.getText();
        System.out.println("Test 18 -Account Number is :" + account1);

        //Withdraw from account
        WithdrawFromAccountPage withdrawFromAccountPage = pageAfterCreatingAccount.navigateToWithdrawFromAccountPage();
        PageAfterWithdrawFromAccountPage pageAfterWithdrawFromAccountPage = withdrawFromAccountPage.withdraw(account1, "500", "deletingAccount");
        pageAfterWithdrawFromAccountPage.checkWithdraw(account1);
        pageAfterWithdrawFromAccountPage.validateForDelete(account1);

        //delete account
        DeleteAccountPage deleteAccountPage = pageAfterWithdrawFromAccountPage.navigateToDeleteAccountPage();
        welcomePage = deleteAccountPage.deleteAccount(account1 ,DeleteAccountPage.VALIDATIONTYPE.full);

        //balance of account
        BalanceEnquiryPage balanceEnquiryPage = welcomePage.navigateToBalanceEnquiryPage();
        balanceEnquiryPage.makeEnquiry(account1, false, true);
        account1 = null;
    }
    //Transfer when there is not enough money. SM22
    @Test
    public void test19() throws IOException {
        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD);
        //create customer
        NewCustomerPage newCustomerPage = welcomePage.navigateToCreateNewCustomerPage();
        PageAfterCostumerCreated pageAfterCostumerCreated = newCustomerPage.createNewCustomer(Util.createCustomerData);
        customerID = pageAfterCostumerCreated.costumerIdNumber.getText();
        pageAfterCostumerCreated.windowSnip("test19_customerDetails");
        System.out.println("Test 19 - customer ID is :" + customerID);
        welcomePage = pageAfterCostumerCreated.backToWelcomePage();

        //create account1
        CreateNewAccountPage createNewAccountPage = welcomePage.navigateToCreateNewAccountPage();
        PageAfterCreatingAccount pageAfterCreatingAccount = createNewAccountPage.initAccount(customerID, CreateNewAccountPage.ACCOUNTTYPE.Current, 500 );
        pageAfterCreatingAccount.windowSnip("test19_accountDetails");
        account1 = pageAfterCreatingAccount.accountNumber.getText();
        System.out.println("Test 19 -Account Number is :" + account1);

        //create account2
        createNewAccountPage = pageAfterCreatingAccount.navigateToCreateNewAccountPage();
        pageAfterCreatingAccount = createNewAccountPage.initAccount(customerID, CreateNewAccountPage.ACCOUNTTYPE.Current, 500 );
        pageAfterCreatingAccount.windowSnip("test19_account2Details");
        account2 = pageAfterCreatingAccount.accountNumber.getText();
        System.out.println("Test 19 -Account2 Number is :" + account2);

        //transfer amount from acc1 to acc2
        MoneyTransferPage moneyTransferPage = pageAfterCostumerCreated.navigateToMoneyTransferPage();
        moneyTransferPage.transferMoney(account1, account2, "1000", "test19", MoneyTransferPage.TRANSFERTYPE.InvalidNotEnoughMoney);
    }

    @After
    public void clearForNext() {
        HomePage homepage = new HomePage(driver);
        homepage.navigateToHomePage();
        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD);

        String[] accounts = {account1, account2};
        for (String account:accounts){
            if (account != null) {
                DeleteAccountPage deleteAccountPage = welcomePage.navigateToDeleteAccountPage();
                deleteAccountPage.deleteAccount(account, DeleteAccountPage.VALIDATIONTYPE.full);;
                System.out.println("****Account no. " + account + " was deleted ****");
            }
        }
        account1 = null;
        account2 = null;

        if (customerID != null) {
            DeleteCustomerPage deleteCustomerPage = welcomePage.navigateToDeleteCustomerPage();
            welcomePage = deleteCustomerPage.deleteCustomerFullCheck(customerID, true);
            System.out.println("****Customer no. " + customerID + " was deleted ****");
            customerID = null;
        }
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