package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import testUtility.setUpTests;
import automationFramework.TestData;

import java.awt.*;

public class BackBaseCloud_Test1__Registration_Flow_Test extends setUpTests {

    @Test
    public void verifyUserRegistration() throws AWTException {
        log.info("Step 1:\t Open backbasecloud home page");

        log.info("Step 2:\t Click 'Sign up' button");
        registrationPage.enterCredentials();
        registrationPage.clickSignUp();

        log.info("Step 3:\t Verify visibility of buttons Home, Sign in, Sign up, BBlog icon, BBlog hyperlink");
        registrationPage.verifyLayoutElementVisibility("Home");
        registrationPage.verifyLayoutElementVisibility("Sign in");
        registrationPage.verifyLayoutElementVisibility("Sign up");
        registrationPage.verifyLayoutElementVisibility("BBlog");

        log.info("Step 4:\t Set Username, Email, Password values");
        registrationPage.setAccountRequiredValues();

        log.info("Step 5:\t Click Sign Up button");
        registrationPage.clickConfirmSignUp();

        log.info("Step 6:\t Click Settings hyperlink");
        registrationPage.clickSettingsHyperlink();

        log.info("Step 7:\t Verify username and email fields");
        Assert.assertEquals(registrationPage.getProfileUsername(), TestData.USERNAME, "Profile username different than expected\n");
        Assert.assertEquals(registrationPage.getProfileEmail(), TestData.EMAIL, "Profile email different than expected\n");
    }
}