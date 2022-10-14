package pageObjects.saucelab;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

    public static LoginPageObject getUserLoginPage(WebDriver driver){
        return new LoginPageObject(driver);
    }
    public static ProductPageObject getProDuctPage(WebDriver driver){
        return new ProductPageObject(driver);
    }
}
