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

public class ChromeDriverTests extends BaseTest {

    @Test
    public void chromeBrowserTests() throws NullPointerException, IOException {
        driver.get("https://google.com");
        driver.manage().window().maximize();
        WebElement googleSearchInput = driver.findElement(By.name("q"));
        googleSearchInput.sendKeys("wikipedia");
        WebElement googleSearchButton = driver.findElement(By.xpath("//*[@class='iblpc']"));
        googleSearchButton.submit();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='Tg7LZd']")));
        WebElement clickLink = driver.findElement(By.xpath("(.//a[contains(@href,'wikipedia.org')])[1]"));
        Assert.assertTrue(clickLink.getText().contains("wikipedia.org"), "clickLink NOT contains 'wikipedia.org' text");

        clickLink.click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.urlContains("wikipedia.org"));
        Assert.assertTrue(true, String.valueOf(driver.findElement(By.xpath("(.//a[contains(@accesskey,'z')])[1]"))));

        String wikiPageTitle = driver.getTitle();
        logger.info(String.format("Wiki Page Title: %s", wikiPageTitle));
        int wikiTitleLength = driver.getTitle().length();
        logger.info(String.format("Wiki Page Title Length: %s", wikiTitleLength));
        String wikiPageURL = driver.getCurrentUrl();
        Assert.assertTrue(wikiPageURL.contains("wikipedia.org"), "Current URL is incorrect.");

        logger.info(String.format("The current page URL: %s", wikiPageURL));
        int wikiPageLength = driver.getPageSource().length();
        logger.info(String.format("Wiki Page Source Length: %s", wikiPageLength));
        WebElement screenshotOfTheFirstDYKSectionElement = driver.findElement(By.xpath("//div[contains(@id,'dyk')]//img"));
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver, screenshotOfTheFirstDYKSectionElement);
        ImageIO.write(screenshot.getImage(), "jpg", new File("target\\ElementScreenshot.jpg"));
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