package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    public static LoginPageObject getLoginPage(WebDriver driver) {
        return new LoginPageObject(driver);
    }

    public static AdminDashboardPageObject getDashboardPage(WebDriver driver) {
        return new AdminDashboardPageObject(driver);
    }

    public static AdminPostSearchPageObject getAdminPostSearchPage(WebDriver driver) {
        return new AdminPostSearchPageObject(driver);
    }

    public static AdminPostAddNewPO getPostAddNewPage(WebDriver driver) {
        return new AdminPostAddNewPO(driver);
    }
}
