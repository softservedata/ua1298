package com.softserve.edu05we;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class GoogleTest {

    @Test
    public void seleniumPage() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //
        driver.get("https://www.google.com");
        WebElement searchElement = driver.findElement(By.name("q"));
        searchElement.sendKeys("Selenium download");
        searchElement.submit();
        //
        Thread.sleep(10000);
        //
        driver.findElement(By.partialLinkText("Selenium")).click();  // Error
        //
        driver.quit();
    }

    @Test
    public void seleniumPage2() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        //WebDriver driver = new ChromeDriver();
        //
        ChromeOptions options = new ChromeOptions();
        // chrome command line options
        // https://peter.sh/experiments/chromium-command-line-switches/
        options.addArguments("--start-maximized");
        options.addArguments("--no-proxy-server");
        // options.addArguments("--no-sandbox");
        options.addArguments("--disable-web-security");
        options.addArguments("--ignore-certificate-errors");
        //options.addArguments("--start-fullscreen"); // F11
        // options.addArguments("--disable-extensions");
        WebDriver driver = new ChromeDriver(options);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //
        driver.get("https://www.google.com");
        WebElement searchElement = driver.findElement(By.name("q"));
        searchElement.sendKeys("Selenium download");
        searchElement.submit();
        //
        Thread.sleep(10000);
        //
        driver.findElement(By.partialLinkText("Selenium")).click();  // Error
        //
        driver.quit();
    }

}
