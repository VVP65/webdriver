package testng;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class BaseTest {
    WebDriver driver;
    Logger logger = Logger.getLogger(BaseTest.class);

    @AfterClass
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}