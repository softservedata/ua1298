package com.softserve.edu03;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WebElemTest {
    private final String BASE_URL = "https://demo.opencart.com/index.php";
    private final long IMPLICITLY_WAIT_SECONDS = 10L;
    private final long ONE_SECOND_DELAY = 1000L;
    private WebDriver driver;

    private void presentationSleep() {
        presentationSleep(1);
    }

    private void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @BeforeAll
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
        // WebDriverManager.firefoxdriver().setup();
        //
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS)); // 0 by default
        driver.manage().window().maximize();
    }

    @AfterAll
    public void afterClass() {
        presentationSleep(); // For Presentation ONLY
        if (driver != null) {
            driver.quit(); // close()
            // driver.close();
        }
    }

    @BeforeEach
    public void beforeMethod() {
        driver.get(BASE_URL);
        presentationSleep(); // For Presentation ONLY
    }

    @AfterEach
    public void afterMethod() {
        presentationSleep(); // For Presentation ONLY
        // logout;
        // Save Screen;
        //
        // Take Screenshot, save sourceCode, save to log, prepare report, Return to;
        // previous state, logout, etc.
        // takeScreenShot(testName);
        // takePageSource(testName);
        driver.manage().deleteAllCookies(); // clear cache; delete cookie; delete session;
        //
        // driver.findElement(By.cssSelector("#logo .img-responsive")).click();
        // driver.findElement(By.cssSelector("#logo > a")).click();
        //driver.findElement(By.xpath("//img[contains(@src, '/logo.png')]/..")).click();
        presentationSleep(); // For Presentation ONLY
    }

    @Test
    public void checkExistWebElement() throws Exception {
        //
        System.out.println("1. isVisible Login Link = "
                + driver.findElement(By.cssSelector("a[href*='route=account/login']")).isDisplayed()); // false
        presentationSleep(); // For Presentation ONLY
        //
        // My Account
        driver.findElement(By.cssSelector("div.nav.float-end a.dropdown-toggle")).click();
        presentationSleep(); // For Presentation ONLY
        //
        System.out.println("2. isVisible Login Link = "
                + driver.findElement(By.cssSelector("a[href*='route=account/login']")).isDisplayed()); // true
        presentationSleep(); // For Presentation ONLY
        //
        // Click Login
        driver.findElement(By.cssSelector("a[href*='route=account/login']")).click();
        presentationSleep(); // For Presentation ONLY
        //
        // /*-
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys("hahaha");
        //
        String content = driver.findElement(By.id("input-email")).getAttribute("value");
        System.out.println("***content email = " + content);
        presentationSleep(2); // For Presentation ONLY
        //
        // Refresh some webelements
        driver.navigate().refresh();
        presentationSleep(); // For Presentation ONLY
        //
        driver.findElement(By.id("input-email")).sendKeys("bebebe");
        presentationSleep(); // For Presentation ONLY
        // */
        /*-
        WebElement email = driver.findElement(By.id("input-email"));
        //
        email.click();
        email.clear();
        email.sendKeys("hahaha");
        String content = email.getAttribute("value");
        System.out.println("content email = " + content); // Ok
        //System.out.println("content email = " + email.getAttribute("value")); // invalid solution
        presentationSleep(); // For Presentation ONLY
        //
        // Refresh some webelements
        driver.navigate().refresh();
        presentationSleep(); // For Presentation ONLY
        email.sendKeys("bebebe"); // Runtime Error
        presentationSleep(); // For Presentation ONLY
        */
        //
        // driver.findElement(By.id("input-email")).sendKeys("password");
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys("password");
        //
        content = driver.findElement(By.id("input-password")).getAttribute("value");
        System.out.println("content password = " + content);
        presentationSleep(2); // For Presentation ONLY
        //
    }


}
