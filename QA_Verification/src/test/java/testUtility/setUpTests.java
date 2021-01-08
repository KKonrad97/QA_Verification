package testUtility;

import driver.DriverInit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.RegistrationPage;

public class setUpTests {
    protected Logger log = LogManager.getLogger(getClass());
    private String testClassName= this.getClass().getSimpleName();
    protected RegistrationPage registrationPage = new RegistrationPage();

   @BeforeMethod
   public void initialize() {
       log.info("Running test: " + testClassName);
       DriverInit.init();
   }

   @AfterMethod
    public void shutDown() {
       log.info("Test is over, closing chrome instance");
       DriverInit.getDriver().close();
   }
}