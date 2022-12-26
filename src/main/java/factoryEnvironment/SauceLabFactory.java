package factoryEnvironment;

import common.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SauceLabFactory {
    private WebDriver driver;
    private String browserName;
    private String osName;

    public SauceLabFactory(String browserName, String osName) {
        this.osName = osName;
        this.browserName = browserName;
    }

    public WebDriver createDriver() throws MalformedURLException {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability("platformName", osName);
        capability.setCapability("browserName", browserName);
        capability.setCapability("browserVersion", "latest");
        capability.setCapability("name", "Run on " + osName + " and" + browserName);
        Map<String, Object> sauceOptions = new HashMap<>();
        if(osName.contains("windows")){
            sauceOptions.put("screenResolution", "1920x1080");
        }
        else{
            sauceOptions.put("screenResolution", "1920x1440");
        }


        capability.setCapability("sauce:options", sauceOptions);
        driver = new RemoteWebDriver(new URL(GlobalConstants.getGlobalConstants().getBrowserStackUrl()), capability);
        driver.manage().timeouts().implicitlyWait(GlobalConstants.getGlobalConstants().getLongTimeout(), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;

    }
}
