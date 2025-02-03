package com.softserve.edu.hw;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class pr_10 {
    private WebDriverWait wait;

    @FindBy(css = "div.main-content.app-container img.ubs-header-sing-in-img")
    private WebElement signInButton;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = ".ubsStyle")
    private WebElement signInSubmitButton;

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
    }

    @ParameterizedTest
    @CsvSource({
            "tester0202@gmail.com, Qwerty123!^",
    })
    @DisplayName("Verify valid login attempts")
    public void signIn(String email, String password) {
        signInButton.click();
        emailInput.sendKeys(email);
        assertThat(emailInput.getAttribute("value"), is(email));
        passwordInput.sendKeys(password);
        assertThat(passwordInput.getAttribute("value"), is(password));
        signInSubmitButton.click();
    }
    }


