package data;

import ultilities.DataHelper;

public class UserData {

    public static String FIRSTNAME = DataHelper.getDataHelper().getFirstName();
    public static String LASTNAME = DataHelper.getDataHelper().getLastName();
    public static String EMAIL = DataHelper.getDataHelper().getEmailAddress();
    public static String PASSWORD = DataHelper.getDataHelper().getPassword();
    public static String CONFIRMPASSWORD = PASSWORD;


}
