package HomeWork10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NegativeRegistrationTests {

    private WebDriver driver;
    private WebDriverWait wait;


    private final static String URL = "https://www.greencity.cx.ua/#/ubs";
    private final static String SIGN_UP_BUTTON = ".ubs-header_sign-up-btn";
    private final static String EMAIL_FIELD_ID = "email";
    private final static String USERNAME_FIELD_ID = "firstName";
    private final static String PASSWORD_FIELD_ID = "password";
    private final static String REPEAT_PASSWORD_FIELD_ID = "repeatPassword";
    private final static String SUBMIT_BUTTON = "button[type='submit']";

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    void openRegistrationForm() {
        driver.get(URL);
        WebElement signUpButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SIGN_UP_BUTTON)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signUpButton);
    }


    @ParameterizedTest
    @CsvSource({
            "' ', Email is required.",
            "olyagmail.com, Please check that your e-mail address is indicated correctly",
            "olya@gmailcom, Please check that your e-mail address is indicated correctly",
            "@gmail.com, Please check that your e-mail address is indicated correctly",

    })
    @DisplayName("Verify Error Message and Disabled 'Sign Up' Button for Invalid Email Input")
    void testInvalidEmail(String email, String expectedError) {
        openRegistrationForm();

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(EMAIL_FIELD_ID)));
        emailField.sendKeys(email);

        WebElement userName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(USERNAME_FIELD_ID)));
        userName.click();

        WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), '" + expectedError + "')]")));
        assertTrue(emailError.isDisplayed(), "Expected email error message is not displayed.");

        WebElement registerButton = driver.findElement(By.cssSelector(SUBMIT_BUTTON));
        assertFalse(registerButton.isEnabled(), "The 'Sign Up' button should be disabled.");
    }


    @ParameterizedTest
    @CsvSource({
            "' ', User name is required.",
            "'!!!', The user name must be 1-30 characters long",
            "'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', The user name must be 1-30 characters long",
            "'_user', The user name must be 1-30 characters long",

    })
    @DisplayName("Verify Error Message and Disabled 'Sign Up' Button for Invalid Username Input")
    void testInvalidUsername(String username, String expectedError) {
        openRegistrationForm();

        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(USERNAME_FIELD_ID)));
        usernameField.sendKeys(username);

        WebElement pass = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PASSWORD_FIELD_ID)));
        pass.click();

        WebElement usernameError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), '" + expectedError + "')]")));
        assertTrue(usernameError.isDisplayed(), "Expected username error message is not displayed.");

        WebElement registerButton = driver.findElement(By.cssSelector(SUBMIT_BUTTON));
        assertFalse(registerButton.isEnabled(), "The 'Sign Up' button should be disabled.");
    }


    @ParameterizedTest
    @CsvSource({
            "short, Password have from 8 to 20 characters long",
            "NoSpecialChar1, Password have from 8 to 20 characters long"
    })
    @DisplayName("Verify Error Message and Disabled 'Sign Up' Button for Invalid Password Input")
    void testInvalidPassword(String password, String expectedError) {
        openRegistrationForm();

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PASSWORD_FIELD_ID)));
        passwordField.sendKeys(password);

        WebElement passwordError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), '" + expectedError + "')]")));
        assertTrue(passwordError.isDisplayed(), "Expected password error message is not displayed.");

        WebElement registerButton = driver.findElement(By.cssSelector(SUBMIT_BUTTON));
        assertFalse(registerButton.isEnabled(), "The 'Sign Up' button should be disabled.");
    }


    @ParameterizedTest
    @CsvSource({
            "Password123!, Password321!, Passwords do not match",
            "TestPass1!, TestPass2!, Passwords do not match"
    })
    @DisplayName("Verify Error Message and Disabled 'Sign Up' Button for Mismatched Passwords")
    void testPasswordsDoNotMatch(String password, String repeatPassword, String expectedError) {
        openRegistrationForm();

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PASSWORD_FIELD_ID)));
        passwordField.sendKeys(password);

        WebElement repeatPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(REPEAT_PASSWORD_FIELD_ID)));
        repeatPasswordField.sendKeys(repeatPassword);
        WebElement userName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(USERNAME_FIELD_ID)));
        userName.click();
        WebElement passwordMismatchError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), '" + expectedError + "')]")));
        assertTrue(passwordMismatchError.isDisplayed(), "Expected password mismatch error message is not displayed.");

        WebElement registerButton = driver.findElement(By.cssSelector(SUBMIT_BUTTON));
        assertFalse(registerButton.isEnabled(), "The 'Sign Up' button should be disabled.");
    }
}
