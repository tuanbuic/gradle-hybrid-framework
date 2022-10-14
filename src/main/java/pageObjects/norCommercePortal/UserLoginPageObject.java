package pageObjects.norCommercePortal;

import common.BasePage;
import common.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.norCommerceUser.LoginPageUI;


public class UserLoginPageObject extends BasePage {
    WebDriver driver;

    public UserLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void inputEmail(String emailAddress) {
        waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver,LoginPageUI.EMAIL_TEXTBOX,emailAddress);
    }

    public UserHomePageObject clickOnLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getUserHomePage(driver);
    }

    public String getEmailErrorMessage() {
        waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
        return getElementText(driver,LoginPageUI.EMAIL_ERROR_MESSAGE);
    }

    public void inputPassword(String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver,LoginPageUI.PASSWORD_TEXTBOX,password);

    }

    public String getNotRegisteredEmailErrorMessage() {
        waitForElementVisible(driver, LoginPageUI.EMAIL_NOT_REGISTERED_ERROR_MESSAGE);
        return getElementText(driver,LoginPageUI.EMAIL_NOT_REGISTERED_ERROR_MESSAGE);
    }

    public UserHomePageObject loginAsUser(String email, String password) {
        inputEmail(email);
        inputPassword(password);
        return clickOnLoginButton();
    }
}
