package tests;

import automationFramework.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;
import testUtility.setUpTests;

import java.awt.*;

public class BackBaseCloud_Test2__Registration_Page_Validations extends setUpTests {

    @Test
    public void verifyUserRegistrationValidations() throws AWTException {
        log.info("Step 1:\t Open backbasecloud home page");

        log.info("Step 2:\t Click 'Sign up' button");
        registrationPage.enterCredentials();
        registrationPage.clickSignUp();

        log.info("Step 3:\t Confirm registration by clicking 'Sign up' button and verify error message");
        registrationPage.clickConfirmSignUp();
        Assert.assertTrue(registrationPage.getErrorMessages().contains("message The \"password\" argument must be one of" +
                " type string, Buffer, TypedArray, or DataView. Received type undefined"));
        Assert.assertTrue(registrationPage.getErrorMessages().contains("error [object Object]"));

        log.info("Step 4:\t Check configurations:" +
                "Fields Filled: Username" +
                "Fields Filled: Username&Password" +
                "Fields Filled: Email&Password" +
                "Fields Filled: Username&wrong Email" +
                "Fields Filled: Username&correct Email");
        registrationPage.refresh();
        registrationPage.setAccountFieldValue("Username", TestData.USERNAME);
        registrationPage.clickConfirmSignUp();
        Assert.assertTrue(registrationPage.getErrorMessages().contains("email can't be blank"));

        registrationPage.refresh();
        registrationPage.setAccountFieldValue("Username", TestData.USERNAME);
        registrationPage.setAccountFieldValue("Password", TestData.PASSWORD);
        registrationPage.clickConfirmSignUp();
        Assert.assertTrue(registrationPage.getErrorMessages().contains("email can't be blank"));

        registrationPage.refresh();
        registrationPage.setAccountFieldValue("Email", TestData.EMAIL);
        registrationPage.setAccountFieldValue("Password", TestData.PASSWORD);
        registrationPage.clickConfirmSignUp();
        Assert.assertTrue(registrationPage.getErrorMessages().contains("username can't be blank"));

        registrationPage.refresh();
        registrationPage.setAccountFieldValue("Username", TestData.USERNAME);
        registrationPage.setAccountFieldValue("Email", TestData.WRONG_EMAIL);
        registrationPage.setAccountFieldValue("Password", "");
        registrationPage.clickConfirmSignUp();
        Assert.assertTrue(registrationPage.getErrorMessages().contains("email is invalid"));

        registrationPage.refresh();
        registrationPage.setAccountFieldValue("Email", TestData.WRONG_EMAIL1);
        registrationPage.clickConfirmSignUp();
        Assert.assertTrue(registrationPage.getErrorMessages().contains("email is invalid"));

        registrationPage.refresh();
        registrationPage.setAccountFieldValue("Email", TestData.WRONG_EMAIL2);
        registrationPage.clickConfirmSignUp();
        Assert.assertTrue(registrationPage.getErrorMessages().contains("email is invalid"));

        registrationPage.refresh();
        registrationPage.setAccountFieldValue("Email", TestData.WRONG_EMAIL3);
        registrationPage.clickConfirmSignUp();
        Assert.assertTrue(registrationPage.getErrorMessages().contains("email is invalid"));

        registrationPage.refresh();
        registrationPage.setAccountFieldValue("Username", TestData.USERNAME);
        registrationPage.setAccountFieldValue("Email", TestData.EMAIL);
        registrationPage.clickConfirmSignUp();
        Assert.assertFalse(registrationPage.verifyLayoutElementVisibility("Home"));
    }
}