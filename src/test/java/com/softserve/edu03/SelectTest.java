package com.softserve.edu03;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SelectTest {
    private final String PATH_SEPARATOR = "/";
    private final String filename = "page.html";
    private final String protocol = "file://";
    private String baseUrl;
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
        //
        baseUrl = protocol + this.getClass().getResource(PATH_SEPARATOR + filename).getPath();
        System.out.println("baseUrl = " + baseUrl);
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
        driver.get(baseUrl);
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
    public void checkExistWebElement() {
        /*
        WebElement select = driver.findElement(By.id("optionSelect"));
        List<WebElement> allOptions = select.findElements(By.tagName("option"));
        for (WebElement option : allOptions) {
            System.out.println("option.getText() = " + option.getText() + "  attribute = " + option.getAttribute("value"));
            option.click();
            presentationSleep(); // For Presentation ONLY
        }
        */
        //
        Select select = new Select(driver.findElement(By.id("optionSelect")));
        select.selectByIndex(1);
        presentationSleep(); // For Presentation ONLY
        //
        select.selectByVisibleText("Option 3");
        presentationSleep(); // For Presentation ONLY
        //
        WebElement radioThat = driver.findElement(By.id("radioThatInput"));
        System.out.println("1. radioThat.isSelected() = " + radioThat.isSelected());
        presentationSleep(); // For Presentation ONLY
        radioThat.click();
        System.out.println("2. radioThat.isSelected() = " + radioThat.isSelected());
        presentationSleep(); // For Presentation ONLY
        System.out.println("font-weight = " + radioThat.getCssValue("font-weight"));
        //
        presentationSleep(2); // For Presentation ONLY
    }
}
