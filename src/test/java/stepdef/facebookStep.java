package stepdef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class facebookStep {
    WebDriver webdriver;
    @Given("Open google chrome")
    public void openGoogleChrome() {
        webdriver = WebDriverManager.chromedriver().create();
        webdriver.get("https://google.com");
        webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @When("I go to facebook")
    public void iGoToFacebook() {
        webdriver.get("https://facebook.com");
    }

    @Then("facebook worked")
    public void facebookWorked() {
        Assert.assertTrue(webdriver.findElement(By.id("email")).isDisplayed());
    }

}
