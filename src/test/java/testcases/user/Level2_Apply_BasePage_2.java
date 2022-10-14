package testcases.user;

import common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level2_Apply_BasePage_2 {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String emailAddress;
    BasePage basePage;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver100.exe");
        driver = new ChromeDriver();
        basePage = BasePage.getBasePageObject();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        emailAddress = generatefakeEmailAddress("kevinbui", "yopmail.com");

    }

    @Test
    public void TC_01_Register_Empty_Data() {
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//a[@class='ico-register']");
        basePage.waitForElementClickable(driver, "//button[@id='register-button']");
        basePage.clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");

    }

    @Test
    public void TC_02_Register_Invalid_Email() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        basePage.sendkeyToElement(driver,"//input[@id='FirstName']","Automation");
        basePage.sendkeyToElement(driver,"//input[@id='LastName']","FC");
        basePage.sendkeyToElement(driver,"//input[@id='Email']","123@456#%*");
        basePage.sendkeyToElement(driver,"//input[@id='Password']","123456");
        basePage.sendkeyToElement(driver,"//input[@id='ConfirmPassword']","123456");
        basePage.clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Wrong email");


    }

    @Test
    public void TC_03_Register_Success() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        basePage.sendkeyToElement(driver,"//input[@id='FirstName']","Automation");
        basePage.sendkeyToElement(driver,"//input[@id='LastName']","FC");
        basePage.sendkeyToElement(driver,"//input[@id='Email']",emailAddress);
        basePage.sendkeyToElement(driver,"//input[@id='Password']","123456");
        basePage.sendkeyToElement(driver,"//input[@id='ConfirmPassword']","123456");
        basePage.clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(basePage.getElementText(driver,"//div[@class='result']"), "Your registration completed");
        basePage.clickToElement(driver, "//a[@class='ico-logout']");
    }

    @Test
    public void TC_04_Register_ExistingEmail() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        basePage.sendkeyToElement(driver,"//input[@id='FirstName']","Automation");
        basePage.sendkeyToElement(driver,"//input[@id='LastName']","FC");
        basePage.sendkeyToElement(driver,"//input[@id='Email']",emailAddress);
        basePage.sendkeyToElement(driver,"//input[@id='Password']","123456");
        basePage.sendkeyToElement(driver,"//input[@id='ConfirmPassword']","123456");
        basePage.clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error")).getText(), "The specified email already exists");
    }

    @Test
    public void TC_05_Register_Password_Less_Than_6_Chars() {

        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        basePage.sendkeyToElement(driver,"//input[@id='FirstName']","Automation");
        basePage.sendkeyToElement(driver,"//input[@id='LastName']","FC");
        basePage.sendkeyToElement(driver,"//input[@id='Email']",generatefakeEmailAddress("kevinbui", "yopmail.com"));
        basePage.sendkeyToElement(driver,"//input[@id='Password']","12345");
        basePage.sendkeyToElement(driver,"//input[@id='ConfirmPassword']","12345");
        basePage.clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(driver.findElement(By.id("Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void TC_06_Register_Invalid_Confirm_Password() {

        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        basePage.sendkeyToElement(driver,"//input[@id='FirstName']","Automation");
        basePage.sendkeyToElement(driver,"//input[@id='LastName']","FC");
        basePage.sendkeyToElement(driver,"//input[@id='Email']",generatefakeEmailAddress("kevinbui", "yopmail.com"));
        basePage.sendkeyToElement(driver,"//input[@id='Password']","123456");
        basePage.sendkeyToElement(driver,"//input[@id='ConfirmPassword']","12345");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(driver.findElement(By.id("ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");
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
