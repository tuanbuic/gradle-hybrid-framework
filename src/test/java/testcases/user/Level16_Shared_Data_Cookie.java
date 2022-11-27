package testcases.user;

import common.BaseTest;
import common.PageGeneratorManager;
import exception.BrowserNotSupport;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.norCommercePortal.UserHomePageObject;
import pageObjects.norCommercePortal.UserLoginPageObject;
import testcases.common.Common01_Register_Cookie;

import java.util.Random;

public class Level16_Shared_Data_Cookie extends BaseTest {
    WebDriver driver;
    String emailAddress;
    String password;
    private UserHomePageObject homePage;
    private UserLoginPageObject loginPage;

    @Parameters({"envName", "servername", "browser", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("Local") String envName, @Optional("chrome") String browserName, @Optional("dev") String serverName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Window") String osName, @Optional("10") String osVersion) throws Exception {
        driver = getBrowserDriver(envName, browserName, serverName, ipAddress, portNumber, osName, osVersion);
        homePage = PageGeneratorManager.getUserHomePage(driver);
//        emailAddress = Common01_Register_Account.emailAddress;
//        password = Common01_Register_Account.password;


        loginPage = homePage.clickOnLoginButton();
        loginPage.setCookie(driver, Common01_Register_Cookie.loggedCookie);
        loginPage.refreshCurrentPage(driver);
        verifyTrue(homePage.isMyAccountLinkDisplayed());
//        loginPage.inputEmail(emailAddress);
//        loginPage.inputPassword(password);
//        homePage = loginPage.clickOnLoginButton();
    }

    @Test
    public void Search_01() {
    }

    @Test
    public void Search_02() {

    }
    @Test
    public void Search_03() {

    }
    @Test
    public void Search_04() {

    }
    @Test
    public void Search_05() {

    }
    @Test
    public void Search_06() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int generateFakeNumber() {
        Random rnd = new Random();
        return rnd.nextInt(9999);
    }

    public String generatefakeEmailAddress(String emailprefix, String webmail) {
        return emailprefix + generateFakeNumber() + "@" + webmail;
    }
}
