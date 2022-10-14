package pageObjects.norCommerceAdmin;


import common.BasePage;
import common.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.norCommerceAdmin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage {
    WebDriver driver;

    public AdminLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void inputEmail(String emailAddress) {
        waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX,emailAddress);
    }

    public void inputPassword(String password) {
        waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX,password);

    }
    public AdminDashboardPageObject clickToLoginButton(){
        waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getAdminDashboardPageObject(driver);
    }
    public AdminDashboardPageObject loginAsAdmin(String email, String password) {
        inputEmail(email);
        inputPassword(password);
        return clickToLoginButton();
    }

}
