package com.softserve.edu.framework.tests;

import com.softserve.edu.framework.lib.SignInUI;
import com.softserve.edu.framework.lib.WebUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;


public class TestRunner {
    @FindBy(css = ".header_sign-in-link.tertiary-global-button")
    protected WebElement signInButton;
    @FindBy(css = "div.right-side h1")
    protected WebElement welcomeText;
    @FindBy(css = "div.right-side h2")
    protected WebElement signInDetailsText;
    @FindBy(css = "label[for='email']")
    protected WebElement emailLabel;
    @FindBy(id = "email")
    protected WebElement emailInput;
    @FindBy(css = "label[for='password']")
    protected WebElement passwordLabel;
    @FindBy(id = "password")
    protected WebElement passwordInput;
    @FindBy(css = ".greenStyle")
    protected WebElement signInSubmitButton;
    @FindBy(css = ".wrapper")
    protected WebElement signInForm;


    @FindBy(css="a.header_user-name")
    protected WebElement result;
    @FindBy(css = "div.alert-general-error.ng-star-inserted")
    protected WebElement errorMessage;
    @FindBy(css = "div.validation-password-error.ng-star-inserted div")
    protected WebElement errorPassword;
    @FindBy(css = "div#email-err-msg div")
    protected WebElement errorEmail;
    @FindBy(xpath = "//li[@class='drop-down-item']/a[contains(text(), 'Sign out')]")
    protected WebElement signOutButton;

    @FindBy(css = ".cross-btn")
    protected WebElement crossButton;


    protected static final String BASE_URL = "http://localhost:4205/#/greenCity";
    protected static WebDriver driver;
    public static final long oneSec = 1;
    public static final long threeSec = 3;
    public static final long fiveSec = 5;
    public static final long tenSec = 10;
    public static final long pageLoadSec = 50;
    public static final long scriptSec = 20;
    public static boolean isTestSuccessful = false;
    protected static final String BROWSER_LABEL = "browser";
    Logger logger = LoggerFactory.getLogger(this.getClass());
    protected static Dotenv dotenv;
    protected static WebUtils webUtils;
    protected static SignInUI signInUI;


    private static void initializeBrowser(){
        switch (dotenv.get(BROWSER_LABEL)){
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver();
                driver = new ChromeDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(tenSec));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(scriptSec));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadSec));
        driver.manage().window().maximize();
    }

    private static void setWebPart(){
         webUtils = new WebUtils(driver);
         signInUI = new SignInUI(driver);
    }

    private static void setEnv(){
        dotenv = Dotenv.configure().directory("target/test-classes").load();
    }

    @BeforeAll
    static void setup(){
        setEnv();
        initializeBrowser();
        setWebPart();
    }

    @AfterAll
    static void tearDown(){
        driver.quit();
    }

    @BeforeEach
    public void setupThis() {
        PageFactory.initElements(driver, this);
        driver.get(BASE_URL);
    }

    @AfterEach
    public void tearThis(TestInfo testInfo) {
        if (!isTestSuccessful) {
            logger.error(() -> "Test_Name = " + testInfo.getTestMethod().toString() + " failed");
        } else {
            logger.info(() -> "Test " + testInfo.getTestMethod() + " done.");
        }
        // delete session
        driver.manage().deleteAllCookies();
    }
}
