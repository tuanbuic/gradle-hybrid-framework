package testcases.jQuery;


import common.BaseTest;
import exception.BrowserNotSupport;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.jQuery.HomePageObject;
import pageObjects.jQuery.PageGeneratorManager;

import java.util.Random;

public class Level11_UploadFile extends BaseTest {
    HomePageObject homePage;
    WebDriver driver;

    String []fileNames = {"Csharp.png","Java.png","Python.png"};

    @Parameters({"envName", "servername", "browser", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("Local") String envName, @Optional("chrome") String browserName, @Optional("dev") String serverName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Window") String osName, @Optional("10") String osVersion) throws Exception {
        driver = getBrowserDriver(envName, browserName, serverName, ipAddress, portNumber, osName, osVersion);
        driver.manage().window().maximize();
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void upload_file_01() {
        homePage.uploadMultipleFiles(driver,fileNames);
        Assert.assertTrue(homePage.isFileLoadedByName("Csharp.png"));
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
