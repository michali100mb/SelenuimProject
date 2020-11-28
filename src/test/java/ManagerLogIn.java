import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

//public class ManagerLogIn {
//    private static WebDriver driver;
//    HomePage homepage;
//    private String customerID;
//    private String account1;
//    private String account2;
//
//
//    @BeforeClass
//    public static void launchBrowser() {
//        System.out.println("launching Chrome browser");
//        System.setProperty(Util.USE_CHROME, Util.CHROME_PATH);
//        driver = Util.CHROME_DRIVER;
//    }
//
//    @Before
//    public void prepareToTest() {
//        homepage = new HomePage(driver);
//        homepage.navigateToHomePage();
//        homepage.scrollToElement();
//    }
//
//
//    //Manager login - correct
//    @Test
//    public void test1() throws IOException {
//        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD, true);
//        Assert.assertEquals(Util.MANAGER_USERNAME, welcomePage.validatePage());
//        welcomePage.scrollToElement();
//        welcomePage.windowSnip("test1");
//    }
//
//    //Manager login - Password is wrong
//    @Test
//    public void test2() {
//        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, "invalidPass", false);
//
//    }
//
//    //Manager login - Username is wrong
//    @Test
//    public void test3() {
//        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, "invalidPass", false);
//    }
//
//    //Manager login - All details are wrong
//    @Test
//    public void test4() {
//        WelcomePage welcomePage = homepage.login(Util.MANAGER_USERNAME, "invalidPass", false);
//    }

@RunWith(Parameterized.class)
public class ManagerLogIn {
    @Parameterized.Parameter(0)
    public String username; //This is the username parameter
    @Parameterized.Parameter(1)
    public String password; //This is the password parameter
    @Parameterized.Parameter(2)
    public boolean testType; //This is the username parameter
    @Parameterized.Parameter(3)
    public boolean close; //This is the username parameter

    @Parameterized.Parameters
    public static Collection<Object[]> testExecutionParameters() {
        return Arrays.asList(new Object[][]{
                {Util.MANAGER_USERNAME, Util.MANAGER_PASSWORD, true, false},
                {Util.MANAGER_USERNAME, "invalidPass", false, false},
                {"invalidUser", Util.MANAGER_PASSWORD, false, false},
                {"invalidUser", "invalidPass", false, true}
        });
    }

    @Test
    public void tests_1_2_3_4() throws IOException {
        //System.out.println("launching Chrome browser");
        System.setProperty(Util.USE_CHROME, Util.CHROME_PATH);
        WebDriver driver = Util.CHROME_DRIVER;

        HomePage homepage = new HomePage(driver);
        homepage.navigateToHomePage();
        homepage.scrollToElement();

        WelcomePage welcomePage = homepage.login(username, password, testType);
        if (testType) {
            Assert.assertEquals(username, welcomePage.validatePage());
            welcomePage.scrollToElement();
            welcomePage.windowSnip("test1");
        }
        if (close) {
            driver.quit();
        }
        System.out.println("**** System Is ready for next test ****");
    }
}

