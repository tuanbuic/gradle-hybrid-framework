package factoryBrowser;

import common.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Collections;
import java.util.HashMap;

public class FirefoxDriverManager implements BrowserFactory {
    @Override
    public WebDriver getBrowserDriver() {
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,GlobalConstants.getGlobalConstants().getBrowserLogPath());

        return WebDriverManager.firefoxdriver().driverVersion("0.21.0").create();
    }
}
