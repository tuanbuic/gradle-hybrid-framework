package testcases.user;

import common.BaseTest;
import common.PageGeneratorManager;
import exception.BrowserNotSupport;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.norCommercePortal.*;

import java.util.Random;

public class Level15_Allure extends BaseTest {
    WebDriver driver;
    String emailAddress;
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserLoginPageObject loginPage;
    private UserCustomerInfoPageObject userCustomerInfoPageObject;
    private UserMyProductReviewPageObject myProductReviewPage;
    private UserRewardPointPageObject rewardPointPage;
    private UserAddressPageObject addressPage;
    private String firstName;
    private String lastName;
    private String password;
    static String adminEmailAddress;
    String adminPassword;

    @Parameters({"envName", "servername", "browser", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("Local") String envName, @Optional("chrome") String browserName, @Optional("dev") String serverName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Window") String osName, @Optional("10") String osVersion) throws Exception {
        driver = getBrowserDriver(envName, browserName, serverName, ipAddress, portNumber, osName, osVersion);
        emailAddress = generatefakeEmailAddress("kevinbui", "yopmail.com");
        firstName = "Automation";
        lastName = "FC";
        password = "123456";
        adminEmailAddress = "admin@yourstore.com";
        adminPassword = "admin";
        driver.manage().window().maximize();
        homePage = PageGeneratorManager.getUserHomePage(driver);
    }
    @Description("Register to system")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_01_Register() {
        registerPage = homePage.clickOnSignupButton();
        registerPage.inputFirstName(firstName);
        registerPage.inputLastName(lastName);
        registerPage.inputEmail(emailAddress);
        registerPage.inputPassword(password);
        registerPage.inputConfirmPassword(password);
        registerPage.clickOnRegisterButton();
        Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed...");
        homePage = registerPage.clickSignout();
        loginPage = homePage.clickOnLoginButton();
        loginPage.inputEmail(emailAddress);
        loginPage.inputPassword(password);
        homePage = loginPage.clickOnLoginButton();
        Assert.assertFalse(homePage.isMyAccountLinkDisplayed());
        userCustomerInfoPageObject = homePage.clickOnMyAccountLink();
        Assert.assertFalse(userCustomerInfoPageObject.isCustomerInfoPageDisplay());
    }
    @Description("Login to system")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_02_Login() {
        homePage = registerPage.clickSignout();
        loginPage = homePage.clickOnLoginButton();
        loginPage.inputEmail(emailAddress);
        loginPage.inputPassword(password);
        homePage = loginPage.clickOnLoginButton();
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
        userCustomerInfoPageObject = homePage.clickOnMyAccountLink();
        Assert.assertTrue(userCustomerInfoPageObject.isCustomerInfoPageDisplay());
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }

    public int generateFakeNumber() {
        Random rnd = new Random();
        return rnd.nextInt(9999);
    }

    public String generatefakeEmailAddress(String emailprefix, String webmail) {
        return emailprefix + generateFakeNumber() + "@" + webmail;
    }
}
