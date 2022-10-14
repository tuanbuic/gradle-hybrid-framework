package testcases.jQuery;


import common.BaseTest;
import exception.BrowserNotSupport;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jQuery.HomePageObject;
import pageObjects.jQuery.PageGeneratorManager;

import java.util.Random;

public class Level11_UploadFile extends BaseTest {
    HomePageObject homePage;
    WebDriver driver;

    String []fileNames = {"Csharp.png","Java.png","Python.png"};

    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browserName,String url) throws BrowserNotSupport {
        driver = getBrowserDriver(browserName,url);
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
