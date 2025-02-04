package com.softserve.edu.hw;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class pr_12_NegativeTest {
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
    @CsvSource({
            "testser0303@gmail.com, Qwerty123!",
            "tester0303@gmail.com, Qwerty123!",
            "11@gmail.com, 123123123!",
    })
    @DisplayName("Negative Login Test - Invalid Credentials")
    void testInvalidLogin(String email, String pass) {
        // Відкриваємо сторінку
        driver.get("http://localhost:4205/#/ubs");

        WebElement signUpButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ubs-header-sign-in")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signUpButton);

        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        emailField.sendKeys(email);

        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
        passwordField.sendKeys(pass);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ubsStyle")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);

        WebElement errorMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.alert-general-error.ng-star-inserted")));
        assertTrue(errorMessage.isDisplayed(), "Введено невірний email або пароль\n");
    }
}