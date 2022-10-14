package pageObjects.norCommercePortal;


import common.BasePage;
import common.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.Test;
import pageUIs.norCommerceUser.RegisterPageUI;

public class UserRegisterPageObject extends BasePage {
    private WebDriver driver;

    public UserRegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }
    @Step("click on register button")
    public void clickOnRegisterButton() {
        waitForElementClickable(driver, Test.REGISTER_BUTTON);
        clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
    }

    public String getFirstNameErrorMessage() {
        return getElementText(driver, RegisterPageUI.REGISTER_FIRSTNAME_ERROR);

    }

    public String getLastNameErrorMessage() {
        return getElementText(driver, RegisterPageUI.REGISTER_LASTNAME_ERROR);

    }

    public String getEmailErrorMessage() {
        return getElementText(driver, RegisterPageUI.REGISTER_EMAIL_ERROR);
    }

    public String getPasswordErrorMessage() {
        return getElementText(driver, RegisterPageUI.REGISTER_PASSWORD_ERROR);
    }
    @Step("Enter first name textbox with value : {0}")
    public void inputFirstName(String text) {
        sendkeyToElement(driver, RegisterPageUI.FIRSTNAME_TEXTBOX, text);
    }
    @Step("Enter last name textbox with value : {0}")
    public void inputLastName(String text) {
        sendkeyToElement(driver, RegisterPageUI.LASTNAME_TEXTBOX, text);
    }
    @Step("Enter email textbox with value : {0}")
    public void inputEmail(String email) {
        sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, email);
    }
    @Step("input password {0}")
    public void inputPassword(String s) {
        sendkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, s);
    }
    @Step("input confirm password {0}")
    public void inputConfirmPassword(String s) {
        sendkeyToElement(driver, RegisterPageUI.CONFIRMPASSWORD_TEXTBOX, s);
    }
    @Step("get success message")
    public String getSuccessMessage() {
        return getElementText(driver, RegisterPageUI.RESULT_MESSAGE);
    }
    @Step("click sign out")
    public UserHomePageObject clickSignout() {
        waitForElementClickable(driver, RegisterPageUI.SIGNOUT_BUTTON);
        clickToElement(driver, RegisterPageUI.SIGNOUT_BUTTON);
        return PageGeneratorManager.getUserHomePage(driver);
    }

    public String getFailedMessage() {
        return getElementText(driver, RegisterPageUI.ERROR_MESSAGE);
    }
}
