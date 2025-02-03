package com.softserve.hw12;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class TestSamples3 {

    private static WebDriverWait wait;
    private static WebDriver driver;
    // UPDATED
    private static JavascriptExecutor jsExecutor;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:4205/#/greenCity");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // UPDATED
        jsExecutor = (JavascriptExecutor) driver;
    }

    @BeforeEach
    public void initPageElements() {
        PageFactory.initElements(driver, this);
        switchToEnglish();
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // UPDATED
    private void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // UPDATED
    private void clickWithJS(WebElement element) {
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    // UPDATED
    private void sendKeysWithWait(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    @FindBy(xpath = "//img[contains(@class, 'ubs-header-sing-in-img') and @alt='sing in button']")
    private WebElement signInButton;

    @FindBy(xpath = "//h1[contains(text(), 'Welcome back!')]")
    private WebElement welcomeText;

    @FindBy(xpath = "//h2[contains(text(), 'Please enter your details to sign in.')]")
    private WebElement signInDetailsText;

    @FindBy(xpath = "//label[@for='email' and contains(text(), 'Email')]")
    private WebElement emailLabel;

    @FindBy(xpath = "//input[@id='email' and @type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='password' and @type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(@class, 'greenStyle') and @type='submit']")
    private WebElement signInSubmitButton;

    @FindBy(css = ".mat-simple-snackbar > span")
    private WebElement result;

    @FindBy(xpath = "//div[contains(@class, 'alert-general-error')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[contains(@class, 'alert-general-error') and contains(text(), 'Bad email or password')]")
    private WebElement errorPassword;

    @FindBy(xpath = "//div[@id='email-err-msg']//div[contains(text(), 'Please check that your e-mail address is indicated correctly')]")
    private WebElement errorEmail;

    @FindBy(xpath = "//ul[contains(@class, 'header_lang')]//li[@role='option' and contains(@aria-label, 'english')]")
    private WebElement languageSwitcher;

    @FindBy(xpath = "//span[text()='En']")
    private WebElement englishOption;

    @FindBy(xpath = "//div[contains(@class, 'mat-tab-label-content') and contains(text(), 'My habits')]")
    private WebElement myHabitsTab;

    @FindBy(xpath = "//a[contains(@class, 'header_user-name')]")
    private WebElement userMenuButton;

    @FindBy(xpath = "//li[@role='button' and contains(@aria-label, 'sign-out')]")
    private WebElement signOutButton;

    @FindBy(xpath = "//a[@class='close-modal-window']")
    private WebElement closeModalButton;

    // UPDATED
    public void switchToEnglish() {
        waitForVisibility(languageSwitcher);
        languageSwitcher.click();
        waitForVisibility(englishOption);
        englishOption.click();
    }

    @DisplayName("Verify title of the page.")
    @Test
    public void verifyTitle() {
        Assertions.assertEquals("GreenCity", driver.getTitle());
    }

    // UPDATED
    @ParameterizedTest
    @CsvSource({
            "bonkatari@gmail.com, Gfhjkm2002!",
            "bonkatari@gmail.com, Gfhjkm2002!"
    })
    @DisplayName("Verify valid sign in and sign out.")
    public void testValidSignInAndSignOut(String email, String password) {
        waitForVisibility(signInButton);
        signInButton.click();

        sendKeysWithWait(emailInput, email);
        sendKeysWithWait(passwordInput, password);

        waitForVisibility(signInSubmitButton);
        clickWithJS(signInSubmitButton);

        waitForVisibility(myHabitsTab);
        Assertions.assertTrue(myHabitsTab.isDisplayed(), "The 'My habits' tab is not visible after login.");

        waitForVisibility(userMenuButton);
        userMenuButton.click();

        waitForVisibility(signOutButton);
        signOutButton.click();

        waitForVisibility(signInButton);
        Assertions.assertTrue(signInButton.isDisplayed(), "Sign out was not successful.");
    }

    // UPDATED
    @ParameterizedTest
    @CsvSource({
            "samplestesgreencity.com, uT346^^^erw, Please check that your e-mail address is indicated correctly",
            "validemail@example.com, gfhjkm12345678, Bad email or password"
    })
    @DisplayName("Verify invalid sign in.")
    public void testInvalidSignIn(String email, String password, String expectedMessage) {
        waitForVisibility(signInButton);
        signInButton.click();

        sendKeysWithWait(emailInput, email);
        sendKeysWithWait(passwordInput, password);

        waitForVisibility(signInSubmitButton);
        clickWithJS(signInSubmitButton);

        WebElement errorElement;
        if (email.contains("@")) {
            waitForVisibility(errorPassword);
            errorElement = errorPassword;
        } else {
            waitForVisibility(errorEmail);
            errorElement = errorEmail;
        }

        Assertions.assertEquals(expectedMessage, errorElement.getText(), "Error message does not match expected.");

        try {
            waitForVisibility(closeModalButton);
            closeModalButton.click();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }
}