package testcases.user;

import common.BaseTest;
import common.PageGeneratorManager;
import exception.BrowserNotSupport;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.norCommercePortal.UserHomePageObject;
import pageObjects.norCommercePortal.UserRegisterPageObject;

import java.util.Random;

public class Level17_Custom_Close_Driver extends BaseTest {
    WebDriver driver;

    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private String firstName, lastName;
    public static String emailAddress, password;


    @BeforeTest
    @Parameters({"browser", "environment"})
    public void Register(String browserName, String environment) throws BrowserNotSupport {
        emailAddress = generatefakeEmailAddress("kevinbui", "yopmail.com");
        firstName = "Automation";
        lastName = "FC";
        password = "123456";
        driver = getBrowserDriver(browserName);
        driver.get(getEnvironmentURL(environment));
        homePage = PageGeneratorManager.getUserHomePage(driver);
        registerPage = homePage.clickOnSignupButton();
        registerPage.inputFirstName(firstName);
        registerPage.inputLastName(lastName);
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
    public void afterClass(){
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
