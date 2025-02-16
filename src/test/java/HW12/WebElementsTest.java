package HW12;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WebElementsTest {
    private static final String BASE_URL = "http://localhost:4205/#/ubs";
    private static final int TIMEOUT = 10;
    private static WebDriver driver;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void openAndInitializeRegistrationForm() {
        driver.get(BASE_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));

        List<WebElement> signUpButtons = driver.findElements(By.cssSelector("div.ubs-header_sign-up-btn"));

        if (signUpButtons.isEmpty()) {
            throw new NoSuchElementException("No Sign Up buttons found on the page.");
        }

        boolean signUpButtonClicked = false;
        for (WebElement signUpButton : signUpButtons) {

            if (signUpButton.isDisplayed() && signUpButton.isEnabled()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signUpButton);

                wait.until(ExpectedConditions.elementToBeClickable(signUpButton));

                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signUpButton);
                    signUpButtonClicked = true;
                    break;
                } catch (Exception e) {
                    System.out.println("Error clicking sign-up button: " + e.getMessage());
                }
            }
        }

        if (!signUpButtonClicked) {
            throw new ElementNotInteractableException("No clickable Sign Up button found.");
        }

        WebElement signUpForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.wrapper")));
        WebElement titleText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.title-text")));
        assertThat(titleText.getText(), is("Вітаємо!"));
    }

    @ParameterizedTest
    @CsvSource({
            "validuser@example.com, ValidUser, ValidPass123!, ValidPass123!, GreenCity"
    })
    @Order(1)
    public void successfulRegistration(String email, String username, String password, String confirmPassword, String expectedTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailInput.sendKeys(email);

        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
        usernameInput.sendKeys(username);

        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        passwordInput.sendKeys(password);

        WebElement confirmPasswordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("repeatPassword")));
        confirmPasswordInput.sendKeys(confirmPassword);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.ubsStyle")));
        submitButton.click();

        wait.until(ExpectedConditions.titleIs(expectedTitle));
        assertThat(driver.getTitle(), is(expectedTitle));
    }

    @ParameterizedTest
    @CsvSource({
            ", ValidUser, ValidPass123!, ValidPass123!",
            "invalidemail@, ValidUser, ValidPass123!, ValidPass123!",
            "existinguser@example.com, ValidUser, ValidPass123!, ValidPass123!",
            "validuser@example.com, , ValidPass123!, ValidPass123!",
            "validuser@example.com, Invalid@User, ValidPass123!, ValidPass123!",
            "validuser@example.com, ValidUser, , ValidPass123!",
            "validuser@example.com, ValidUser, ValidPass123!, "
    })
    @Order(2)
    public void registrationValidationErrors(String email, String username, String password, String confirmPassword) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        List<String> errorMessages = new ArrayList<>();

        try {
            if (email != null) driver.findElement(By.id("email")).sendKeys(email);
            if (username != null) driver.findElement(By.id("firstName")).sendKeys(username);
            if (password != null) driver.findElement(By.id("password")).sendKeys(password);
            if (confirmPassword != null) driver.findElement(By.id("repeatPassword")).sendKeys(confirmPassword);

            boolean hasErrors = false;
            hasErrors |= checkAndCollectError(wait, "email-err-msg", "Email error: ", errorMessages);
            hasErrors |= checkAndCollectError(wait, "firstname-err-msg", "Username error: ", errorMessages);
            hasErrors |= checkAndCollectError(wait, "password-err-msg", "Password error: ", errorMessages);
            hasErrors |= checkAndCollectError(wait, "confirm-err-msg", "Confirm password error: ", errorMessages);

            if (!hasErrors) {
                WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("ubsStyle")));
                try {
                    submitButton.click();
                } catch (TimeoutException e) {
                    errorMessages.add("Sign-up button is not clickable. Please check the form.");
                }
            }

            boolean isLoggedIn = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("login-form")));
            if (!isLoggedIn) {
                errorMessages.add("Login did not happen. Invalid input data.");
            }

            if (!errorMessages.isEmpty()) {
                System.out.println("\n--- Test Result ---");
                for (String error : errorMessages) {
                    System.out.println(error);
                }
            } else {
                System.out.println("Test passed successfully!");
            }

        } catch (Exception e) {
            System.out.println("Unexpected error during test execution: " + e.getMessage());
        }
    }

    private boolean checkAndCollectError(WebDriverWait wait, String errorElementId, String errorMessagePrefix, List<String> errorMessages) {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(errorElementId)));
            String errorText = errorElement.getText();
            errorMessages.add(errorMessagePrefix + errorText);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

