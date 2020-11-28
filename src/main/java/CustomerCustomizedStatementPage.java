import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerCustomizedStatementPage extends CustomerPageWithSideBar {
    public static enum TESTTYPE {Valid, Invalid_WrongAccount, Invalid_DaysRangeMinus}
    @FindBy(name = "accountno")
    WebElement cAccountNoBox;

    @FindBy(name = "fdate")
    WebElement cFromDateBox;

    @FindBy(name = "tdate")
    WebElement cToDateBox;

    @FindBy(name = "amountlowerlimit")
    WebElement cMinAmountBox;

    @FindBy(name = "numtransaction")
    WebElement cTransactionNoBox;

    @FindBy(name = "AccSubmit")
    WebElement cSubmitButton;


    CustomerCustomizedStatementPage() {
        super();
    }

    CustomerCustomizedStatementPage(WebDriver driver) {
        super(driver);
    }

    public CustomerPageAfterCustomizedStatement getCustomStatement(String accountNo,
                                                                   String fromDate,
                                                                   String toDate,
                                                                   String startFromAmount,
                                                                   String transactionsToShow,
                                                                   CustomerCustomizedStatementPage.TESTTYPE testType){
        cAccountNoBox.sendKeys(accountNo);
        cFromDateBox.sendKeys(fromDate);
        cToDateBox.sendKeys(toDate);
        cMinAmountBox.sendKeys(startFromAmount);
        cTransactionNoBox.sendKeys(transactionsToShow);
        cSubmitButton.click();
        if (testType == TESTTYPE.Invalid_WrongAccount) {
            Assert.assertEquals("Account does not exist", getAlertText());
            acceptAlert();
            return null;
        }
        if (testType == TESTTYPE.Invalid_DaysRangeMinus) {
            Assert.assertEquals("FromDate field should be lower than ToDate field!!", getAlertText());
            acceptAlert();
            return null;
        }
        return new CustomerPageAfterCustomizedStatement(driver);
    }
}
