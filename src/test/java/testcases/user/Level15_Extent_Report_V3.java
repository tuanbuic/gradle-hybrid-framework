package testcases.user;

import common.BaseTest;
import common.PageGeneratorManager;
import exception.BrowserNotSupport;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.norCommercePortal.*;

import java.util.Random;

public class Level15_Extent_Report_V3 extends BaseTest {
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

    @Parameters({"browser", "environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environment) throws BrowserNotSupport {
        driver = getBrowserDriver(browserName);
        driver.get(getEnvironmentURL(environment));

        emailAddress = generatefakeEmailAddress("kevinbui", "yopmail.com");
        firstName = "Automation";
        lastName = "FC";
        password = "123456";
        adminEmailAddress = "admin@yourstore.com";
        adminPassword = "admin";
        driver.manage().window().maximize();
        homePage = PageGeneratorManager.getUserHomePage(driver);
    }

//    @Test
//    public void TC_01_Verify_Element_Displayed(Method method) {
//        ExtentManager.startTest(method.getName(),"Register - Step01- Navigate to Register Page");
//        registerPage = homePage.clickOnSignupButton();
//        ExtentManager.startTest(method.getName(),"Register - Step02- Enter to Firstname textbox with value: '" + firstName + "'");
//        registerPage.inputFirstName(firstName);
//        ExtentManager.startTest(method.getName(),"Register - Step03- Enter to LastName textbox with value: '" + lastName + "'");
//        registerPage.inputLastName(lastName);
//        ExtentManager.startTest(method.getName(),"Register - Step04- Enter to Email Address textbox with value: '" + emailAddress + "'");
//        registerPage.inputEmail(emailAddress);
//        ExtentManager.startTest(method.getName(),"Register - Step05- Enter to password textbox with value: '" + password + "'");
//        registerPage.inputPassword(password);
//        ExtentManager.startTest(method.getName(),"Register - Step06- Enter to confirm password textbox with value: '" + password + "'");
//        registerPage.inputConfirmPassword(password);
//        ExtentManager.startTest(method.getName(),"Register - Step07- click to 'Register' button");
//
//        registerPage.clickOnRegisterButton();
//        ExtentManager.startTest(method.getName(),"Register - Step08-Verify register success message is displayed");
//        Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed......");
//        ExtentManager.startTest(method.getName(),"Register - Step09-Click to Logout link");
//        homePage = registerPage.clickSignout();
//        ExtentManager.startTest(method.getName(),"Login - Step01-Navigate to Login button");
//        loginPage = homePage.clickOnLoginButton();
//        ExtentManager.startTest(method.getName(),"Login - Step02- Enter to emailAdress textbox with value: '" + emailAddress + "'");
//        loginPage.inputEmail(emailAddress);
//        ExtentManager.startTest(method.getName(),"Login - Step02- Enter to password textbox with value: '" + password + "'");
//        loginPage.inputPassword(password);
//        ExtentManager.startTest(method.getName(),"Login - Step03-Click on Login button");
//        homePage = loginPage.clickOnLoginButton();
//        ExtentManager.startTest(method.getName(),"Login - Step04-Verify My account menu display");
//        Assert.assertFalse(homePage.isMyAccountLinkDisplayed());
//        ExtentManager.startTest(method.getName(),"Login - Step05- Click on My account link");
//        userCustomerInfoPageObject = homePage.clickOnMyAccountLink();
//        ExtentManager.startTest(method.getName(),"Login - Step06- Verify Customer Info page diplay");
//        Assert.assertFalse(userCustomerInfoPageObject.isCustomerInfoPageDisplay());
//        ExtentManager.endTest();
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
