import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class CustomerChangePasswordPage extends CustomerPageWithSideBar {
    @FindBy(name = "oldpassword")
    WebElement cOldPasswordBox;

    @FindBy(name = "newpassword")
    WebElement cNewPasswordBox;

    @FindBy(name = "confirmpassword")
    WebElement cConfirmNewPasswordBox;

    @FindBy(name = "sub")
    WebElement cSubmitButton;


    CustomerChangePasswordPage() {
        super();
    }

    CustomerChangePasswordPage(WebDriver driver) {
        super(driver);
    }

    public void changePassword(String oldPassword, String newPassword, String snapShotName , boolean valid) throws IOException {
        scrollToElement();
        cOldPasswordBox.sendKeys(oldPassword);
        cNewPasswordBox.sendKeys(newPassword);
        cConfirmNewPasswordBox.sendKeys(newPassword);
        windowSnip(snapShotName);
        cSubmitButton.click();
        if (!valid) {
            Assert.assertEquals("Old Password is incorrect", getAlertText());
            acceptAlert();
        }
    }
}
