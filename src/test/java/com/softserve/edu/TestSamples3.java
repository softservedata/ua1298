package com.softserve.edu;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestSamples3 {
    private static WebDriverWait wait;
    private static WebDriver driver;
    private static JavascriptExecutor js;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.greencity.cx.ua/#/greenCity");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }

    @BeforeEach
    public void initPageElements() {
        PageFactory.initElements(driver, this);
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private WebElement findElementByJs(String script) {
        return (WebElement) js.executeScript(script);
    }

    @FindAll({
            @FindBy(xpath = "//a[contains(@class, 'header_sign-in-link') and @role='link']"),
            @FindBy(xpath = "//img[contains(@class, 'ubs-header-sing-in-img') and @alt='sing in button']")
    })
    private WebElement signInButton;

    @FindBy(xpath = "//h1[contains(text(), 'Welcome back!')]")
    private WebElement welcomeText;

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

    @FindBy(xpath = "//a[@class='close-modal-window']")
    private WebElement closeModalButton;

    @DisplayName("Verify title of the page.")
    @Test
    public void verifyTitle() {
        Assertions.assertEquals("GreenCity — Build Eco-Friendly Habits Today", driver.getTitle());
    }

    @ParameterizedTest
    @CsvSource({"levjuli98@gmail.com, 12345Yulia!"})
    @DisplayName("Verify valid sign in and sign out.")
    public void testValidSignInAndSignOut(String email, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();

        WebElement userEmail = wait.until(ExpectedConditions.visibilityOf(emailInput));
        userEmail.clear();
        userEmail.sendKeys(email);

        WebElement userPassword = wait.until(ExpectedConditions.visibilityOf(passwordInput));
        userPassword.clear();
        userPassword.sendKeys(password);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(signInSubmitButton));
        js.executeScript("arguments[0].click();", submitButton);

        WebElement habitsTab = wait.until(ExpectedConditions.visibilityOf(myHabitsTab));
        Assertions.assertTrue(habitsTab.isDisplayed(), "The 'My habits' tab is not visible after login.");

        WebElement userMenu = wait.until(ExpectedConditions.visibilityOf(userMenuButton));
        userMenu.click();

        WebElement signOut = wait.until(ExpectedConditions.elementToBeClickable(signOutButton));
        signOut.click();

        WebElement signInVisible = wait.until(ExpectedConditions.visibilityOf(signInButton));
        Assertions.assertTrue(signInVisible.isDisplayed(), "Sign out was not successful.");
    }

    @ParameterizedTest
    @CsvSource({
            "testgreencity.com, 347593-!, Please check that your e-mail address is indicated correctly",
            "validemail@ggmail.com, juli12345!, Bad email or password"
    })
    @DisplayName("Verify invalid sign in.")
    public void testInvalidSignIn(String email, String password, String expectedMessage) {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();

        WebElement userEmail = wait.until(ExpectedConditions.visibilityOf(emailInput));
        userEmail.clear();
        userEmail.sendKeys(email);

        WebElement userPassword = wait.until(ExpectedConditions.visibilityOf(passwordInput));
        userPassword.clear();
        userPassword.sendKeys(password);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(signInSubmitButton));
        js.executeScript("arguments[0].click();", submitButton);

        WebElement errorElement;
        if (email.contains("testgreencity.com")) {
            errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='email-err-msg']//div[contains(text(), 'Please check that your e-mail address is indicated correctly')]")));
        } else {
            errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'alert-general-error') and contains(text(), 'Bad email or password')]")));
        }

        Assertions.assertEquals(expectedMessage, errorElement.getText(), "Error message does not match expected.");
        wait.until(ExpectedConditions.elementToBeClickable(closeModalButton)).click();
    }
}
