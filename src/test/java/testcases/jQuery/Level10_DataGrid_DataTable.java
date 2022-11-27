package testcases.jQuery;

import common.BaseTest;
import exception.BrowserNotSupport;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.jQuery.HomePageObject;
import pageObjects.jQuery.PageGeneratorManager;

import java.util.Random;

public class Level10_DataGrid_DataTable extends BaseTest {
    HomePageObject homePage;
    WebDriver driver;


    @Parameters({"envName", "servername", "browser", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("Local") String envName, @Optional("chrome") String browserName, @Optional("dev") String serverName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Window") String osName, @Optional("10") String osVersion) throws Exception {
        driver = getBrowserDriver(envName, browserName, serverName, ipAddress, portNumber, osName, osVersion);
        homePage = PageGeneratorManager.getHomePage(driver);
    }

//    @Test
//    public void Table_01() {
//        homePage.openPagingByPageNumber("10");
//        homePage.verifyPagingActive("10");
//        homePage.openPagingByPageNumber("20");
//        homePage.verifyPagingActive("20");
//
//        homePage.openPagingByPageNumber("7");
//        homePage.verifyPagingActive("7");
//        homePage.openPagingByPageNumber("18");
//        homePage.verifyPagingActive("18");
//
//    }
//    @Test
//    public void Table_02() {
//        homePage.enterHeaderTextBoxByLabel("Females","salsa");
//        homePage.enterHeaderTextBoxByLabel("Country","VietNam");
//        homePage.enterHeaderTextBoxByLabel("Males","Kevin");
//        homePage.enterHeaderTextBoxByLabel("Total","10000");
//    }
//    @Test
//    public void Table_03(){
//        homePage.getValueEachRowAtAllPage();
//    }
    @Test
    public void Table_04_Enter_To_TextBox_At_any_Row() throws InterruptedException {
        homePage.enterToTextBoxByColumnNameAtRowNumber("Album","1","Miceal 97");
        homePage.enterToTextBoxByColumnNameAtRowNumber("Artist","1","Mihawk 15");
        homePage.enterToTextBoxByColumnNameAtRowNumber("Price","1","15$");
        homePage.selectDropDownByColumnNameInRowNumber("Origin","1","Japan");
        Thread.sleep(5000);
        homePage.selectDropDownByColumnNameInRowNumber("Origin","1","Hong Kong");
        homePage.checkToCheckBoxByColumnNameAtRowNumber("With Poster?","1");
        homePage.ClickToIconByRowNumber("1","Remove");

    }
    @AfterClass
    public void afterClass() {
//        driver.quit();
    }

    public int generateFakeNumber() {
        Random rnd = new Random();
        return rnd.nextInt(9999);
    }

    public String generatefakeEmailAddress(String emailprefix, String webmail) {
        return emailprefix + generateFakeNumber() + "@" + webmail;
    }
}
