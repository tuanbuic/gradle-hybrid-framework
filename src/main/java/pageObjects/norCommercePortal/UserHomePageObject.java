package pageObjects.norCommercePortal;


import common.BasePage;
import common.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.norCommerceUser.HomePageUI;

public class UserHomePageObject extends BasePage {
    private WebDriver driver;

    public UserHomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public UserRegisterPageObject clickOnSignupButton() {
        waitForElementClickable(driver, HomePageUI.SIGNUP_BUTTON);
        clickToElement(driver, HomePageUI.SIGNUP_BUTTON);
        return PageGeneratorManager.getUserRegisterPage(driver);
    }
    public UserLoginPageObject clickOnLoginButton(){
        waitForElementClickable(driver, HomePageUI.LOGIN_BUTTON);
        clickToElement(driver, HomePageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getUserLoginPage(driver);
    }

    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver,HomePageUI.MY_ACCOUNT_LINK);
        return isElementDisplay(driver,HomePageUI.MY_ACCOUNT_LINK);
    }

    public UserCustomerInfoPageObject clickOnMyAccountLink() {
        isMyAccountLinkDisplayed();
        clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
        return PageGeneratorManager.getUserMyAccountPage(driver);
    }
}
