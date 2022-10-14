package testcases.user;


import common.BaseTest;
import common.PageGeneratorManager;
import exception.BrowserNotSupport;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.norCommercePortal.UserHomePageObject;
import pageObjects.norCommercePortal.UserLoginPageObject;
import testcases.common.Common01_Register_Account;

import java.util.Random;

public class Level16_Shared_Data extends BaseTest {
    WebDriver driver;
    String emailAddress;
    String password;
    private UserHomePageObject homePage;
    private UserLoginPageObject loginPage;

    @Parameters({"browser", "environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environment) throws BrowserNotSupport {
        driver = getBrowserDriver(browserName);
        driver.get(getEnvironmentURL(environment));
        homePage = PageGeneratorManager.getUserHomePage(driver);
        emailAddress = Common01_Register_Account.emailAddress;
        password = Common01_Register_Account.password;
        loginPage = homePage.clickOnLoginButton();
        loginPage.inputEmail(emailAddress);
        loginPage.inputPassword(password);
        homePage = loginPage.clickOnLoginButton();
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
