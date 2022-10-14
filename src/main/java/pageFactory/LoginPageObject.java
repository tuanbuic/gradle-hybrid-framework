package pageFactory;

import common.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject extends BasePageFactory {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(how= How.XPATH,using = "//input[@id='Email']")
    private WebElement emailTextbox;

    @FindBy(how= How.XPATH,using = "//input[@id='Password']")
    private WebElement passwordTextbox;

    @FindBy(how= How.XPATH,using = "//div[@class='message-error validation-summary-errors']")
    private WebElement emailNotRegisteredMessage;

    @FindBy(how= How.XPATH,using = "//span[@class='field-validation-error']")
    private WebElement emailErrorMessage;

    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    private WebElement loginLink;

    public void inputEmail(String emailAddress) {
        waitForElementVisible(driver, emailTextbox);
        sendkeyToElement(driver,emailTextbox,emailAddress);
    }

    public void clickOnLoginButton() {
        waitForElementClickable(driver, loginLink);
        clickToElement(driver, loginLink);
    }

    public String getEmailErrorMessage() {
        waitForElementVisible(driver, emailErrorMessage);
        return getElementText(driver,emailErrorMessage);
    }

    public void inputPassword(String password) {
        waitForElementVisible(driver,passwordTextbox);
        sendkeyToElement(driver,passwordTextbox,password);

    }

    public String getNotRegisteredEmailErrorMessage() {
        waitForElementVisible(driver, emailNotRegisteredMessage);
        return getElementText(driver,emailNotRegisteredMessage);
    }
}
