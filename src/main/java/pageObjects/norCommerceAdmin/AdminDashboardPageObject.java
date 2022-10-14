package pageObjects.norCommerceAdmin;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.norCommerceAdmin.AdminDashboardPageUI;

public class AdminDashboardPageObject extends BasePage {
    WebDriver driver;

    public AdminDashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDashboardDisplay(){
        return isElementDisplay(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
    }


}
