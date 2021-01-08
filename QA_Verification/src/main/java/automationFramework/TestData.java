package automationFramework;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestData {
    static String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    public static final String BACKBBASE_USERNAME = "candidatex";
    public static final String BACKBBASE_PASSWORD = "qa-is-cool";
    public static final String PASSWORD = "testPass" + timeStamp;
    public static final String HOME_URL = "https://qa-task.backbasecloud.com/#/";
    public static final String EMAIL = "testEmail" + timeStamp + "@gmail.com";
    public static final String WRONG_EMAIL = "testEmail" + timeStamp + "gmail.com";
    public static final String WRONG_EMAIL1 = "testEmail" + timeStamp + "@gmailcom";
    public static final String WRONG_EMAIL2 = "testEmail" + timeStamp;
    public static final String WRONG_EMAIL3 = "gmail.com";
    public static final String USERNAME = "testUsername" + timeStamp;
}