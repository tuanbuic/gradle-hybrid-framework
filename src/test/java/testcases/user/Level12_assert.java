package testcases.user;

import common.BaseTest;
import common.PageGeneratorManager;
import exception.BrowserNotSupport;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.norCommercePortal.*;

import java.util.Random;

public class Level12_assert extends BaseTest {
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

    @Parameters({"browser","environment"})
    @BeforeClass
    public void beforeClass(String browserName,String environment) throws BrowserNotSupport {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getUserHomePage(driver);
        emailAddress = generatefakeEmailAddress("kevinbui", "yopmail.com");
        firstName = "Automation";
        lastName = "FC";
        password = "123456";
         adminEmailAddress = "admin@yourstore.com";
         adminPassword = "admin";

        driver.manage().window().maximize();
        driver.get(getEnvironmentURL(environment));
    }

    @Test
    public void TC_01_Login_Empty_Data() {
        registerPage = homePage.clickOnSignupButton();
        registerPage.inputFirstName(firstName);
        registerPage.inputLastName(lastName);
        registerPage.inputEmail(emailAddress);
        registerPage.inputPassword(password);
        registerPage.inputConfirmPassword(password);
        registerPage.clickOnRegisterButton();
        verifyEquals(registerPage.getSuccessMessage(),"Your registration completed......");

        homePage = registerPage.clickSignout();
        loginPage = homePage.clickOnLoginButton();
        loginPage.inputEmail(emailAddress);
        loginPage.inputPassword(password);
        homePage = loginPage.clickOnLoginButton();
        verifyFalse(homePage.isMyAccountLinkDisplayed());
        userCustomerInfoPageObject = homePage.clickOnMyAccountLink();
        verifyFalse(userCustomerInfoPageObject.isCustomerInfoPageDisplay());


    }

//    @Test
//    public void TC_02_Login() {
//        loginPage = homePage.clickOnLoginButton();
//        loginPage.inputEmail(emailAddress);
//        loginPage.inputPassword(password);
//        homePage = loginPage.clickOnLoginButton();
//    }
//
//    @Test
//    public void TC_03_My_Account() {
//        userCustomerInfoPageObject = homePage.clickOnMyAccountLink();
//        userCustomerInfoPageObject.isCustomerInfoPageDisplay();
//
//    }
//
//    @Test
//    public void TC_04_DynamicPage() {
//        addressPage = (UserAddressPageObject) userCustomerInfoPageObject.openPagesAtMyAccountByName(driver,"Addresses");
//        myProductReviewPage = (UserMyProductReviewPageObject) addressPage.openPagesAtMyAccountByName(driver,"My product reviews");
//        rewardPointPage = (UserRewardPointPageObject) myProductReviewPage.openPagesAtMyAccountByName(driver,"Reward points");
//
//    }

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
