package com.softserve.framework.tests;

import com.google.gson.Gson;
import com.softserve.framework.lib.RegisterUI;
import com.softserve.framework.lib.WebUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@ExtendWith(com.softserve.framework.tests.ResultExtension.class)
public abstract class TestRunner {
    protected static final String BASE_URL = "https://www.greencity.cx.ua/#/ubs";
    protected static final String POST_URL = "https://greencity-user.greencity.cx.ua/api/testers/sign-in";
    public static final int MAX_IMPLICITLY_WAIT = 10;
    public static final int MIN_IMPLICITLY_WAIT = 2;
    private static final Long ONE_SECOND_DELAY = 1000L;
    private static final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";
    private static final String BROWSER_NAME = "browser";
    protected static WebDriver driver;
    protected static Boolean isTestSuccessful = false;
    //
    protected static RegisterUI registerUI;
    protected static WebUtils webUtils;
    protected static Dotenv dotenv;

    public static void presentationSleep() {
        presentationSleep(1);
    }

    // Overload
    protected static void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void takeScreenShot() {
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./" + currentTime + "_screenshot.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void takePageSource() {
        //String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        //
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_TEMPLATE);
        String currentTime = localDate.format(formatter);
        //
        String pageSource = driver.getPageSource();
        byte[] strToBytes = pageSource.getBytes();
        Path path = Paths.get("./" + currentTime + "_" + "_source.html.txt");
        try {
            Files.write(path, strToBytes, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initWebDriver() {
        switch (dotenv.get(BROWSER_NAME).toLowerCase()) {
            case "firefox":
                // Firefox
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "chrome":
            default:
                // Chrome
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }
        //
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(MAX_IMPLICITLY_WAIT)); // 0 by default
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(100)); // 30 by default
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(180)); // 300 by default
        driver.manage().window().maximize();
    }

    private static void initUI() {
        registerUI = new RegisterUI(driver);
        webUtils = new WebUtils(driver);
    }

    private static void initEnv() {
        dotenv = Dotenv.load();
    }

    @BeforeAll
    public static void setup() {
        initEnv();
        initWebDriver();
        initUI();
    }

    @AfterAll
    public static void tear() {
        presentationSleep(4); // For Presentation ONLY
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeEach
    public void setupThis() {
        driver.get(BASE_URL);
        presentationSleep();
    }

    @AfterEach
    public void tearThis(TestInfo testInfo) {
        if (!isTestSuccessful) {
            // TODO use logging
            System.out.println("\t\t\tgetTestMethod = " + testInfo.getTestMethod());
            System.out.println("\t\t\tgetDisplayName = " + testInfo.getDisplayName());
            takeScreenShot();
            takePageSource();
        }
        // delete session
        driver.manage().deleteAllCookies(); // clear cache; delete cookie; delete session;
        webUtils.removeItemLocalStorage("accessToken");
        webUtils.removeItemLocalStorage("refreshToken");
        presentationSleep(); // For Presentation ONLY
    }

}
