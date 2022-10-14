package testcases.user;


import common.BaseTest;
import exception.BrowserNotSupport;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level5_Page_Factory extends BaseTest {
    WebDriver driver;

    String emailAddress;
    private pageFactory.HomePageObject homePage;
    private pageFactory.RegisterPageObject registerPage;
    private pageFactory.LoginPageObject loginPage;
    private String firstName;
    private String lastName;
    private String password;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) throws BrowserNotSupport {
        driver= getBrowserDriver(browserName);

        emailAddress = generatefakeEmailAddress("kevinbui", "yopmail.com");
        firstName= "Automation";
        lastName="FC";
        password="123456";
        registerPage = new pageFactory.RegisterPageObject(driver);
        homePage = new pageFactory.HomePageObject(driver);
        loginPage = new pageFactory.LoginPageObject(driver);
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
        Assert.assertEquals(loginPage.getEmailErrorMessage(),"Please enter your email");


    }
    @Test
    public void TC_02_Login_Invalid_Data() {
        String invalidEmail ="Abcde";
        homePage.clickOnLoginButton();
        loginPage.inputEmail(invalidEmail);
        loginPage.inputPassword(password);
    }
    @Test
    public void TC_03_Login_NotRegistered_Data() {
        String notRegistered ="nonexistemail@gmail.com";
        homePage.clickOnLoginButton();
        loginPage.inputEmail(notRegistered);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.getNotRegisteredEmailErrorMessage(),"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");

    }
    @Test
    public void TC_04_Login_Existing_Email_Empty_Password() {
        homePage.clickOnLoginButton();
        loginPage.inputEmail(emailAddress);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.getNotRegisteredEmailErrorMessage(),"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");


    }
    @Test
    public void TC_05_Login_Existing_Email_Incorrect_Password() {
        String wrongPassword="123123";
        homePage.clickOnLoginButton();
        loginPage.inputEmail(emailAddress);
        loginPage.inputPassword(wrongPassword);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.getNotRegisteredEmailErrorMessage(),"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

    }
    @Test
    public void TC_06_Login_successfully_Data() {
        homePage.clickOnLoginButton();
        loginPage.inputEmail(emailAddress);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        homePage = new pageFactory.HomePageObject(driver);
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
