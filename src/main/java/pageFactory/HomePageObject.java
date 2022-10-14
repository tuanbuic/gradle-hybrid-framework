package pageFactory;

import common.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pageObjects.norCommercePortal.UserRegisterPageObject;

public class HomePageObject extends BasePageFactory {
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(how= How.XPATH,using = "//a[@class='ico-register']")
    private WebElement registerLink;
    @FindBy(xpath = "//a[contains(text(),'Log in')]")
    private WebElement loginLink;
    @FindBy(xpath = "//a[@class='ico-account']")
    private WebElement accountLink;

    public UserRegisterPageObject clickOnSignupButton() {
        waitForElementClickable(driver, registerLink);
        clickToElement(driver, registerLink);
        return new UserRegisterPageObject(driver);
    }
    public void clickOnLoginButton(){
        waitForElementClickable(driver, loginLink);
        clickToElement(driver,loginLink);
    }

    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver,accountLink);
        return isElementDisplay(driver,accountLink);
    }

    public void clickOnMyAccountLink() {
        isMyAccountLinkDisplayed();
        clickToElement(driver,accountLink);
    }
}
