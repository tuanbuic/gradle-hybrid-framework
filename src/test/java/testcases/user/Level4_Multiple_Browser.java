package testcases.user;

import common.BaseTest;
import exception.BrowserNotSupport;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.norCommercePortal.UserHomePageObject;
import pageObjects.norCommercePortal.UserLoginPageObject;
import pageObjects.norCommercePortal.UserRegisterPageObject;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level4_Multiple_Browser extends BaseTest {
    WebDriver driver;

    String emailAddress;
    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserLoginPageObject loginPage;
    private String firstName;
    private String lastName;
    private String password;

    @Parameters({"envName", "browser", "serverName", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("local") String envName, @Optional("chrome") String browserName, @Optional("dev") String serverName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Window") String osName, @Optional("10") String osVersion) throws Exception {
        driver = getBrowserDriver(envName, browserName, serverName, ipAddress, portNumber, osName, osVersion);

        emailAddress = generatefakeEmailAddress("kevinbui", "yopmail.com");
        firstName = "Automation";
        lastName = "FC";
        password = "123456";
        registerPage = new UserRegisterPageObject(driver);
        homePage = new UserHomePageObject(driver);
        loginPage = new UserLoginPageObject(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
        homePage.clickOnSignupButton();
        registerPage.inputFirstName(firstName);
        registerPage.inputLastName(lastName);
        registerPage.inputEmail(emailAddress);
        registerPage.inputPassword(password);
        registerPage.inputConfirmPassword(password);
        registerPage.clickOnRegisterButton();
        registerPage.getSuccessMessage();
        registerPage.clickSignout();


    }

    @Test
    public void TC_01_Login_Empty_Data() {
        homePage.clickOnLoginButton();
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.getEmailErrorMessage(), "Please enter your email");


    }

    @Test
    public void TC_02_Login_Invalid_Data() {
        String invalidEmail = "Abcde";
        homePage.clickOnLoginButton();
        loginPage.inputEmail(invalidEmail);
        loginPage.inputPassword(password);
    }

    @Test
    public void TC_03_Login_NotRegistered_Data() {
        String notRegistered = "nonexistemail@gmail.com";
        homePage.clickOnLoginButton();
        loginPage.inputEmail(notRegistered);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.getNotRegisteredEmailErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");

    }

    @Test
    public void TC_04_Login_Existing_Email_Empty_Password() {
        homePage.clickOnLoginButton();
        loginPage.inputEmail(emailAddress);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.getNotRegisteredEmailErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");


    }

    @Test
    public void TC_05_Login_Existing_Email_Incorrect_Password() {
        String wrongPassword = "123123";
        homePage.clickOnLoginButton();
        loginPage.inputEmail(emailAddress);
        loginPage.inputPassword(wrongPassword);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.getNotRegisteredEmailErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

    }

    @Test
    public void TC_06_Login_successfully_Data() {
        homePage.clickOnLoginButton();
        loginPage.inputEmail(emailAddress);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        homePage = new UserHomePageObject(driver);
        homePage.isMyAccountLinkDisplayed();
        homePage.clickOnMyAccountLink();

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
