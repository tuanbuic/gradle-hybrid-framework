package common;

import lombok.Getter;

import java.io.File;
@Getter
public class GlobalConstants {

    private static GlobalConstants globalInstance;
    private GlobalConstants(){}
    public static synchronized GlobalConstants getGlobalConstants(){
        if(globalInstance==null){
            globalInstance = new GlobalConstants();
        }
        return globalInstance;
    }
    private final long shortTimeout = 5;

    private final long longTimeout = 10;
    private final String portalPageUrl = "https://demo.nopcommerce.com/";
    private final String adminPageUrl = "https://admin-demo.nopcommerce.com";
    private final String projectPath = System.getProperty("user.dir");
    private final String osName = System.getProperty("os.name");

    private final String browserName ="browserStackName";
    private final String browserKey = "browserStackKey";
    private final String browserStackUrl ="https://"+ browserName+":"+browserKey+"hub-cloud.browserstack.com/wd/hub";



    //window /mac/linux
    private final String uploadFileFolder = projectPath + File.separator + "uploadFiles" + File.separator;
    private final String downloadFileFolder = projectPath + File.separator + "downloadFiles";
    private final String browserLogPath = projectPath + File.separator + "browserLogs";
    private final String browserLogFolder = projectPath + File.separator + "browserLogs";
    private final String dragDropHTML5 = projectPath + File.separator + "dragdropHTML5";
    private final String autoITScript = projectPath + File.separator + "autoIT";
    private final String ReportingScreenshot = projectPath + File.separator + "ReportNGScreenShots" + File.separator;
    //Database Account / User / Pass/ Port
    private final String dbDevUrl = "32.18.252.185:9860";
    private final String dbDevUser = "automationfc";
    private final String dbDevpass = "P@ssw0rld1";


    private final long retryTestFail = 3;
    private final String extendPath = "PROJECT_PATH" + File.separator + "extentV2" + File.separator;

    private final String javaVersion = System.getProperty("java.version");

}
