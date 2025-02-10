package homework11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class positiveTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/positiveTest", numLinesToSkip = 1)  // Позитивний тестовий файл
    @DisplayName("Verify successful login")
    void testUserLoginning(String email, String pass) throws InterruptedException {
        driver.get("http://localhost:4205/#/ubs");

        WebElement signUpButton = wait.until((ExpectedConditions.elementToBeClickable(By.cssSelector(".ubs-header-sign-in"))));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signUpButton);

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys(email);

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(pass);

        WebElement submit = driver.findElement(By.cssSelector(".ubsStyle"));
        submit.click();


        WebElement headerUser = wait.until((ExpectedConditions.elementToBeClickable(By.cssSelector("a.ubs-header_user-name"))));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", headerUser);

        WebElement signOut = driver.findElement(By.cssSelector(".drop-down-item"));
        signOut.click();


        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ubs-header-sign-in")));
        assertTrue(signInButton.isDisplayed(), "Sign-in button should be displayed after sign out.");
    }
}