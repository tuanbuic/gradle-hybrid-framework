package common;

import exception.BrowserNotSupport;
import factoryEnvironment.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static factoryEnvironment.EnvironmentList.*;

public class BaseTest {
    private WebDriver driver;
    protected final Log log;

    String projectPath = System.getProperty("user.dir");

    @BeforeSuite
    public void initBeforeSuite() {
        deleteAllureReport();
    }

    public BaseTest() {
        log = LogFactory.getLog(getClass());
    }

    public int generateFakeNumber() {
        Random rnd = new Random();
        return rnd.nextInt(9999);
    }

    public String generatefakeEmailAddress(String emailprefix, String webmail) {
        return emailprefix + generateFakeNumber() + "@" + webmail;
    }

    protected WebDriver getBrowserDriver(String envName,String browserName, String serverName,  String ipAddress, String portNumber,String osName,String osVersion) throws Exception {
        switch (envName) {
            case "grid":
                driver = new GridFactory(ipAddress,portNumber,browserName).createDriver();
                break;
            case "browserStack":
                driver = new BrowserStackFactory(browserName,osName,osVersion).createDriver();
                break;
            case "saucelab":
                driver = new SauceLabFactory(browserName,osName).createDriver();
                break;
            case "local":
            default:
                driver = new LocalFactory(browserName).createDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(getEnvironmentURL(serverName));
        return driver;
    }

    protected String getEnvironmentURL(String environmentName) {
        String url = null;
        EnvironmentList environment = valueOf(environmentName.toUpperCase());
        switch (environment) {
            case DEV:
                url = "https://demo.nopcommerce.com/";
                break;
            case STAGING:
                url = "https://staging.nopcommerce.com/";
                break;
            default:
                log.error("Wrong environment");
                break;
        }
        return url;
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
//                log.info(" -------------------------- PASSED -------------------------- ");
            Assert.assertTrue(condition);
            System.out.println(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            System.out.println(" -------------------------- FAILED -------------------------- ");
            // Add lỗi vào ReportNG
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }


    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
            System.out.println(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            System.out.println(" -------------------------- FAILED -------------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }


    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            System.out.println(" -------------------------- FAILED -------------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void deleteAllureReport() {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/allure-json";
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
//                System.out.println(listOfFiles[i].getName());
                new File(listOfFiles[i].toString()).delete();
            }
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    protected void closeBrowserAndDriver() {
        String cmd = "";
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS name = " + osName);

            String driverInstanceName = driver.toString().toLowerCase();
            log.info("Driver instance name = " + driverInstanceName);

            if (driverInstanceName.contains("chrome")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
                } else {
                    cmd = "pkill chromedriver";
                }
            } else if (driverInstanceName.contains("internetexplorer")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
                }
            } else if (driverInstanceName.contains("firefox")) {
                if (osName.contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
                } else {
                    cmd = "pkill geckodriver";
                }
            } else if (driverInstanceName.contains("edge")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
                } else {
                    cmd = "pkill msedgedriver";
                }
            } else if (driverInstanceName.contains("opera")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
                } else {
                    cmd = "pkill operadriver";
                }
            } else if (driverInstanceName.contains("safari")) {
                if (osName.contains("mac")) {
                    cmd = "pkill safaridriver";
                }
            }

            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void showBrowserConsoleLogs(WebDriver driver) {
        if (driver.toString().contains("chrome")) {
            LogEntries logs = driver.manage().logs().get("browser");
            List<LogEntry> logList = logs.getAll();
            for (LogEntry logging : logList) {
                log.info("------" + logging.getLevel().toString() + "------------- \n" + logging.getMessage());
            }
        }
    }
}
