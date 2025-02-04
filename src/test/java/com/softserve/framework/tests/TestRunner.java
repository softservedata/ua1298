package com.softserve.framework.tests;

import com.google.gson.Gson;
import com.softserve.framework.lib.RegisterUI;
import com.softserve.framework.lib.WebUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@ExtendWith(com.softserve.framework.tests.ResultExtension.class)
public abstract class TestRunner {
    protected static final String BASE_URL = "https://www.greencity.cx.ua/#/ubs";
    protected static final String POST_URL = "https://greencity-user.greencity.cx.ua/api/testers/sign-in";
    public static final int MAX_IMPLICITLY_WAIT = 10;
    public static final int MIN_IMPLICITLY_WAIT = 2;
    private static final Long ONE_SECOND_DELAY = 1000L;
    protected static WebDriver driver;
    protected static Boolean isTestSuccessful = false;
    //
    protected static RegisterUI registerUI;
    protected static WebUtils webUtils;

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

    private static void initWebDriver() {
        // Chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //
        // Firefox
        //WebDriverManager.firefoxdriver().setup();
        //driver = new FirefoxDriver();
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

    @BeforeAll
    public static void setup() {
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
        }
        // delete session
        driver.manage().deleteAllCookies(); // clear cache; delete cookie; delete session;
        webUtils.removeItemLocalStorage("accessToken");
        webUtils.removeItemLocalStorage("refreshToken");
        presentationSleep(); // For Presentation ONLY
    }

}
