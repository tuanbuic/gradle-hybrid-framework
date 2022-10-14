package pageObjects.wordpress.admin;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.admin.AdminDashboardPageUI;

public class AdminDashboardPageObject extends BasePage {
    WebDriver driver;

    public AdminDashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }


    public void clickToPostMenuLink() {
        waitForElementClickable(driver, AdminDashboardPageUI.POST_MENU_LINK);
        clickToElement(driver, AdminDashboardPageUI.POST_MENU_LINK);
    }
}
