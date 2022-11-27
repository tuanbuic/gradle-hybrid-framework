package testcases.user;

import common.BaseTest;
import common.PageGeneratorManager;
import exception.BrowserNotSupport;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.norCommercePortal.*;

import java.util.Random;

public class Level14_LOG_ReportNG extends BaseTest {
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

    @Test
    public void TC_01_Verify_Element_Displayed() {
        log.info("Register - Step01- Navigate to Register Page");
        registerPage = homePage.clickOnSignupButton();
        log.info("Register - Step02- Enter to Firstname textbox with value: '" + firstName + "'");
        registerPage.inputFirstName(firstName);
        log.info("Register - Step03- Enter to LastName textbox with value: '" + lastName + "'");
        registerPage.inputLastName(lastName);
        log.info("Register - Step04- Enter to Email Address textbox with value: '" + emailAddress + "'");
        registerPage.inputEmail(emailAddress);
        log.info("Register - Step05- Enter to password textbox with value: '" + password + "'");
        registerPage.inputPassword(password);
        log.info("Register - Step06- Enter to confirm password textbox with value: '" + password + "'");
        registerPage.inputConfirmPassword(password);
        log.info("Register - Step07- click to 'Register' button");

        registerPage.clickOnRegisterButton();
        log.info("Register - Step08-Verify register success message is displayed");
        verifyEquals(registerPage.getSuccessMessage(), "Your registration completed......");
        log.info("Register - Step09-Click to Logout link");
        homePage = registerPage.clickSignout();
        log.info("Login - Step01-Navigate to Login button");
        loginPage = homePage.clickOnLoginButton();
        log.info("Login - Step02- Enter to emailAdress textbox with value: '" + emailAddress + "'");
        loginPage.inputEmail(emailAddress);
        log.info("Login - Step02- Enter to password textbox with value: '" + password + "'");
        loginPage.inputPassword(password);
        log.info("Login - Step03-Click on Login button");
        homePage = loginPage.clickOnLoginButton();
        log.info("Login - Step04-Verify My account menu display");
        verifyFalse(homePage.isMyAccountLinkDisplayed());
        log.info("Login - Step05- Click on My account link");
        userCustomerInfoPageObject = homePage.clickOnMyAccountLink();
        log.info("Login - Step06- Verify Customer Info page diplay");
        verifyFalse(userCustomerInfoPageObject.isCustomerInfoPageDisplay());
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
