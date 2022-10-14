package pageFactory;

import common.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageObject extends BasePageFactory {
    private WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//button[@id='register-button']")
    private WebElement registerButton;

    @FindBy(how = How.XPATH, using = "//span[@id='FirstName-error']")
    private WebElement firstNameError;
    @FindBy(xpath = "//span[@id='LastName-error']")
    private WebElement lastNameError;
    @FindBy(xpath = "//span[@id='Email-error']")
    private WebElement emailError;
    @FindBy(xpath = "//span[@id='Password-error']")
    private WebElement passwordError;
    @FindBy(xpath = "//input[@id='FirstName']")
    private WebElement firstNameTextBox;
    @FindBy(xpath = "//input[@id='LastName']")
    private WebElement lastNameTextBox;
    @FindBy(xpath = "//input[@id='Email']")
    private WebElement emailTextBox;
    @FindBy(xpath = "//input[@id='Password']")
    private WebElement passwordTextBox;
    @FindBy(xpath = "//input[@id='ConfirmPassword']")
    private WebElement confirmPasswordTextbox;
    @FindBy(xpath = "//div[@class='result']")
    private WebElement resultMessage;
    @FindBy(xpath = "//a[@class='ico-logout']")
    private WebElement signoutButton;
    @FindBy(xpath = "div.message-error")
    private WebElement failedMessage;


    public void clickOnRegisterButton() {
        waitForElementClickable(driver, registerButton);
        clickToElement(driver, registerButton);
    }

    public String getFirstNameErrorMessage() {
        return getElementText(driver, firstNameError);
    }

    public String getLastNameErrorMessage() {
        return getElementText(driver, lastNameError);
    }

    public String getEmailErrorMessage() {
        return getElementText(driver, emailError);
    }

    public String getPasswordErrorMessage() {
        return getElementText(driver, passwordError);
    }

    public void inputFirstName(String text) {
        sendkeyToElement(driver, firstNameTextBox, text);
    }

    public void inputLastName(String text) {
        sendkeyToElement(driver, lastNameTextBox, text);
    }

    public void inputEmail(String email) {
        sendkeyToElement(driver, emailTextBox, email);
    }

    public void inputPassword(String s) {
        sendkeyToElement(driver, passwordTextBox, s);
    }

    public void inputConfirmPassword(String s) {
        sendkeyToElement(driver, confirmPasswordTextbox, s);
    }

    public String getSuccessMessage() {
        return getElementText(driver, resultMessage);
    }

    public void clickSignout() {
        waitForElementClickable(driver, signoutButton);
        clickToElement(driver, signoutButton);
    }

    public String getFailedMessage() {
        return getElementText(driver, failedMessage);
    }
}
