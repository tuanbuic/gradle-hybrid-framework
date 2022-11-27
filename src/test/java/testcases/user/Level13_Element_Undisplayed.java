package testcases.user;

import common.BaseTest;
import common.PageGeneratorManager;
import exception.BrowserNotSupport;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.norCommercePortal.FacebookPage;

import java.util.Random;

public class Level13_Element_Undisplayed extends BaseTest {
    WebDriver driver;
   FacebookPage facebookPage;

    @Parameters({"envName", "servername", "browser", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("Local") String envName, @Optional("chrome") String browserName, @Optional("dev") String serverName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Window") String osName, @Optional("10") String osVersion) throws Exception {
        driver = getBrowserDriver(envName, browserName, serverName, ipAddress, portNumber, osName, osVersion);
        facebookPage = PageGeneratorManager.getFacebookPage(driver);
    }

    @Test
    public void TC_01_Verify_Element_Displayed() {
        facebookPage.clickToSignUp();
        verifyTrue(facebookPage.isEmailAddressTextBoxDisplay());
    }

    @Test
    public void TC_02_Verify_Element_Undisplayed_IN_DOM() throws InterruptedException {
        facebookPage.enterEmailAddress("automationfc@gmail.com");

        verifyTrue(facebookPage.isConfirmEmailAddressTextboxDisplayed());

        facebookPage.enterEmailAddress(" ");
        Thread.sleep(3000);
        verifyFalse(facebookPage.isConfirmEmailAddressTextboxDisplayed());

    }


    @Test
    public void TC_03_Verify_Element_Undisplayed_NOT_IN_DOM() {
        facebookPage.clickOnClosePopUp();
        verifyFalse(facebookPage.isConfirmEmailAddressTextboxDisplayed());

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
