package homwork10;


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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestSamples3 {
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
    @FindBy(css = ".body-2.user-name")
    private WebElement result;
    @FindBy(css = ".alert-general-error")
    private WebElement errorMessage;
    @FindBy(xpath = "//div[@id='password-err-msg']/div")
    private WebElement errorPassword;
    @FindBy(xpath = "//div[@id='email-err-msg']/div")
    private WebElement errorEmail;

    private final String BASE_URL = "https://www.greencity.cx.ua/#/greenCity";
    private WebDriver driver;
    private int oneSec = 1;

    @BeforeAll
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(oneSec * 5L));
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void initPageElements() {
        PageFactory.initElements(driver, this);
        closeIframeIfExists();
    }

    @Test
    public void verifyTitleTest() {
        assertTrue(Objects.requireNonNull(driver.getTitle()).contains("GreenCity"));
        assertEquals("GreenCity - Build Eco-Friendly Habits Today", driver.getTitle());
    }

    private void closeIframeIfExists() {
        List<WebElement> iframe = driver.findElements(By.cssSelector("iframe"));
        if (!iframe.isEmpty()) {
            driver.switchTo().frame(iframe.getFirst());
            List<WebElement> popupButton = driver.findElements(By.id("close"));
            if (!popupButton.isEmpty()) {
                popupButton.getFirst().click();
            }
            driver.switchTo().defaultContent();
        }
    }

    @ParameterizedTest
    @CsvSource({
            "samplestest@greencity.com, weyt3$Guew^",
            "anotheruser@greencity.com, anotherpassword"
    })
    public void positiveSignIn(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();

        assertEquals("Welcome back!", welcomeText.getText());
        assertEquals("Please enter your details to sign in.", signInDetailsText.getText());

        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();

        assertTrue(result.isDisplayed());
        assertEquals("You have successfully signed in", result.getText());
    }

    @ParameterizedTest
    @CsvSource({
            "invalid-email.com, validPassword123, Please check if the email is written correctly",
            "samplestest@greencity.com, wrongPassword123, Invalid email or password",
            ", validPassword123, Email cannot be empty",
            "samplestest@greencity.com, , Password cannot be empty"
    })
    public void negativeSignIn(String email, String password, String expectedErrorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();

        if (email != null) emailInput.sendKeys(email);
        if (password != null) passwordInput.sendKeys(password);

        signInSubmitButton.click();

        String actualErrorMessage = errorMessage.isDisplayed() ? errorMessage.getText() : "";
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}