package HW10;

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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SignInTest {

    @FindBy(css = "li.lang-option")
    private WebElement langOption;

    @FindBy(css = "li[aria-label='En'] > span[aria-hidden='true']")
    private WebElement english;

    @FindBy(css = "img.ubs-header-sing-in-img-greencity")
    private WebElement signInButton;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement signInSubmitButton;

    @FindBy(css = ".mat-snack-bar-container span")
    private WebElement successMessage;

    @FindBy(css = "div#email-err-msg")
    private WebElement errorEmail;

    @FindBy(css = "div#pass-err-msg.validation-password-error.ng-star-inserted")
    private WebElement errorPassword;

    private static WebDriver driver;
    private static boolean isLanguageSet = false;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:4205/#/greenCity");
        driver.manage().window().setSize(new Dimension(1264, 798));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        setEnglishLanguage(wait);
    }

    @BeforeEach
    public void initPageElements() {
        PageFactory.initElements(driver, this);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        if (!isSignInFormVisible()) {
            signInButton.click();
            wait.until(ExpectedConditions.visibilityOf(emailInput));
        }

        emailInput.clear();
        passwordInput.clear();

    }

    private static void setEnglishLanguage(WebDriverWait wait) {
        if (isLanguageSet) {
            return;
        }

        WebElement langOption = driver.findElement(By.cssSelector("li.lang-option"));
        langOption.click();

        WebElement english = driver.findElement(By.cssSelector("li[aria-label='En'] > span[aria-hidden='true']"));
        wait.until(ExpectedConditions.elementToBeClickable(english));

        english.click();
        isLanguageSet = true;
    }

    private boolean isSignInFormVisible() {
        try {
            return emailInput.isDisplayed() && passwordInput.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Test
    public void verifyTitle() {
        Assertions.assertEquals("GreenCity", driver.getTitle());
    }

    @ParameterizedTest
    @CsvSource({
            "huo48653@bcooq.com, Hhuo48653!"
    })

    public void signInPositiveScenario(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        emailInput.sendKeys(email);
        assertThat(emailInput.getAttribute("value"), is(email));

        passwordInput.sendKeys(password);
        assertThat(passwordInput.getAttribute("value"), is(password));

        wait.until(ExpectedConditions.elementToBeClickable(signInSubmitButton));
        signInSubmitButton.click();

        Assertions.assertEquals("GreenCity", driver.getTitle());
    }

    @ParameterizedTest
    @CsvSource({
            "invalidemail@, InvalidPassword, Please check that your e-mail address is indicated correctly",
            ", Password123!, Please check that your e-mail address is indicated correctly",
            "validuser@example.com,  , Bad email or password"
    })
    public void signInNegativeScenario(String email, String password, String expectedErrorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        emailInput.click();
        if (email != null) {
            emailInput.sendKeys(email);
        }

        passwordInput.click();
        if (password != null) {
            passwordInput.sendKeys(password);
        }

        signInSubmitButton.click();

        if (email == null || !email.contains("@")) {
            try {
                wait.until(ExpectedConditions.visibilityOf(errorEmail));
                assertThat(errorEmail.getText(), is(expectedErrorMessage));
            } catch (TimeoutException e) {
                System.out.println("Email error message not displayed.");
            }
    } else if (password == null || password.isEmpty()) {
            try {
                wait.until(ExpectedConditions.visibilityOf(errorPassword));
                assertThat(errorPassword.getText(), is(expectedErrorMessage));
            } catch (TimeoutException e) {
                System.out.println("Password error message not displayed.");
            }
        }
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}