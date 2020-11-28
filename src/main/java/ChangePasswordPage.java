import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class ChangePasswordPage extends PageWithSideBar {
    public String alertPassChanged = "Password is Changed";
    public String alertOldPassWrong = "Old Password is incorrect";
    @FindBy(css = "[name='oldpassword']")
    WebElement oldPasswordBox;

    @FindBy(css = "[name='newpassword']")
    WebElement newPasswordBox;

    @FindBy(css = "[name='confirmpassword']")
    WebElement confirmNewPasswordBox;

    @FindBy(css = "[type='submit']")
    WebElement submitButton;

    @FindBy(css = "[type='reset']")
    WebElement resetButton;

    ChangePasswordPage() {
        super();
    }

    ChangePasswordPage(WebDriver driver) {
        super(driver);
    }

    public void scrollToElement(){
        super.scrollToElementImpl(confirmNewPasswordBox, driver);
    }

    public void changePassword(String oldPassword, String newPassword, String snapShotName) throws IOException {
        scrollToElement();
        oldPasswordBox.sendKeys(oldPassword);
        newPasswordBox.sendKeys(newPassword);
        confirmNewPasswordBox.sendKeys(newPassword);
        windowSnip(snapShotName);
        submitButton.click();
    }
}
