package testcases.common;


import common.BaseTest;
import common.PageGeneratorManager;
import exception.BrowserNotSupport;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageObjects.norCommercePortal.UserHomePageObject;
import pageObjects.norCommercePortal.UserLoginPageObject;
import pageObjects.norCommercePortal.UserRegisterPageObject;

import java.util.Random;
import java.util.Set;

public class Common01_Register_Cookie extends BaseTest {
    WebDriver driver;

    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserLoginPageObject loginPage;
    private String firstName, lastName;
    public static String emailAddress, password;
    public static Set<Cookie> loggedCookie;


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
        loginPage = homePage.clickOnLoginButton();
        loginPage.inputEmail(emailAddress);
        loginPage.inputPassword(password);
        homePage = loginPage.clickOnLoginButton();
        loggedCookie =homePage.getCookie(driver);
    }

    @AfterTest
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
