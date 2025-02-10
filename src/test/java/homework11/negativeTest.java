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

public class negativeTest {
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
    @CsvFileSource(resources = "/negativeTest", numLinesToSkip = 1)  // Негативний тестовий файл
    @DisplayName("Verify unsuccessful login")
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


        WebElement errorMessage = wait.until((ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert-general-error"))));
        assertTrue(errorMessage.isDisplayed(), "Error message should be displayed for invalid login.");
    }
}