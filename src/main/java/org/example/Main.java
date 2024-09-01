package org.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Main {
    public static WebDriver browser;
    public static final String URL = "https://magento.softwaretestingboard.com/";
    public static final int SECONDS_WAIT_TIME_FOR_ELEMENT = 10;

    public static void main(String[] args) {}

    public static void setup(String url){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver-win64/chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-search-engine-choice-screen");

        browser = new ChromeDriver(chromeOptions);
        browser.get(url);
    }

    public static void closeBrowser(){
        browser.quit();
    }

    public static void waitForElementVisibility(By by){
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(SECONDS_WAIT_TIME_FOR_ELEMENT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForUrlChange(String Url){
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(SECONDS_WAIT_TIME_FOR_ELEMENT));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(Url)));
    }

    public static void waitForElementToBeClickable(By by){
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(SECONDS_WAIT_TIME_FOR_ELEMENT));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static WebElement findElement(By by){
        return browser.findElement(by);
    }

    public static String getText(By by){
        waitForElementVisibility(by);
        return findElement(by).getText();
    }

    public static String getValue(By by){
        waitForElementVisibility(by);
        return findElement(by).getAttribute("value");
    }

    public static void clickOnElement(By by){
        waitForElementVisibility(by);
        waitForElementToBeClickable(by);
        findElement(by).click();
    }

    public static void sendKeys(By by, String key){
        waitForElementVisibility(by);
        findElement(by).clear();
        Actions actions = new Actions(browser);
        actions.sendKeys(findElement(by), key).perform();
    }

    public static void hoverOverElement(By by){
        waitForElementVisibility(by);
        Actions actions = new Actions(browser);
        actions.moveToElement(findElement(by)).build().perform();
    }

    public static void actionClickOnElement(By by){
        waitForElementVisibility(by);
        Actions actions = new Actions(browser);
        actions.click(findElement(by)).build().perform();
    }

    public static void scrollToPageEnd(){
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void scrollToPageTop(){
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("window.scrollTo(0, 0)");
    }

    public static void scrollTillElementIsFound(By by){
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("arguments[0].scrollIntoView();", findElement(by));
    }

    public static void selectDropDownByValue(By by, String value){
        WebElement element = findElement(by);
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public static void selectDropDownByIndex(By by, int value){
        WebElement element = findElement(by);
        Select select = new Select(element);
        select.selectByIndex(value);
    }

    public static void takeScreenshot(){
        String name = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        File imgFile = ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(imgFile, new File("screenshots/" + name + "_screenshot.png"));
        }catch (Exception ex){
            System.out.println("Couldn't take a screenshot: " + ex.getMessage());
        }
    }

}