package common;

import org.openqa.selenium.WebDriver;
import pageObjects.norCommerceAdmin.AdminDashboardPageObject;
import pageObjects.norCommerceAdmin.AdminLoginPageObject;
import pageObjects.norCommercePortal.*;

public class PageGeneratorManager {
    public static UserRegisterPageObject getUserRegisterPage(WebDriver driver){
        return new UserRegisterPageObject(driver);
    }
    public static UserHomePageObject getUserHomePage(WebDriver driver){
        return new UserHomePageObject(driver);
    }
    public static UserLoginPageObject getUserLoginPage(WebDriver driver){
        return new UserLoginPageObject(driver);
    }
    public static UserCustomerInfoPageObject getUserMyAccountPage(WebDriver driver){
        return new UserCustomerInfoPageObject(driver);
    }
    public static UserAddressPageObject getUserAddressPage(WebDriver driver){
        return new UserAddressPageObject(driver);
    }
    public static UserRewardPointPageObject getUserRewardPointPage(WebDriver driver){
        return new UserRewardPointPageObject(driver);
    }
    public static UserMyProductReviewPageObject getUserMyProductReviewPageObject(WebDriver driver){
        return new UserMyProductReviewPageObject(driver);
    }

    public static UserCustomerInfoPageObject getUserCustomerInfo(WebDriver driver){
        return new UserCustomerInfoPageObject(driver);
    }

    public static AdminLoginPageObject getAdminLoginPageObject(WebDriver driver){
        return new AdminLoginPageObject(driver);

    }
    public static AdminDashboardPageObject getAdminDashboardPageObject(WebDriver driver){
        return new AdminDashboardPageObject(driver);

    }
    public static FacebookPage getFacebookPage(WebDriver driver){
        return new FacebookPage(driver);

    }
}
