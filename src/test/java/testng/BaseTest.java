package testng;

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
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\webdrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        System.setProperty("webdriver.edge.driver", "src\\main\\resources\\webdrivers\\msedgedriver.exe");
        driver = new EdgeDriver();
        System.setProperty("webdriver.geco.driver", "src\\main\\resources\\webdrivers\\gecodriver.exe");
        driver = new FirefoxDriver();
    }

    @AfterClass
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}