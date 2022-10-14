package pageObjects.norCommercePortal;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.norCommerceUser.FacebookUI;

public class FacebookPage extends BasePage {
    WebDriver driver;

    public FacebookPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToSignUp() {
        waitForElementClickable(driver, FacebookUI.SIGNUP_BUTTON);
        clickToElement(driver, FacebookUI.SIGNUP_BUTTON);
    }

    public boolean isEmailAddressTextBoxDisplay() {
        waitForAllElementVisible(driver, FacebookUI.EMAILADDRESS_TEXTBOX);
        return isElementDisplay(driver, FacebookUI.EMAILADDRESS_TEXTBOX);
    }

    public void enterEmailAddress(String text) {
        waitForAllElementVisible(driver, FacebookUI.EMAILADDRESS_TEXTBOX);
        sendkeyToElement(driver, FacebookUI.EMAILADDRESS_TEXTBOX, text);
    }

    public boolean isConfirmEmailAddressTextboxDisplayed() {
        return isElementDisplay(driver, FacebookUI.EMAILADDRESS_CONFIRM_TEXTBOX);
    }

    public boolean isConfirmEmailAddressTextboxUnDisplayed() {
        return isElementUndisplayed(driver, FacebookUI.EMAILADDRESS_CONFIRM_TEXTBOX);
    }

    public void clickOnClosePopUp() {
        waitForElementClickable(driver, FacebookUI.CLOSE_IMG);
        clickToElement(driver, FacebookUI.CLOSE_IMG);

    }
}



