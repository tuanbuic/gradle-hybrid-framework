package factoryBrowser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;
import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;

public class EdgeDriverManager implements BrowserFactory {
    @Override
    public WebDriver getBrowserDriver() {
        if(!IS_OS_WINDOWS || !IS_OS_MAC){
            throw new BrowserNotSupportedException("Edge is not supported on " + System.getProperty("os.name"));
        }
        return WebDriverManager.edgedriver().create();
    }
}
