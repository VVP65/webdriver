package testng;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    WebDriver driver;
    Logger logger = Logger.getLogger(BaseTest.class);

    @BeforeClass
    public void browserSetUp() {
        driver = new ChromeDriver();
//        WebDriverManager.edgedriver().setup();
//        driver = new EdgeDriver();
//        driver = new FirefoxDriver();
    }

    @AfterClass
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}

