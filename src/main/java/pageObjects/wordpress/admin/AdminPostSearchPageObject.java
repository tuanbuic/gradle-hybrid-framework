package pageObjects.wordpress.admin;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.admin.AdminPostSearchPageUI;

public class AdminPostSearchPageObject extends BasePage {
    WebDriver driver;

    public AdminPostSearchPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToAddNewButton() {
        waitForElementClickable(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
        clickToElement(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
    }
}
