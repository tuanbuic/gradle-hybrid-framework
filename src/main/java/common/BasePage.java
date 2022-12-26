package common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.norCommerceAdmin.AdminLoginPageObject;
import pageObjects.norCommercePortal.*;
import pageUIs.jQuery.HomePageUI;
import pageUIs.norCommerceUser.BasePageUI;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BasePage {
    private long longTimeout = GlobalConstants.getGlobalConstants().getLongTimeout();
    private long shortTimeout = GlobalConstants.getGlobalConstants().getShortTimeout();

    public static BasePage getBasePageObject() {
        return new BasePage();
    }

    public void openPageUrl(WebDriver driver, String url) {
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

    public void refreshCurrentPage(WebDriver driver) {
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
        WebDriverWait wait = new WebDriverWait(driver, longTimeout);
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


    public void clickToElement(WebDriver driver, String locator) {
        getElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... values) {
        getElement(driver, getDynamicXpath(locator, values)).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String value) {
        WebElement e = getElement(driver, locator);
        e.clear();
        e.sendKeys(value);
    }

    public void sendkeyToElement(WebDriver driver, String locator, String value, String... values) {
        WebElement e = getElement(driver, getDynamicXpath(locator, values));
        e.clear();
        e.sendKeys(value);
    }

    public WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    public WebElement getElement(WebDriver driver, String locator, String... values) {
        return driver.findElement(getByLocator(getDynamicXpath(locator, values)));
    }

    public WebElement getChildElement(WebDriver driver, WebElement element, String locator) {
        return element.findElement(getByLocator(locator));
    }

    public WebElement getChildElement(WebDriver driver, WebElement element, String locator, String... values) {
        return element.findElement(getByLocator(getDynamicXpath(locator, values)));
    }

    public String getTextChildElement(WebDriver driver, WebElement element, String locator) {
        return element.findElement(getByLocator(locator)).getText();
    }

    public String getTextChildElement(WebDriver driver, WebElement element, String locator, String... values) {
        return element.findElement(getByLocator(getDynamicXpath(locator, values))).getText();
    }

    public List<WebElement> getListElements(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    public List<WebElement> getListElements(WebDriver driver, String locator, String... values) {
        return driver.findElements(getByLocator(getDynamicXpath(locator, values)));
    }

    public void selectItemInDefaultDropdownByValue(WebDriver driver, String locator, String textItem) {
        Select select = new Select(getElement(driver, locator));
        select.selectByValue(textItem);
    }

    public void selectItemInDefaultDropdownByValue(WebDriver driver, String locator, String textItem, String... values) {
        Select select = new Select(getElement(driver, getDynamicXpath(locator, values)));
        select.selectByValue(textItem);
    }

    public void selectItemInDefaultDropdownByIndex(WebDriver driver, String locator, int index) {
        Select select = new Select(getElement(driver, locator));
        select.selectByIndex(index);
    }

    public void selectItemInDefaultDropdownByIndex(WebDriver driver, String locator, int index, String... values) {
        Select select = new Select(getElement(driver, getDynamicXpath(locator, values)));
        select.selectByIndex(index);
    }

    public void selectItemInDefaultDropdownByVisibleText(WebDriver driver, String locator, String textItem) {
        Select select = new Select(getElement(driver, locator));
        select.selectByVisibleText(textItem);
    }

    public void selectItemInDefaultDropdownByVisibleText(WebDriver driver, String locator, String textItem, String... values) {
        Select select = new Select(getElement(driver, getDynamicXpath(locator, values)));
        select.selectByVisibleText(textItem);
    }

    public String getItemInDefaultDropdown(WebDriver driver, String locator) {
        Select select = new Select(getElement(driver, locator));
        return select.getFirstSelectedOption().getText();
    }

    public String getItemInDefaultDropdown(WebDriver driver, String locator, String... values) {
        Select select = new Select(getElement(driver, getDynamicXpath(locator, values)));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        Select select = new Select(getElement(driver, locator));
        return select.isMultiple();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator, String... values) {
        Select select = new Select(getElement(driver, getDynamicXpath(locator, values)));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdownList(WebDriver driver, String parentLocator, String childLocator, String expectedTestItem) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        getElement(driver, parentLocator).click();

        List<WebElement> allTimes = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
        for (WebElement e : allTimes) {
            String actualText = e.getText();
            if (actualText.trim().equals(expectedTestItem)) {
                e.click();
                break;
            }
        }

    }

    public String getElementAttribute(WebDriver driver, String locator, String attribute) {
        return getElement(driver, locator).getAttribute(attribute);
    }

    public String getElementAttribute(WebDriver driver, String locator, String attribute, String... values) {
        return getElement(driver, getDynamicXpath(locator, values)).getAttribute(attribute);
    }

    public String getElementText(WebDriver driver, String locator) {
        return getElement(driver, locator).getText();
    }

    public String getElementText(WebDriver driver, String locator, String... values) {
        return getElement(driver, getDynamicXpath(locator, values)).getText();
    }

    public String getElementCssValue(WebDriver driver, String locator, String CSS) {
        return getElement(driver, locator).getCssValue(CSS);
    }

    public String getElementCssValue(WebDriver driver, String locator, String CSS, String... values) {
        return getElement(driver, getDynamicXpath(locator, values)).getCssValue(CSS);
    }

    public String getHexaColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    public int getElementSize(WebDriver driver, String locator) {
        return getListElements(driver, locator).size();
    }

    public int getElementSize(WebDriver driver, String locator, String... values) {
        return getListElements(driver, getDynamicXpath(locator, values)).size();
    }

    public void checkDefaultCheckboxRadio(WebDriver driver, String locator) {
        WebElement e = getElement(driver, locator);
        if (!e.isSelected()) {
            e.click();
        }
    }

    public void checkDefaultCheckboxRadio(WebDriver driver, String locator, String... values) {
        WebElement e = getElement(driver, getDynamicXpath(locator, values));
        if (!e.isSelected()) {
            e.click();
        }
    }

    public void uncheckDefaultCheckboxRadio(WebDriver driver, String locator) {
        WebElement e = getElement(driver, locator);
        if (e.isSelected()) {
            e.click();
        }
    }

    public void uncheckDefaultCheckboxRadio(WebDriver driver, String locator, String... values) {
        WebElement e = getElement(driver, getDynamicXpath(locator, values));
        if (e.isSelected()) {
            e.click();
        }
    }

    public boolean isElementDisplay(WebDriver driver, String locator) {
        try {
            return getElement(driver, locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementDisplay(WebDriver driver, String locator, String... values) {
        return getElement(driver, getDynamicXpath(locator, values)).isDisplayed();
    }

    public boolean isElementEnable(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }

    public boolean isElementEnable(WebDriver driver, String locator, String... values) {
        return getElement(driver, getDynamicXpath(locator, values)).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... values) {
        return getElement(driver, getDynamicXpath(locator, values)).isSelected();
    }

    public void switchToFrameIframe(WebDriver driver, String locator) {
        driver.switchTo().frame(getElement(driver, locator));
    }

    public void switchToFrameIframe(WebDriver driver, String locator, String... values) {
        driver.switchTo().frame(getElement(driver, getDynamicXpath(locator, values)));
    }


    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void hoverMouseToElement(WebDriver driver, String locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement(driver, locator));
    }

    public void hoverMouseToElement(WebDriver driver, String locator, String... values) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement(driver, getDynamicXpath(locator, values)));
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
        Actions actions = new Actions(driver);
        actions.sendKeys(getElement(driver, locator), key).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... values) {
        Actions actions = new Actions(driver);
        actions.sendKeys(getElement(driver, getDynamicXpath(locator, values)), key).perform();
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    public void highlightElement(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void highlightElement(WebDriver driver, String locator, String... values) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getElement(driver, getDynamicXpath(locator, values));
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
    }

    public void clickToElementByJS(WebDriver driver, String locator, String... values) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getElement(driver, getDynamicXpath(locator, values)));
    }

    public void scrollToElement(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    public void scrollToElement(WebDriver driver, String locator, String... values) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, getDynamicXpath(locator, values)));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value, String... values) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, getDynamicXpath(locator, values)));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove, String... values) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, getDynamicXpath(locator, values)));
    }

    public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locator));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isImageLoaded(WebDriver driver, String locator, String... values) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, getDynamicXpath(locator, values)));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... values) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locator, values))));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator, String... values) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locator, values))));
    }

    public void waitForAllElementVisible(WebDriver driver, String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public void waitForAllElementVisible(WebDriver driver, String locator, String... values) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locator, values))));
    }

    public void waitForAllElementInvisible(WebDriver driver, String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElements(driver, locator)));
    }

    public void waitForAllElementInvisible(WebDriver driver, String locator, String... values) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElements(driver, getDynamicXpath(locator, values))));
    }

    public void waitForElementUndisplayed(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
        overrideGlobalTimeout(driver, shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
        overrideGlobalTimeout(driver, longTimeout);
    }

    private void overrideGlobalTimeout(WebDriver driver, long timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    protected boolean isElementUndisplayed(WebDriver driver, String locator) {
        overrideGlobalTimeout(driver, shortTimeout);
        List<WebElement> elements = getListElements(driver, locator);
        overrideGlobalTimeout(driver, longTimeout);
        if (elements.size() == 0) {
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    private String getDynamicXpath(String locatorType, String... values) {
        if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")) {
            locatorType = String.format(locatorType, (Object) values);
        }
        return locatorType;
    }

    public By getByLocator(String locatorType) {
        By by = null;
        if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
            by = By.id(locatorType.substring(3));
        } else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=")) {
            by = By.className(locatorType.substring(6));
        } else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=")) {
            by = By.name(locatorType.substring(5));
        } else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
            by = By.cssSelector(locatorType.substring(4));
        } else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")) {
            by = By.xpath(locatorType.substring(6));
        } else {
            throw new RuntimeException("Locator Type invalid");
        }
        return by;
    }

    //Refactor o bai 8
    public UserAddressPageObject openAddressPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.ADDRESS_LINK_PAGE);
        clickToElement(driver, BasePageUI.ADDRESS_LINK_PAGE);
        return PageGeneratorManager.getUserAddressPage(driver);
    }

    public UserRewardPointPageObject openRewardPoint(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.REWARDPOINTS_LINK_PAGE);
        clickToElement(driver, BasePageUI.REWARDPOINTS_LINK_PAGE);
        return PageGeneratorManager.getUserRewardPointPage(driver);
    }

    public UserMyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.PRODUCTREVIEWS_LINK_PAGE);
        clickToElement(driver, BasePageUI.PRODUCTREVIEWS_LINK_PAGE);
        return PageGeneratorManager.getUserMyProductReviewPageObject(driver);
    }

    public UserCustomerInfoPageObject openCustomerInfo(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.CUSTOMERINFO_LINK_PAGE);
        clickToElement(driver, BasePageUI.CUSTOMERINFO_LINK_PAGE);
        return PageGeneratorManager.getUserCustomerInfo(driver);
    }

    public UserHomePageObject logOutLinkUser(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_AT_USER);
        clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_USER);
        return PageGeneratorManager.getUserHomePage(driver);
    }

    public AdminLoginPageObject logOutAdmin(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN);
        clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN);
        return PageGeneratorManager.getAdminLoginPageObject(driver);
    }

    //Refactor o bai 9
    public BasePage openPagesAtMyAccountByName(WebDriver driver, String name) {
        waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_BY_NAME, name);
        clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_BY_NAME, name);
        switch (name) {
            case "Customer info":
                return PageGeneratorManager.getUserCustomerInfo(driver);

            case "Addresses":
                return PageGeneratorManager.getUserAddressPage(driver);

            case "My product reviews":
                return PageGeneratorManager.getUserMyProductReviewPageObject(driver);

            case "Reward points":
                return PageGeneratorManager.getUserRewardPointPage(driver);
            default:
                throw new RuntimeException("Wrong page name");
        }

    }

    public void openPagesAtMyAccountByNames123(WebDriver driver, String name) {
        waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_BY_NAME, name);
        clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_BY_NAME, name);
    }

    public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
        String filepath = GlobalConstants.getGlobalConstants().getUploadFileFolder();
        String fullFileName = "";
        for (String file : fileNames) {
            fullFileName = fullFileName + filepath + file + "\n";
        }
        fullFileName = fullFileName.trim();
        getElement(driver, HomePageUI.UPLOAD_FILE).sendKeys(fullFileName);
    }

    public void setCookie(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Set<Cookie> getCookie(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void inputToTextBoxById(WebDriver driver, String textID, String value) {
        waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, textID);
        sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, textID, value);
    }

    public void clickButtonByText(WebDriver driver, String text) {
        waitForElementClickable(driver, BasePageUI.DYNAMIC_BUTTON_TEXT, text);
        clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_TEXT, text);
    }

    public void selectToDropdownByName(WebDriver driver,String dropdownAttributeName, String itemValue) {
        waitForElementClickable(driver, BasePageUI.DYNAMIC_DROPDOWN_NAME, dropdownAttributeName);
        selectItemInDefaultDropdownByVisibleText(driver, BasePageUI.DYNAMIC_BUTTON_TEXT, dropdownAttributeName,itemValue);
    }
}
