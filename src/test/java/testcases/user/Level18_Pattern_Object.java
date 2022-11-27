package testcases.user;

import common.BaseTest;
import common.PageGeneratorManager;
import exception.BrowserNotSupport;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.norCommercePortal.UserHomePageObject;
import pageObjects.norCommercePortal.UserRegisterPageObject;

import java.util.Random;

public class Level18_Pattern_Object extends BaseTest {
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
        homePage = PageGeneratorManager.getUserHomePage(driver);
        registerPage = homePage.clickOnSignupButton();
        registerPage.inputToTextBoxById(driver, "FirstName",firstName);
        registerPage.inputToTextBoxById(driver,"LastName", lastName);
        registerPage.selectToDropdownByName(driver,"DateOfBirthDay","10");
        registerPage.selectToDropdownByName(driver,"DateOfBirthMonth","August");
        registerPage.selectToDropdownByName(driver,"DateOfBirthYear","1995");
        registerPage.inputToTextBoxById(driver, "Email",emailAddress);
        registerPage.inputToTextBoxById(driver, "Password",password);
        registerPage.inputToTextBoxById(driver, "ConfirmPassword",password);


        registerPage.clickButtonByText(driver, "Login");
        Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
         registerPage.clickButtonByText(driver, "Sign out");
        homePage = PageGeneratorManager.getUserHomePage(driver);
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
