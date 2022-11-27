package testcases.user;


import com.aventstack.extentreports.Status;
import common.BaseTest;
import common.PageGeneratorManager;
import data.UserDataMapper;
import exception.BrowserNotSupport;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.norCommercePortal.UserHomePageObject;
import pageObjects.norCommercePortal.UserRegisterPageObject;
import reportConfig.ExtentTestManager;
import ultilities.Environment;

import java.lang.reflect.Method;
import java.util.Random;

public class Level22_Multiple_Environment extends BaseTest {
    WebDriver driver;

    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserDataMapper userData;
    private String emailAddress, password;
    Environment env;
    @Parameters({"envName", "servername", "browser", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("Local") String envName, @Optional("chrome") String browserName, @Optional("dev") String serverName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Window") String osName, @Optional("10") String osVersion) throws Exception {
        driver = getBrowserDriver(envName, browserName, serverName, ipAddress, portNumber, osName, osVersion);
        System.out.println(System.getProperty("envi"));
        ConfigFactory.setProperty("env",System.getProperty("envi"));
        env = ConfigFactory.create(Environment.class);
        System.out.println(env.appUrl());
        userData = UserDataMapper.getUserData();
        emailAddress = userData.getEmailAddress();

        homePage = PageGeneratorManager.getUserHomePage(driver);

    }
    @Test
    public void User_01_Register( Method method){
        ExtentTestManager.startTest(method.getName(),"Register - Step01- Navigate to Register Page");
        registerPage = homePage.clickOnSignupButton();
        ExtentTestManager.getTest().log(Status.INFO,"Register - Step02- Enter to Firstname textbox with value");
        registerPage.inputFirstName(userData.getFirstName());
        ExtentTestManager.getTest().log(Status.INFO,"Register - Step02- Enter to last textbox with value");
        registerPage.inputLastName(userData.getLastName());
        ExtentTestManager.getTest().log(Status.INFO,"Register - EMAIL");
        registerPage.inputEmail(emailAddress);
        ExtentTestManager.getTest().log(Status.INFO,"Register - PWD");
        registerPage.inputPassword(userData.getPassword());
        ExtentTestManager.getTest().log(Status.INFO,"Register -CPWD");
        registerPage.inputConfirmPassword(userData.getPassword());
        registerPage.clickOnRegisterButton();
        Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed1");
        homePage = registerPage.clickSignout();
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
