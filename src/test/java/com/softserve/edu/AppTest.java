package com.softserve.edu;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class AppTest {
    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private static WebDriver driver;


    @BeforeAll
    public static void setup() {
        // WebDriverManager.chromedriver().setup();
        // driver = new ChromeDriver();
        //
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        //
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // 0 by default
        driver.manage().window().maximize();
        System.out.println("@BeforeAll executed");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        System.out.println("@AfterAll executed");
    }

    @BeforeEach
    public void setupThis() {
        System.out.println("\t@BeforeEach executed");
    }

    @AfterEach
    public void tearThis() {
        System.out.println("\t@AfterEach executed");
    }


    @Test
    public void simplePrint() {
        //System.out.println("start");
        //int i = 1 / 0;
        System.out.println("\t\tdone");
        Assertions.assertEquals(4, 2 + 2);
    }

    @Test
    public void checkNews() throws InterruptedException {
        //System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        //
        //WebDriver driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS)); // 0 by default
        //driver.manage().window().maximize();
        //
        driver.get("https://www.greencity.cx.ua/");
        Thread.sleep(4000); // For Presentation
        //
        // Switch to Ggreencity
        driver.findElement(By.cssSelector("app-header:nth-child(1) .nav-left-list:nth-child(4) > .url-name")).click();
        Thread.sleep(1000); // For Presentation
        //
        // Check Language
        String lang = driver.findElement(By.cssSelector("ul[aria-label='language switcher'] span")).getText();
        if (lang.toLowerCase().equals("ua")) {
            // Open Dropdown Language
            driver.findElement(By.cssSelector(".header_arrow")).click();
            Thread.sleep(1000); // For Presentation
            //
            // Click EN
            driver.findElement(By.cssSelector(".ng-star-inserted > span")).click();
            Thread.sleep(1000); // For Presentation
        }
        //
        driver.findElement(By.cssSelector(".nav-left-list:nth-child(1) > .url-name")).click();
        Thread.sleep(1000); // For Presentation
        //
        driver.findElement(By.cssSelector(".tag-button:nth-child(3) .text")).click();
        Thread.sleep(1000); // For Presentation
        //
        //driver.findElement(By.cssSelector(".gallery-view-li-active:nth-child(3) .added-data p")).click();
        driver.findElement(By.xpath("//h3[contains(text(), 'Green Horizons')]")).click();
        Thread.sleep(1000); // For Presentation
        //
        driver.findElement(By.cssSelector(".app-wrapper")).click();
        Thread.sleep(1000); // For Presentation
        //
        driver.findElement(By.cssSelector(".ql-editor")).click();
        Thread.sleep(1000); // For Presentation
        //
        Assertions.assertEquals("Explore the cutting edge of sustainability, climate action, and eco-innovation with Green Horizons. Discover inspiring stories, groundbreaking research, and simple actions that can help reshape our world into a greener, more resilient place for future generations",
                driver.findElement(By.cssSelector(".ql-editor > p")).getText() );
        //
        Thread.sleep(4000); // For Presentation
        //driver.quit();
    }

}
