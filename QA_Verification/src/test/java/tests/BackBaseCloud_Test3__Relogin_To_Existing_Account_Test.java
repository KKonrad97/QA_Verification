package tests;

        import automationFramework.TestData;
        import org.testng.Assert;
        import org.testng.annotations.Test;
        import testUtility.setUpTests;

        import java.awt.*;

public class BackBaseCloud_Test3__Relogin_To_Existing_Account_Test extends setUpTests {

    @Test
    public void verifyUserRelogin() throws AWTException {
        log.info("Step 1:\t Open backbasecloud home page");

        log.info("Step 2:\t Click 'Sign up' button");
        registrationPage.enterCredentials();
        registrationPage.clickSignUp();

        log.info("Step 4:\t Set Username, Email, Password values");
        registrationPage.setAccountRequiredValues();

        log.info("Step 5:\t Click Sign Up button");
        registrationPage.clickConfirmSignUp();

        log.info("Step 6:\t Click Settings hyperlink");
        registrationPage.clickSettingsHyperlink();

        log.info("Step 7:\t Click Logout button");
        registrationPage.clickLogoutButton();

        log.info("Step 8:\t Click Sign Up button");
        registrationPage.clickSignUp();

        log.info("Step 9:\t Click Have an account? hyperlink");
        registrationPage.clickHaveAnAccountHyperlink();

        log.info("Step 10:\t Try to login with credentials used in Step 4");
        registrationPage.setAccountFieldValue("Username", TestData.USERNAME);
        registrationPage.setAccountFieldValue("Password", TestData.PASSWORD);
        registrationPage.clickSignIn();

        log.info("Step 11:\t Verify if logged successfully");
        Assert.assertTrue(registrationPage.verifyLayoutElementVisibility("Global Feed"));
    }
}