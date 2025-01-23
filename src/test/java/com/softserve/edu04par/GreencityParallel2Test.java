package com.softserve.edu04par;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Execution(ExecutionMode.CONCURRENT)
public class GreencityParallel2Test {
    private static final String BASE_URL = "https://www.greencity.cx.ua/#/ubs";
    public static final int MAX_IMPLICITLY_WAIT = 10;
    //private static WebDriver driver;
    //private static Map<Long,WebDriver> drivers;
    private static ThreadLocal<WebDriver> drivers;

    private WebDriver getDriver() {
        //WebDriver currentDriver = drivers.get(Thread.currentThread().getId());
        WebDriver currentDriver = drivers.get();
        if (currentDriver == null) {
            currentDriver = new ChromeDriver();
            currentDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(MAX_IMPLICITLY_WAIT));
            currentDriver.manage().window().maximize();
            //drivers.put(Thread.currentThread().getId(),currentDriver);
            drivers.set(currentDriver);
        }
        return currentDriver;
    }

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
        //
        //WebDriverManager.firefoxdriver().setup();
        //driver = new FirefoxDriver();
        //
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(MAX_IMPLICITLY_WAIT));
        //driver.manage().window().maximize();
        //
        //drivers = new HashMap<>();
        drivers = new ThreadLocal<>();
        //
        System.out.println("@BeforeAll executed, ThreadId = " + Thread.currentThread().getId());
    }

    @AfterAll
    public static void tearDown() {
//        if (driver != null) {
//            driver.quit();
//            //driver.close();
//        }
        /*
        if (drivers != null) {
            for(Map.Entry<Long,WebDriver> entry : drivers.entrySet()) {
                entry.getValue().quit();
            }
        }
        */
        System.out.println("@AfterAll executed, ThreadId = " + Thread.currentThread().getId());
    }

    @BeforeEach
    public void setupThis() {
        getDriver().get(BASE_URL);
        //
        System.out.println("\t@BeforeEach executed, ThreadId = " + Thread.currentThread().getId());
    }

    @AfterEach
    public void tearThis() throws InterruptedException {
        // logout, clear cookies, delete token
        Thread.sleep(10000); // For Presentation ONLY;
        //
        if (drivers.get() != null) {
            drivers.get().quit();
        }
        //
        System.out.println("\t@AfterEach executed, ThreadId = " + Thread.currentThread().getId());
    }

    @Test
    public void checkLogin1() throws InterruptedException {
        //WebDriver driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(MAX_IMPLICITLY_WAIT));
        //driver.manage().window().maximize();
        //driver.get(BASE_URL);
        //
        System.out.println("\t\t@Test checkLogin1(), ThreadId = " + Thread.currentThread().getId());
        //
        getDriver().findElement(By.cssSelector("div.main-content.app-container img.ubs-header-sing-in-img.ng-star-inserted")).click();
        Thread.sleep(2000); // For Presentation ONLY
        //
        //driver.findElement(By.cssSelector(".main-picture")).click();
        //Thread.sleep(1000); // For Presentation ONLY
        // /*
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        List<WebElement> iframe = getDriver().findElements(By.cssSelector("iframe"));
        System.out.println("\t\t\t\tiframe.size() = " + iframe.size());
        if (iframe.size() > 0) {
            getDriver().switchTo().frame(iframe.get(0));
            List<WebElement> popupButton = getDriver().findElements(By.id("close"));
            System.out.println("\t\t\t\tpopupButton.size() = " + popupButton.size());
            if (popupButton.size() > 0) {
                popupButton.get(0).click();
            }
            getDriver().switchTo().defaultContent();
        }
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(MAX_IMPLICITLY_WAIT));
        Thread.sleep(1000); // For Presentation ONLY
        //
        getDriver().findElement(By.id("email")).click();
        getDriver().findElement(By.id("email")).clear();
        Thread.sleep(1000); // For Presentation ONLY
        //
        getDriver().findElement(By.id("email")).sendKeys("tyv09754@zslsz.com");
        Thread.sleep(1000); // For Presentation ONLY
        //
        getDriver().findElement(By.id("password")).click();
        getDriver().findElement(By.id("password")).clear();
        Thread.sleep(1000); // For Presentation ONLY
        //
        getDriver().findElement(By.id("password")).sendKeys("password");
        Thread.sleep(1000); // For Presentation ONLY
        //
        // /*
        // From OS
        String javaHome = System.getenv("JAVA_HOME");
        System.out.println("\t\t\tSystem.getenv(\"JAVA_HOME\") = " + javaHome);
        //
        // From Eclipse/Idea
        //String password = System.getenv().get("MY_PASSWORD");
        String password = "Qwerty";
        System.out.println("\t\t\tpassword = " + password);
        //driver.findElement(By.id("password")).sendKeys("Qwerty_1"); // Invalid Solution
        getDriver().findElement(By.id("password")).sendKeys(password);
        Thread.sleep(1000); // For Presentation ONLY
        //
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("document.querySelector('button.ubsStyle').removeAttribute('disabled')");
        Thread.sleep(2000); // For Presentation ONLY
        //
        getDriver().findElement(By.cssSelector("button.ubsStyle")).click();
        Thread.sleep(1000); // For Presentation ONLY
        //
        //driver.quit();
    }

    @Test
    public void checkLogin2() throws InterruptedException {
        //WebDriver driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(MAX_IMPLICITLY_WAIT));
        //driver.manage().window().maximize();
        //driver.get(BASE_URL);
        //
        System.out.println("\t\t@Test checkLogin2(), ThreadId = " + Thread.currentThread().getId());
        //
        getDriver().findElement(By.cssSelector("div.main-content.app-container img.ubs-header-sing-in-img.ng-star-inserted")).click();
        Thread.sleep(2000); // For Presentation ONLY
        //
        //driver.findElement(By.cssSelector(".main-picture")).click();
        //Thread.sleep(1000); // For Presentation ONLY
        // /*
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        List<WebElement> iframe = getDriver().findElements(By.cssSelector("iframe"));
        System.out.println("\t\t\t\tiframe.size() = " + iframe.size());
        if (iframe.size() > 0) {
            getDriver().switchTo().frame(iframe.get(0));
            List<WebElement> popupButton = getDriver().findElements(By.id("close"));
            System.out.println("\t\t\t\tpopupButton.size() = " + popupButton.size());
            if (popupButton.size() > 0) {
                popupButton.get(0).click();
            }
            getDriver().switchTo().defaultContent();
        }
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(MAX_IMPLICITLY_WAIT));
        Thread.sleep(1000); // For Presentation ONLY
        //
        getDriver().findElement(By.id("email")).click();
        getDriver().findElement(By.id("email")).clear();
        Thread.sleep(1000); // For Presentation ONLY
        //
        getDriver().findElement(By.id("email")).sendKeys("tyv09754@zslsz.com");
        Thread.sleep(1000); // For Presentation ONLY
        //
        getDriver().findElement(By.id("password")).click();
        getDriver().findElement(By.id("password")).clear();
        Thread.sleep(1000); // For Presentation ONLY
        //
        getDriver().findElement(By.id("password")).sendKeys("password");
        Thread.sleep(1000); // For Presentation ONLY
        //
        // /*
        // From OS
        String javaHome = System.getenv("JAVA_HOME");
        System.out.println("\t\t\tSystem.getenv(\"JAVA_HOME\") = " + javaHome);
        //
        // From Eclipse/Idea
        //String password = System.getenv().get("MY_PASSWORD");
        String password = "Qwerty";
        System.out.println("\t\t\tpassword = " + password);
        //driver.findElement(By.id("password")).sendKeys("Qwerty_1"); // Invalid Solution
        getDriver().findElement(By.id("password")).sendKeys(password);
        Thread.sleep(1000); // For Presentation ONLY
        //
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("document.querySelector('button.ubsStyle').removeAttribute('disabled')");
        Thread.sleep(2000); // For Presentation ONLY
        //
        getDriver().findElement(By.cssSelector("button.ubsStyle")).click();
        Thread.sleep(1000); // For Presentation ONLY
        //
        //driver.quit();
    }

}
