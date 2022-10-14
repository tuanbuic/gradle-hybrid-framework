package pageUIs.norCommerceUser;

public class BasePageUI {
    public static final String CUSTOMERINFO_LINK_PAGE="xpath=//li[contains(@class,'customer-info')]/a";
    public static final String ADDRESS_LINK_PAGE="xpath=//li[contains(@class,'customer-addresses')]/a";
    public static final String REWARDPOINTS_LINK_PAGE="xpath=//li[contains(@class,'reward-points')]/a";
    public static final String PRODUCTREVIEWS_LINK_PAGE="xpath=//li[contains(@class,'customer-reviews ')]/a";
    public static final String LOGOUT_LINK_AT_USER="xpath=//a[@class='ico-logout']";
    public static final String LOGOUT_LINK_AT_ADMIN="xpath=//a[text()='Logout']";
    public static  String DYNAMIC_PAGE_AT_MY_ACCOUNT_BY_NAME="xpath=//div[contains(@class,'account-navigation')]//a[text()='%s']";
    public static  String DYNAMIC_TEXTBOX_BY_ID="xpath=//input[@id='%s']";
    public static  String DYNAMIC_BUTTON_TEXT="xpath=//button[text()='%s']";
    public static  String DYNAMIC_DROPDOWN_NAME="xpath=//select[@name='%s']";


}

