package testcases.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level1_User_DRY {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String emailAddress;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver100.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        emailAddress = generatefakeEmailAddress("kevinbui", "yopmail.com");
    }

    @Test
    public void TC_01_Register_Empty_Data() {
        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("span#FirstName-error")).getText(), "First name is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#LastName-error")).getText(), "Last name is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Email is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password is required.");

    }

    @Test
    public void TC_02_Register_Invalid_Email() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        driver.findElement(By.id("FirstName")).sendKeys("Automation");
        driver.findElement(By.id("LastName")).sendKeys("FC");
        driver.findElement(By.id("Email")).sendKeys("123@456#%*");
        driver.findElement(By.id("Password")).sendKeys("123456");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Wrong email");


    }

    @Test
    public void TC_03_Register_Success() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        driver.findElement(By.id("FirstName")).sendKeys("Automation");
        driver.findElement(By.id("LastName")).sendKeys("FC");
        driver.findElement(By.id("Email")).sendKeys(emailAddress);
        driver.findElement(By.id("Password")).sendKeys("123456");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
        driver.findElement(By.cssSelector("a.ico-logout")).click();
    }

    @Test
    public void TC_04_Register_ExistingEmail() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        driver.findElement(By.id("FirstName")).sendKeys("Automation");
        driver.findElement(By.id("LastName")).sendKeys("FC");
        driver.findElement(By.id("Email")).sendKeys(emailAddress);
        driver.findElement(By.id("Password")).sendKeys("123456");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error")).getText(), "The specified email already exists");
    }

    @Test
    public void TC_05_Register_Password_Less_Than_6_Chars() {

        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        driver.findElement(By.id("FirstName")).sendKeys("Automation");
        driver.findElement(By.id("LastName")).sendKeys("FC");
        driver.findElement(By.id("Email")).sendKeys(generatefakeEmailAddress("kevinbui", "yopmail.com"));
        driver.findElement(By.id("Password")).sendKeys("12345");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("12345");
        driver.findElement(By.cssSelector("button#register-button")).click();

        Assert.assertEquals(driver.findElement(By.id("Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void TC_06_Register_Invalid_Confirm_Password() {

        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        driver.findElement(By.id("FirstName")).sendKeys("Automation");
        driver.findElement(By.id("LastName")).sendKeys("FC");
        driver.findElement(By.id("Email")).sendKeys(generatefakeEmailAddress("kevinbui", "yopmail.com"));
        driver.findElement(By.id("Password")).sendKeys("123456");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("12345");
        driver.findElement(By.cssSelector("button#register-button")).click();

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
