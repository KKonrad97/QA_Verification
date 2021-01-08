package driver;

import automationFramework.TestData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverInit {
    private static WebDriver driver;
    private static String homeUrl = TestData.HOME_URL;

    public static void init() {
        WebDriverManager.chromedriver().setup();
        setDriver(new ChromeDriver());
        driver.get(homeUrl);
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver instance) {
        driver = instance;
    }
}