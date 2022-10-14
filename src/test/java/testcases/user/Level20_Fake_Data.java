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
import ultilities.DataHelper;

import java.util.Random;

public class Level20_Fake_Data extends BaseTest {
    WebDriver driver;

    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private DataHelper dataHelper;
    private String emailAddress, password;

    @BeforeTest
    @Parameters({"browser", "environment"})
    public void Register(String browserName, String environment) throws BrowserNotSupport {
        dataHelper = DataHelper.getDataHelper();
        emailAddress = dataHelper.getEmailAddress();
        password = dataHelper.getPassword();
        driver = getBrowserDriver(browserName);
        driver.get(getEnvironmentURL(environment));
        homePage = PageGeneratorManager.getUserHomePage(driver);
        registerPage = homePage.clickOnSignupButton();
        registerPage.inputFirstName(dataHelper.getFirstName());
        registerPage.inputLastName(dataHelper.getLastName());
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
