package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class EdgeDriverTests extends BaseTest {

    @Test
    public void EdgeBrowserTests() throws NullPointerException, IOException {
        driver.get("http://google.com");
        driver.manage().window().maximize();
        WebElement googleSearchInput = driver.findElement(By.name("q"));
        googleSearchInput.sendKeys("wikipedia");
        WebElement googleSearchButton = driver.findElement(By.xpath("//*[@class='iblpc']"));
        googleSearchButton.submit();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='Tg7LZd']")));
        Assert.assertTrue(true, String.valueOf(driver.findElement(By.xpath("(.//a[contains(@href,'wikipedia.org')])[1]"))));
        WebElement clickLink = driver.findElement(By.xpath("(.//a[contains(@href,'wikipedia.org')])[1]"));
        clickLink.click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.urlContains("wikipedia.org"));
        Assert.assertTrue(true, String.valueOf(driver.findElement(By.xpath("(.//a[contains(@accesskey,'z')])[1]"))));
        String wikiPageTitle = driver.getTitle();
        logger.info("Wiki Page Title: " + wikiPageTitle);
        int wikiTitleLength = driver.getTitle().length();
        logger.info("Wiki Page Title Length is: " + wikiTitleLength);
        String wikiPageURL = driver.getCurrentUrl();
        Assert.assertTrue(wikiPageURL.contains("ru.wikipedia.org"), "Current URL is incorrect.");
        logger.info("The current page URL is: " + wikiPageURL);
        int wikiPageLength = driver.getPageSource().length();
        logger.info("Wiki Page Source Length: " + wikiPageLength);
        WebElement screenshotOfTheFirstDYKSectionElement = driver.findElement(By.xpath("//div[contains(@id,'dyk')]//img"));
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver, screenshotOfTheFirstDYKSectionElement);
        ImageIO.write(screenshot.getImage(), "jpg", new File("C:\\Users\\Volodymyr_Protsiv\\Desktop\\JavaMentoringProgram\\M6_Webdriver_and_Locators\\hello-webdriver\\target\\ElementScreenshot.jpg"));
        WebElement wikipediaSearchInput = driver.findElement(By.name("search"));
        wikipediaSearchInput.sendKeys("Test Automation");
        WebElement wikipediaSearchButton = driver.findElement(By.xpath("//input[@name='search']"));
        wikipediaSearchButton.submit();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[@id='firstHeading']")));
        WebElement wikipediaSearchResult = driver.findElement(By.xpath("//div[@id='mw-content-text']"));
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[@id='firstHeading']")));
        String contentOfWikipediaSearchResult = wikipediaSearchResult.getText();
        Assert.assertTrue(contentOfWikipediaSearchResult.contains("Test Automation"), "The search results are not contain expected text.");
    }
}

