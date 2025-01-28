package com.softserve.edu;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GreenCityDockerSigningInTest {
    @FindBy(css = ".header_sign-in-link.tertiary-global-button")
    private WebElement signInButton;
    @FindBy(css = "div.right-side h1")
    private WebElement welcomeText;
    @FindBy(css = "div.right-side h2")
    private WebElement signInDetailsText;
    @FindBy(css = "label[for='email']")
    private WebElement emailLabel;
    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(css = "label[for='password']")
    private WebElement passwordLabel;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(css = ".greenStyle")
    private WebElement signInSubmitButton;


    @FindBy(css="header_user-name")
    private WebElement result;
    @FindBy(css = ".alert-general-error")
    private WebElement errorMessage;
    @FindBy(xpath = "div#password-err-msg div")
    private WebElement errorPassword;
    @FindBy(xpath = "div#email-err-msg div")
    private WebElement errorEmail;
    @FindBy(css = "li.lang-option span")
    private WebElement curLang;
    @FindBy(css = "li.ng-star-inserted[aria-label='En'] span")
    private WebElement lang;

    private static final String BASE_URL = "http://localhost:4205/#/greenCity";
    private static WebDriver driver;
    private static long oneSec = 1;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-blink-features=AutomationControlled");
//        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
//        options.setExperimentalOption("useAutomationExtension", false);
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(oneSec * 5L));
        driver.manage().window().maximize();
    }
    @BeforeEach
    public void initPageElements() {
        PageFactory.initElements(driver, this);
    }
    @Test
    public void verifyTitleTest() {
        assertTrue(Objects.requireNonNull(driver.getTitle()).contains("GreenCity"));
        assertEquals("GreenCity - Build Eco-Friendly Habits Today", driver.getTitle());
    }

    private void closeIframeIfExists(){
        List<WebElement> iframe = driver.findElements(By.cssSelector("iframe"));
        if(!iframe.isEmpty()){
            driver.switchTo().frame(iframe.getFirst());
            List<WebElement> popupButton = driver.findElements(By.id("close"));
            if(!popupButton.isEmpty()){
                popupButton.getFirst().click();
            }
            driver.switchTo().defaultContent();
        }
    }

    private void changeLangToEn(){
        if(!Objects.equals(curLang.getDomProperty("value"), "En")){
            curLang.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(oneSec));
            lang.click();
        }
    }

    @Test
    public void signInWithValidDataTest() throws InterruptedException {
        //arrange
        String login = System.getenv("USER_LOGIN");
        String pass = System.getenv("USER_PASSWORD");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        //act
        changeLangToEn();
        signInButton.click();
        closeIframeIfExists();

//        assertEquals(welcomeText.getText(), "Welcome back!");
//        assertEquals(signInDetailsText.getText(), "Please enter your details to sign in.");
//        assertEquals(emailLabel.getText(), "Email");
        emailInput.clear();
        emailInput.sendKeys(login);
        assertEquals(emailInput.getDomProperty("value"), login);
        passwordInput.clear();
        passwordInput.sendKeys(pass);
        assertEquals(passwordInput.getDomProperty("value"), pass);
        signInSubmitButton.click();
        WebElement userBox = wait.until(ExpectedConditions.visibilityOf(result));
        assertTrue(userBox.isDisplayed());
    }
    @ParameterizedTest
    @CsvSource({
            "Please check if the email is written correctly"
    })
    public void signInNotValidTest(String message) {
        signInButton.click();
        emailInput.sendKeys("samplestesgreencity.com");
        passwordInput.sendKeys("uT346^^^erw");
        assertEquals(errorEmail.getText(), message);
    }
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

}
