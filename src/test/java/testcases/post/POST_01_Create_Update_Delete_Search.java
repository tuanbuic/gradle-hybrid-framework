package testcases.post;

import common.BaseTest;
import exception.BrowserNotSupport;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.wordpress.admin.*;

public class POST_01_Create_Update_Delete_Search extends BaseTest {
    WebDriver driver;
    LoginPageObject loginPage;
    AdminDashboardPageObject adminDashboardPage;
    AdminPostSearchPageObject adminPostSearchPage;
    AdminPostAddNewPO adminPostAddNewPage;
    String searchPostUrl;
    String adminUsername="automationfc";
    String adminPassword="automationfc";
    String postTitleValue = "";
    String postBodyValue="";


    @BeforeTest
    @Parameters({"browser", "urlAdmin"})
    public void Register(String browserName, String urlAdmin) throws BrowserNotSupport {
        driver = getBrowserDriver(browserName, urlAdmin);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.enterUsernameTextbox(adminUsername);
        loginPage.enterPasswordTextbox(adminPassword);
        loginPage.clickToLoginButton();
        adminDashboardPage = PageGeneratorManager.getDashboardPage(driver);

    }

    @Test
    public void create_Post() {

        adminDashboardPage.clickToPostMenuLink();
        adminPostSearchPage = PageGeneratorManager.getAdminPostSearchPage(driver);
        searchPostUrl=adminPostSearchPage.getPageURL(driver);
        adminPostSearchPage.clickToAddNewButton();
        adminPostAddNewPage = PageGeneratorManager.getPostAddNewPage(driver);
        adminPostAddNewPage.enterToAddNewPostTitle(postTitleValue);
        adminPostAddNewPage.enterToAddNewPostBody(postBodyValue);
        adminPostAddNewPage.clickToPublishButton();
        adminPostAddNewPage.isPostPublishMessageDisplayed();


    }

    @Test
    public void Search_Post() {

    }

    @Test
    public void View_Post() {

    }

    @Test
    public void Edit_Post() {

    }

    @Test
    public void Delete_Post() {

    }

    @Test
    public void Search_05() {

    }

    @Test
    public void Search_06() {

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }

}
