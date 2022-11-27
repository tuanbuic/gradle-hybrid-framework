package testcases.post;

import common.BaseTest;
import exception.BrowserNotSupport;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
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


    @Parameters({"envName", "servername", "browser", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("Local") String envName, @Optional("chrome") String browserName, @Optional("dev") String serverName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Window") String osName, @Optional("10") String osVersion) throws Exception {
        driver = getBrowserDriver(envName, browserName, serverName, ipAddress, portNumber, osName, osVersion);
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
