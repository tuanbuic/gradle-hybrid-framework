package testcases.sort;


import common.BaseTest;
import exception.BrowserNotSupport;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.saucelab.LoginPageObject;
import pageObjects.saucelab.PageGeneratorManager;
import pageObjects.saucelab.ProductPageObject;

import java.util.Random;

public class Level19_Asc_Desc extends BaseTest {
    WebDriver driver;

    private LoginPageObject loginPage;
    private ProductPageObject productPage;


    @BeforeClass
    @Parameters({"browser", "appUrl"})
    public void login(String browserName, String appUrl) throws BrowserNotSupport {

        driver = getBrowserDriver(browserName, appUrl);
        loginPage = PageGeneratorManager.getUserLoginPage(driver);
        loginPage.inputEmail("standard_user");
        loginPage.inputPassword("secret_sauce");
        productPage = loginPage.clickLogin();

    }

    @Test
    public void Sort_Name() {
        productPage.selectDropdown("Name (A to Z)");
        Assert.assertTrue(productPage.isSortByAscending());
        productPage.selectDropdown("Name (Z to A)");
        Assert.assertTrue(productPage.isSortByDecending());
    }

    @Test
    public void Sort_Price() {

        productPage.selectDropdown("Price (low to high)");
        Assert.assertTrue(productPage.isProductPriceSortedAscending());
        productPage.selectDropdown("Price (high to low)");
        Assert.assertTrue(productPage.isProductPriceSortedDescending());

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
