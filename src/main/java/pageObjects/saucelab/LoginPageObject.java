package pageObjects.saucelab;


import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.saucelab.LoginPageUI;

public class LoginPageObject extends BasePage {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void inputEmail(String emailAddress) {
        waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver,LoginPageUI.EMAIL_TEXTBOX,emailAddress);
    }

    public void inputPassword(String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver,LoginPageUI.PASSWORD_TEXTBOX,password);
    }
    public ProductPageObject clickLogin() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver,LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getProDuctPage(driver);
    }

}
