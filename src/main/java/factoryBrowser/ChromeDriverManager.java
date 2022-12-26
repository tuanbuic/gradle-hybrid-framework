package factoryBrowser;

import common.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;
import java.util.HashMap;

public class ChromeDriverManager implements BrowserFactory {
    @Override
    public WebDriver getBrowserDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension",false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-geolocation");

        HashMap<String, Object> chromePrefs= new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups",0);
        chromePrefs.put("download.default_directory", GlobalConstants.getGlobalConstants().getProjectPath()+"\\downloadFiles");
        options.setExperimentalOption("prefs",chromePrefs);
        System.setProperty("wevdriver.chrome.args","--disable-logging");
        System.setProperty("wevdriver.chrome.silentOutput","true");
        return WebDriverManager.chromedriver().capabilities(options).create();
    }
}
