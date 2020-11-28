import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShot {
    public static final String screenShotsPath = "C:\\Users\\Michal\\Desktop\\QA\\selenium\\ScreenShots";

    public static void takeSnapShot(WebDriver webDriver, String testName) throws IOException {
        String path = screenShotsPath+ "\\" + StringDateTime(testName)+".jpg";
        TakesScreenshot screenShot = ((TakesScreenshot)webDriver);
        File SrcFile = screenShot.getScreenshotAs(OutputType.FILE);
        File DestinationFile = new File(path);
        FileUtils.copyFile(SrcFile, DestinationFile);
    }

    private static String StringDateTime(String testName) {
        SimpleDateFormat format = new SimpleDateFormat("ddMMMMyy_HHmm");
        return testName + "_" + format.format(new Date());
    }
}
