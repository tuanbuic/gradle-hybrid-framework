package testcases.user;


import com.aventstack.extentreports.Status;
import common.BaseTest;
import common.PageGeneratorManager;
import data.UserDataMapper;
import exception.BrowserNotSupport;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.norCommercePortal.UserHomePageObject;
import pageObjects.norCommercePortal.UserRegisterPageObject;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;
import java.util.Random;

public class Level20_External_Data extends BaseTest {
    WebDriver driver;

    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserDataMapper userData;
    private String emailAddress, password;

    @BeforeTest
    @Parameters({"browser", "environment"})
    public void Register(String browserName, String environment) throws BrowserNotSupport {
        userData = UserDataMapper.getUserData();
        emailAddress = userData.getEmailAddress();

        driver = getBrowserDriver(browserName);
        driver.get(getEnvironmentURL(environment));
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
