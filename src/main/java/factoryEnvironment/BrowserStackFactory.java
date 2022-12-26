package factoryEnvironment;

import common.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrowserStackFactory {
    private WebDriver driver;
    private String browserName;
    private String osName;
    private String osVersion;

    public BrowserStackFactory(String browserName, String osName, String osVersion) {

        this.osName = osName;
        this.osVersion = osVersion;
        this.browserName = browserName;
    }

    public WebDriver createDriver() throws MalformedURLException {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability("os", osName);
        capability.setCapability("os_version", osVersion);
        capability.setCapability("browser", browserName);
        capability.setCapability("browser_version", "latest");
        capability.setCapability("browserstack.debug", "true");
        capability.setCapability("name", "Run on " + osName + " and" + browserName);

        driver = new RemoteWebDriver(new URL(GlobalConstants.getGlobalConstants().getBrowserStackUrl()), capability);
        driver.manage().timeouts().implicitlyWait(GlobalConstants.getGlobalConstants().getLongTimeout(), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
