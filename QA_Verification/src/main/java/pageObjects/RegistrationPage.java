package pageObjects;

import automationFramework.TestData;
import driver.DriverInit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.Collectors;

public class RegistrationPage {
    protected Logger log = LogManager.getLogger(getClass());
    private int waitTime = 15; //dependent on sandbox performance
    private By signUpBtn = By.xpath("//a[@routerlink='/register']");
    private By emailInput = By.xpath("//input[@name='email']");
    private By createAccAfterEmailEnterBtn = By.xpath("//button[@data-testid='create-new-account-button']");
    private By confirmSignUpBtn = By.xpath("//button[@type='submit' and text()=' Sign up ']");
    private By signInBtn = By.xpath("//button[@type='submit' and text()=' Sign in ']");
    private By settingsLink = By.xpath("//a[contains(text(),'Settings')]");
    private By profileUsername = By.xpath("//input[@placeholder='Your Name']");
    private By profileEmail = By.xpath("//input[@placeholder='Email']");
    private By registrationError = By.xpath("//li[not(@class)]");
    private By logoutBtn = By.xpath("//button[contains(text(),'logout')]");
    private By alreadyHaveAccountLink = By.xpath("//a[contains(text(),'Have an account?')]");

    public WebElement initElement(By locator) {
        return DriverInit.getDriver().findElement(locator);
    }

    public void scrollToElement(By locator) {
        WebElement element = DriverInit.getDriver().findElement(locator);
        ((JavascriptExecutor) DriverInit.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickUsingJS(By locator) {
        JavascriptExecutor executor = (JavascriptExecutor) DriverInit.getDriver();
        executor.executeScript("arguments[0].click();", initElement(locator));
    }

    public void highlightElement(By locator) {
        ((JavascriptExecutor) DriverInit.getDriver()).executeScript("arguments[0].style.border='3px solid red'", initElement(locator));

    }

    public void waitForElement(By locator, int timeInterval) {
        new WebDriverWait(DriverInit.getDriver(), timeInterval).until(ExpectedConditions.elementToBeClickable(locator));
        new WebDriverWait(DriverInit.getDriver(), timeInterval).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void clickElement(By element) {
        waitForElement((element), waitTime);
        initElement(element).click();
    }

    public void setValue(By locator, String inputValue) {
        waitForElement(locator, waitTime);
        scrollToElement(locator);
        try {
            initElement(locator).click();
        } catch (ElementClickInterceptedException ignored) {
            clickUsingJS(locator);
        }
        highlightElement(locator);
        initElement(locator).clear();
        initElement(locator).sendKeys(inputValue);
    }

    public void clickSignUp() {
        clickElement(signUpBtn);
        log.info("Sign up Button clicked");
    }

    public void clickSignIn() {
        clickElement(signInBtn);
        log.info("Sign In Button clicked");
    }

    public void clickConfirmSignUp() {
        clickElement(confirmSignUpBtn);
        log.info("Confirm Sign up Button clicked");
    }

    public void clickSettingsHyperlink() {
        clickElement(settingsLink);
    }

    public void setAccountRequiredValues() {
        setAccountFieldValue("Username", TestData.USERNAME);
        setAccountFieldValue("Email", TestData.EMAIL);
        setAccountFieldValue("Password", TestData.PASSWORD);
        log.info("Account required values   filled");
    }

    public void setAccountFieldValue(String fieldName, String value) {
        setValue(By.xpath("//input[@placeholder='" + fieldName + "']"), value);

    }


    public boolean verifyLayoutElementVisibility(String buttonName) {
        if (buttonName.equals("BBlog"))
            return DriverInit.getDriver().findElements(By.xpath("//a[text()='" + buttonName + "']")).size() == 2;
        return initElement(By.xpath("//a[text()='" + buttonName + "']")).isDisplayed();
    }

    public String getProfileUsername() {
        waitForElement((profileUsername), 15);
        return initElement(profileUsername).getAttribute("value");
    }

    public String getProfileEmail() {
        waitForElement((profileEmail), 15);
        return initElement(profileEmail).getAttribute("value");
    }

    public void enterCredentials() throws AWTException {
        Robot robot = new Robot();
        for (char c : TestData.BACKBBASE_USERNAME.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                throw new RuntimeException(
                        "Key code not found for character '" + c + "'");
            }
            robot.keyPress(keyCode);
            robot.delay(100);
            robot.keyRelease(keyCode);
            robot.delay(100);
        }
        robot.keyPress(KeyEvent.VK_TAB);

        for (char c : TestData.BACKBBASE_PASSWORD.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                throw new RuntimeException(
                        "Key code not found for character '" + c + "'");
            }
            robot.keyPress(keyCode);
            robot.delay(100);
            robot.keyRelease(keyCode);
            robot.delay(100);
        }
        robot.keyPress(KeyEvent.VK_ENTER);
    }

    public List<String> getErrorMessages() {
        waitForElement(registrationError, 15);
        List<String> a = DriverInit.getDriver().findElements(registrationError).stream()
                .map(WebElement::getText).collect(Collectors.toList());
        for (String s : a) System.out.println(s);
        return DriverInit.getDriver().findElements(registrationError).stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }

    public void refresh() {
        DriverInit.getDriver().navigate().refresh();
    }

    public void clickLogoutButton() {
        clickElement(logoutBtn);
    }

    public void clickHaveAnAccountHyperlink() {
        clickElement(alreadyHaveAccountLink);
    }
}