package pageObjects.norCommercePortal;


import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.norCommerceUser.CustomerInfoPageUI;

public class UserCustomerInfoPageObject extends BasePage {
    WebDriver driver;
    public UserCustomerInfoPageObject(WebDriver driver) {
        this.driver = driver;
    }


    public boolean isCustomerInfoPageDisplay() {
        waitForElementVisible(driver, CustomerInfoPageUI.MY_ACCOUNT_TITLE);
        return isElementDisplay(driver, CustomerInfoPageUI.MY_ACCOUNT_TITLE);
    }
}
