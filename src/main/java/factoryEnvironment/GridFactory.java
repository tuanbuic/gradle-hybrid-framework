package factoryEnvironment;

import common.GlobalConstants;
import exception.BrowserNotSupport;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class GridFactory {
    private RemoteWebDriver driver;
    private String ipAddress;
    private String portNumber;
    private String browserName;

    public GridFactory(String ipAddress, String portNumber, String browserName) {

        this.ipAddress = ipAddress;
        this.portNumber = portNumber;
        this.browserName = browserName;
    }

    public WebDriver createDriver() throws MalformedURLException {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        DesiredCapabilities capability = null;

        if (browser == BrowserList.CHROME) {
            capability = DesiredCapabilities.chrome();
            capability.setBrowserName("chrome");
            capability.setPlatform(Platform.ANY);
            ChromeOptions options = new ChromeOptions();
            options.merge(capability);
        } else if (browser == BrowserList.H_CHROME) {
            capability = DesiredCapabilities.chrome();
            capability.setBrowserName("chrome");
            capability.setPlatform(Platform.ANY);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("-headless");
            options.addArguments("window-size=1920x1080");
            options.merge(capability);
        } else if (browser == BrowserList.FIREFOX) {
            capability = DesiredCapabilities.firefox();
            capability.setBrowserName("firefox");
            capability.setPlatform(Platform.ANY);;
            FirefoxOptions options = new FirefoxOptions();
            options.merge(capability);
        } else if (browser == BrowserList.H_FIREFOX) {
            capability = DesiredCapabilities.firefox();
            capability.setBrowserName("firefox");
            capability.setPlatform(Platform.ANY);
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-headless");
            options.addArguments("window-size=1920x1080");
            options.merge(capability);
        } else {
            throw new BrowserNotSupport(browserName);
        }
        driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress, portNumber)), capability);
        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
