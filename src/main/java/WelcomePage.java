import org.openqa.selenium.WebDriver;

public class WelcomePage extends PageWithSideBar{
    WelcomePage() {
        super();
        scrollToElement();
    }

    WelcomePage(WebDriver driver) {
        super(driver);
        scrollToElement();
    }

    public String validatePage() {
        String[] output = userNameBox.getText().trim().split(" ");
        return output[9];
    }

    public void scrollToElement() {
        super.scrollToElementImpl(userNameBox, driver);
    }
}
