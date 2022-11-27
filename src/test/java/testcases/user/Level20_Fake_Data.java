package testcases.user;

import common.BaseTest;
import common.PageGeneratorManager;
import exception.BrowserNotSupport;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.norCommercePortal.UserHomePageObject;
import pageObjects.norCommercePortal.UserRegisterPageObject;
import ultilities.DataHelper;

import java.util.Random;

public class Level20_Fake_Data extends BaseTest {
    WebDriver driver;

    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private DataHelper dataHelper;
    private String emailAddress, password;

    @Parameters({"envName", "servername", "browser", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("Local") String envName, @Optional("chrome") String browserName, @Optional("dev") String serverName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Window") String osName, @Optional("10") String osVersion) throws Exception {
        driver = getBrowserDriver(envName, browserName, serverName, ipAddress, portNumber, osName, osVersion);
        dataHelper = DataHelper.getDataHelper();
        emailAddress = dataHelper.getEmailAddress();
        password = dataHelper.getPassword();
        homePage = PageGeneratorManager.getUserHomePage(driver);
        registerPage = homePage.clickOnSignupButton();
        registerPage.inputFirstName(dataHelper.getFirstName());
        registerPage.inputLastName(dataHelper.getLastName());
        registerPage.inputEmail(emailAddress);
        registerPage.inputPassword(password);
        registerPage.inputConfirmPassword(password);
        registerPage.clickOnRegisterButton();
        Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
        homePage = registerPage.clickSignout();
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

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }

    public int generateFakeNumber() {
        Random rnd = new Random();
        return rnd.nextInt(9999);
    }

    public String generatefakeEmailAddress(String emailprefix, String webmail) {
        return emailprefix + generateFakeNumber() + "@" + webmail;
    }
}
