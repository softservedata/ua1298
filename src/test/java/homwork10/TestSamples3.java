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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestSamples3 {
    private WebDriverWait wait;

    @FindBy(css = "div.main-content.app-container img.ubs-header-sing-in-img")
    private WebElement signInButton;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = ".ubsStyle")
    private WebElement signInSubmitButton;

    @FindBy(css = ".mat-simple-snackbar > span")
    private WebElement result;

    @FindBy(css = ".alert-general-error")
    private WebElement errorMessage;

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:4205/#/ubs");
        driver.manage().window().setSize(new Dimension(1300, 1120));
    }

    @BeforeEach
    public void initPageElements() {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Verify page title is correct")
    public void verifyTitle() {
        Assertions.assertEquals("GreenCity", driver.getTitle());
    }

    @ParameterizedTest
    @CsvSource({
            "samplestest@greencity.com, weyt3$Guew^",
            "anotheruser@greencity.com, anotherpassword"
    })
    @DisplayName("Verify valid login attempts")
    public void signIn(String email, String password) {

        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.main-content.app-container img.ubs-header-sing-in-img")));


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", signInButton);


        emailInput.clear();
        passwordInput.clear();

        emailInput.sendKeys(email);
        assertThat(emailInput.getAttribute("value"), is(email));
        passwordInput.sendKeys(password);
        assertThat(passwordInput.getAttribute("value"), is(password));


        WebElement signInSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ubsStyle")));
        js.executeScript("arguments[0].click();", signInSubmitButton);
    }

    @ParameterizedTest
    @CsvSource({
            "invalidemail@greencity.com, password, Please check if the email is written correctly",
            "validemail@greencity.com, wrongpassword, Invalid password"
    })
    @DisplayName("Verify invalid login attempts")
    public void signInNotValid(String email, String password, String expectedError) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", signInButton);


        emailInput.clear();
        passwordInput.clear();

        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);


        WebElement signInSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ubsStyle")));
        js.executeScript("arguments[0].click();", signInSubmitButton);

        assertThat(errorMessage.getText(), is(expectedError));
    }

    @ParameterizedTest
    @CsvSource({
            " ,password, Email is required",
            "email@example.com, , Password is required"
    })
    @DisplayName("Verify login with missing fields")
    public void signInMissingFields(String email, String password, String expectedError) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", signInButton);


        emailInput.clear();
        passwordInput.clear();

        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);


        WebElement signInSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ubsStyle")));
        js.executeScript("arguments[0].click();", signInSubmitButton);

        assertThat(errorMessage.getText(), is(expectedError));
    }
}