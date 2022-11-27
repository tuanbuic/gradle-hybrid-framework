package testcases.user;

import com.aventstack.extentreports.Status;
import common.BaseTest;
import common.PageGeneratorManager;
import exception.BrowserNotSupport;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.norCommercePortal.*;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;
import java.util.Random;

public class Level15_Extent_Report_V5 extends BaseTest {
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
    public void TC_01_Verify_Element_Displayed(Method method) {
        ExtentTestManager.startTest(method.getName(),"Register - Step01- Navigate to Register Page");
        registerPage = homePage.clickOnSignupButton();
        ExtentTestManager.getTest().log(Status.INFO,"Register - Step02- Enter to Firstname textbox with value: '" + firstName + "'");
        registerPage.inputFirstName(firstName);
        ExtentTestManager.getTest().log(Status.INFO,"Register - Step03- Enter to LastName textbox with value: '" + lastName + "'");
        registerPage.inputLastName(lastName);
        ExtentTestManager.getTest().log(Status.INFO,"Register - Step04- Enter to Email Address textbox with value: '" + emailAddress + "'");
        registerPage.inputEmail(emailAddress);
        ExtentTestManager.getTest().log(Status.INFO,"Register - Step05- Enter to password textbox with value: '" + password + "'");
        registerPage.inputPassword(password);
        ExtentTestManager.getTest().log(Status.INFO,"Register - Step06- Enter to confirm password textbox with value: '" + password + "'");
        registerPage.inputConfirmPassword(password);
        ExtentTestManager.getTest().log(Status.INFO,"Register - Step07- click to 'Register' button");

        registerPage.clickOnRegisterButton();
        ExtentTestManager.getTest().log(Status.INFO,"Register - Step08-Verify register success message is displayed");
        Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed......");
        ExtentTestManager.getTest().log(Status.INFO,"Register - Step09-Click to Logout link");
        homePage = registerPage.clickSignout();
        ExtentTestManager.getTest().log(Status.INFO,"Login - Step01-Navigate to Login button");
        loginPage = homePage.clickOnLoginButton();
        ExtentTestManager.getTest().log(Status.INFO,"Login - Step02- Enter to emailAdress textbox with value: '" + emailAddress + "'");
        loginPage.inputEmail(emailAddress);
        ExtentTestManager.getTest().log(Status.INFO,"Login - Step02- Enter to password textbox with value: '" + password + "'");
        loginPage.inputPassword(password);
        ExtentTestManager.getTest().log(Status.INFO,"Login - Step03-Click on Login button");
        homePage = loginPage.clickOnLoginButton();
        ExtentTestManager.getTest().log(Status.INFO,"Login - Step04-Verify My account menu display");
        Assert.assertFalse(homePage.isMyAccountLinkDisplayed());
        ExtentTestManager.getTest().log(Status.INFO,"Login - Step05- Click on My account link");
        userCustomerInfoPageObject = homePage.clickOnMyAccountLink();
        ExtentTestManager.getTest().log(Status.INFO,"Login - Step06- Verify Customer Info page diplay");
        Assert.assertFalse(userCustomerInfoPageObject.isCustomerInfoPageDisplay());
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
