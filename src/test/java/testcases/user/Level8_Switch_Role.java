package testcases.user;

import common.BaseTest;
import common.GlobalConstants;
import common.PageGeneratorManager;
import exception.BrowserNotSupport;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.norCommerceAdmin.AdminDashboardPageObject;
import pageObjects.norCommerceAdmin.AdminLoginPageObject;
import pageObjects.norCommercePortal.*;

import java.util.Random;

public class Level8_Switch_Role extends BaseTest {
    WebDriver driver;

    String emailAddress;
    private UserHomePageObject userHomePageObject;
    private UserRegisterPageObject userRegisterPageObject;
    private UserLoginPageObject userLoginPageObjectginPage;
    private UserCustomerInfoPageObject userCustomerInfoPageObject;
    private UserMyProductReviewPageObject myProductReviewPage;
    private UserRewardPointPageObject userRewardPointPageObject;
    private UserAddressPageObject userAddressPageObject;
    private AdminLoginPageObject adminLoginPageObject;
    private AdminDashboardPageObject adminDashboardPageObject;
    private String firstName;
    private String lastName;
    private String password;
    String adminEmailAddress;
    String adminPassword;

    public Level8_Switch_Role() {
    }

    @Parameters({"envName", "servername", "browser", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("Local") String envName, @Optional("chrome") String browserName, @Optional("dev") String serverName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Window") String osName, @Optional("10") String osVersion) throws Exception {
        driver = getBrowserDriver(envName, browserName, serverName, ipAddress, portNumber, osName, osVersion);
        userHomePageObject = PageGeneratorManager.getUserHomePage(driver);
        emailAddress = generatefakeEmailAddress("kevinbui", "yopmail.com");
        firstName = "Automation";
        lastName = "FC";
        password = "123456";
         adminEmailAddress = "admin@yourstore.com";
         adminPassword = "admin";

        driver.manage().window().maximize();
        driver.get(GlobalConstants.PORTAL_PAGE_URL);
    }

    @Test
    public void TC_01_Login_Empty_Data() {
        userRegisterPageObject = userHomePageObject.clickOnSignupButton();
        userRegisterPageObject.inputFirstName(firstName);
        userRegisterPageObject.inputLastName(lastName);
        userRegisterPageObject.inputEmail(emailAddress);
        userRegisterPageObject.inputPassword(password);
        userRegisterPageObject.inputConfirmPassword(password);
        userRegisterPageObject.clickOnRegisterButton();
        userRegisterPageObject.getSuccessMessage();
        userHomePageObject = userRegisterPageObject.clickSignout();
        System.out.println(emailAddress);


    }

    @Test
    public void TC_02_Login_User () {
        userLoginPageObjectginPage = userHomePageObject.clickOnLoginButton();
       userHomePageObject = userLoginPageObjectginPage.loginAsUser(emailAddress,password);
    }

    @Test
    public void TC_03_Admin() {
        userHomePageObject.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
        adminLoginPageObject = PageGeneratorManager.getAdminLoginPageObject(driver);
       adminDashboardPageObject = adminLoginPageObject.loginAsAdmin(adminEmailAddress,adminPassword);

       adminDashboardPageObject.isDashboardDisplay();
    }

    @Test
    public void TC_05_Switch_Role() {


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
