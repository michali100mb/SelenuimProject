import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static final String USE_CHROME = "webdriver.chrome.driver";
    //public static final String USE_FIREFIX = "webdriver.gecko.driver";
    public static final String CHROME_PATH = "C:\\Users\\Michal\\Desktop\\QA\\selenium\\chromedriver.exe";
    //public static final String FIREFOX_PATH = "C:\\Users\\Michal\\Desktop\\QA\\selenium\\geckodriver.exe";
    public static final WebDriver CHROME_DRIVER = new ChromeDriver();
    //public static final WebDriver FIREFOX_DRIVER = new FirefoxDriver();
    public static final String MANAGER_USERNAME = "mngr229168";
    public static final String MANAGER_PASSWORD = "Alex1010!";
    public static final String homePageUrl = "http://www.demo.guru99.com/V4/";
    public static final String customerPassword = "Alex1010!";
    public static final Object[] createCustomerData = {
            "Virendra",
            "1",
            "04112013",
            "Jamnagar",
            "Gujarat",
            "567321",
            "8000439024",
            "Alex1010!"};




    public static void initChrome() {
        System.setProperty(Util.USE_CHROME,Util.CHROME_PATH);
    }
    public static void initFirefox() {
        System.setProperty(Util.USE_CHROME, Util.CHROME_PATH);
    }

    public static String emailGenerator() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMHHmmssYYSS");
        Date date = new Date();
        String strDate = sdf.format(date);
        return "V" + strDate + "@gmail.com";
    }

    public static String dateBefore(int daysBefore) {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYY");
        Date date = new Date(System.currentTimeMillis()-(daysBefore*24*60*60*1000));
        return sdf.format(date);
    }

    public static String nowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYY");
        Date date = new Date();
        return sdf.format(date);
    }


}

