package homwork10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TestSamples3 {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://localhost:4205/#/greenCity");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeEach
    public void initPageElements() {
        PageFactory.initElements(driver, this);
        switchToEnglish();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @FindBy(xpath = "//img[contains(@class, 'ubs-header-sing-in-img') and @alt='sing in button']")
    private WebElement signInButton;

    @FindBy(xpath = "//input[@id='email' and @type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='password' and @type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(@class, 'greenStyle') and @type='submit']")
    private WebElement signInSubmitButton;

    @FindBy(xpath = "//div[contains(@class, 'mat-tab-label-content') and contains(text(), 'My habits')]")
    private WebElement myHabitsTab;

    @FindBy(xpath = "//a[contains(@class, 'header_user-name')]")
    private WebElement userMenuButton;

    @FindBy(xpath = "//li[@role='button' and contains(@aria-label, 'sign-out')]")
    private WebElement signOutButton;

    @FindBy(xpath = "//div[@id='email-err-msg']//div[contains(text(), 'Please check that your e-mail address is indicated correctly')]")
    private WebElement errorEmail;

    @FindBy(xpath = "//div[contains(@class, 'alert-general-error') and contains(text(), 'Bad email or password')]")
    private WebElement errorPassword;

    @FindBy(xpath = "//a[@class='close-modal-window']")
    private WebElement closeModalButton;

    @FindBy(xpath = "//ul[contains(@class, 'header_lang')]//li[@role='option' and contains(@aria-label, 'english')]")
    private WebElement languageSwitcher;

    @FindBy(xpath = "//span[text()='En']")
    private WebElement englishOption;

    public void switchToEnglish() {
        languageSwitcher.click();
        englishOption.click();
    }

    @Test
    @DisplayName("Verify title of the page.")
    public void verifyTitle() {
        Assertions.assertEquals("GreenCity", driver.getTitle());
    }

    @ParameterizedTest
    @CsvSource({
            "samplestest@greencity.com, weyt3$Guew^",
            "anotheruser@greencity.com, anotherpassword"
    })
    @DisplayName("Verify valid sign in and sign out.")
    public void testValidSignInAndSignOut(String email, String password) {
        signInButton.click();
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(signInSubmitButton)).click();
        Assertions.assertTrue(wait.until(ExpectedConditions.visibilityOf(myHabitsTab)).isDisplayed());
        userMenuButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(signOutButton)).click();
        Assertions.assertTrue(wait.until(ExpectedConditions.visibilityOf(signInButton)).isDisplayed());
    }

    @ParameterizedTest
    @CsvSource({
            "samplestesgreencity.com, uT346^^^erw, Please check that your e-mail address is indicated correctly",
            "validemail@example.com, gfhjkm12345678, Bad email or password"
    })
    @DisplayName("Verify invalid sign in.")
    public void testInvalidSignIn(String email, String password, String expectedMessage) {
        signInButton.click();
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(signInSubmitButton)).click();
        if (email.contains("samplestesgreencity.com")) {
            Assertions.assertEquals(expectedMessage, wait.until(ExpectedConditions.visibilityOf(errorEmail)).getText());
        } else {
            Assertions.assertEquals(expectedMessage, wait.until(ExpectedConditions.visibilityOf(errorPassword)).getText());
        }
        wait.until(ExpectedConditions.elementToBeClickable(closeModalButton)).click();
    }
}
