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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Registration {

    private WebDriver driver;
    private WebDriverWait wait;
    private final static String URL = "https://www.greencity.cx.ua/#/ubs";


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
        WebElement signUpButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ubs-header_sign-up-btn")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signUpButton);
    }

    @ParameterizedTest
    @CsvSource({
            "testUser011@gmail.com, Testuser01, Password1234!",
            "testUser021@gmail.com, Testuser02, Password1234!",
            "testUser031@gmail.com, Testuser03, Password1234!",
            "testUser041@gmail.com, Testuser04, Password1234!",
            "testUser051@gmail.com, Testuser05, Password1234!"
    })
    @DisplayName("Verify registration new user")
    void testUserPositiveRegistrationGreenCity(String email, String username, String pass) {
        openRegistrationForm();
        WebElement userEmail = driver.findElement(By.id("email"));//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        userEmail.sendKeys(email);

        WebElement userName = driver.findElement(By.id("firstName"));//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
        userName.sendKeys(username);

        WebElement password = driver.findElement(By.id("password"));//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        password.sendKeys(pass);

        WebElement repeatPassword = driver.findElement(By.id("repeatPassword"));//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("repeatPassword")));
        repeatPassword.sendKeys(pass);

        WebElement registerButton = wait.until((ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", registerButton);

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Congratulations! You have successfully registered on the site. Please confirm your email address in the email box.')]")));
        assertTrue(successMessage.isDisplayed(), "Congratulation");
    }
}