package factoryEnvironment;

import common.GlobalConstants;
import exception.BrowserNotSupport;
import factoryBrowser.*;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LocalFactory {
    private  WebDriver driver;
    private String browserName;
    public LocalFactory(String browserName){
        this.browserName=browserName;
    }
    public WebDriver createDriver(){
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        switch(browser){
            case FIREFOX:
                driver = new FirefoxDriverManager().getBrowserDriver();
                break;
            case CHROME:
                driver =new ChromeDriverManager().getBrowserDriver();
                break;
            case EDGE:
                driver = new EdgeDriverManager().getBrowserDriver();
                break;
            case SAFARI:
                driver = new SafariDriverManager().getBrowserDriver();
                break;
            default:
                throw new BrowserNotSupport(browserName);
        }
        driver.manage().timeouts().implicitlyWait(GlobalConstants.getGlobalConstants().getLongTimeout(), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
//        if (browser == BrowserList.CHROME) {
////            File google = new File(GlobalConstants.PROJECT_PATH +"\\browserDrivers\\google-trans.crx");
////            options.addExtensions(google);
//            ChromeOptions options = new ChromeOptions();
//            driver = WebDriverManager.chromedriver().capabilities(options).create();
//        } else if (browser == BrowserList.H_CHROME) {
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("-headless");
//            options.addArguments("window-size=1920x1080");
//            driver = WebDriverManager.chromedriver().capabilities(options).create();
//        } else if (browser == BrowserList.FIREFOX) {
//            driver = WebDriverManager.firefoxdriver().driverVersion("0.21.0").create();
////            driver = WebDriverManager.firefoxdriver().create();
//        } else if (browser == BrowserList.H_FIREFOX) {
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("-headless");
//            options.addArguments("window-size=1920x1080");
//            driver = WebDriverManager.firefoxdriver().capabilities(options).create();
//        } else if (browser == BrowserList.EDGE) {
//            driver = WebDriverManager.edgedriver().create();
//        } else {
//            throw new BrowserNotSupport(browserName);
//        }

    }
}
