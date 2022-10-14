package common;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class BasePageFactory {
    public static BasePageFactory getBasePageFactoryObject(){
        return new BasePageFactory();
    }
    protected void openPageUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshToPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void acceptAlert(WebDriver driver) {
        waitAlertPresent(driver).accept();
    }

    public void cancelAlert(WebDriver driver) {
        waitAlertPresent(driver).dismiss();
    }

    public void getTextAlert(WebDriver driver) {
        waitAlertPresent(driver).getText();
    }

    public Alert waitAlertPresent(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public void switchToWindowByID(WebDriver driver, String windowID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String id : allWindows) {
            if (id.equals(windowID)) {
                driver.switchTo().window(id);
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String id : allWindows) {
            driver.switchTo().window(id);
            if (driver.getTitle().equals(title))
                break;
        }
    }

    public void closeAllTabWithoutParent(WebDriver driver, String parentId) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String id : allWindows) {
            if (!id.equals(parentId)) {
                driver.switchTo().window(id);
                driver.close();
            }
            driver.switchTo().window(parentId);
        }
    }

    public void waitForElementVisible(WebDriver driver, WebElement e){
        WebDriverWait explicitWait = new WebDriverWait(driver, 10);
        explicitWait.until(ExpectedConditions.visibilityOf(e));
    }
    public void waitForElementClickable(WebDriver driver,WebElement e){
        WebDriverWait explicitWait = new WebDriverWait(driver, 10);
        explicitWait.until(ExpectedConditions.elementToBeClickable(e));
    }
    public void waitForAllElementVisible(WebDriver driver,List<WebElement> e){
        WebDriverWait explicitWait = new WebDriverWait(driver, 10);
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(e));
    }
    public void waitForAllElementInvisible(WebDriver driver, WebElement e){
        WebDriverWait explicitWait = new WebDriverWait(driver, 10);
        explicitWait.until(ExpectedConditions.invisibilityOf(e));
    }

    public void clickToElement(WebDriver driver, WebElement element) {
        element.click();
    }

    public boolean isElementDisplay(WebDriver driver, WebElement element) {
        return element.isDisplayed();
    }

    public void sendkeyToElement(WebDriver driver, WebElement e,String value) {
        e.clear();
        e.sendKeys(value);
    }
    public String getElementText(WebDriver driver, WebElement element) {
        return element.getText();
    }

}
