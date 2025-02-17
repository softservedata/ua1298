package HomeWork12;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestSamples3 {

    private static WebDriverWait wait;
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:4205/#/greenCity");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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

    @AfterEach
    void resetState() {
        try {
            if (isElementPresent(closeModalButton)) {
                closeModalButton.click();
                System.out.println("Modal window closed."); // Update
            } else {
                System.out.println("Modal window not found."); // Update
            }
        } catch (Exception e) {
            System.out.println("Issue during modal close: " + e.getMessage()); // Update
        }
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

    @FindBy(css=".mat-simple-snackbar > span")
    private WebElement result;

    @FindBy(xpath = "//div[contains(@class, 'alert-general-error')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[contains(@class, 'alert-general-error') and contains(text(), 'Bad email or password')]")
    private WebElement errorPassword;

    @FindBy(xpath = "//div[text()='Please check that your e-mail address is indicated correctly']")
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

    private boolean isElementPresent(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (Exception e) {
            System.out.println("Element not found: " + e.getMessage()); // Update
            return false;
        }
    }

    public void switchToEnglish() {
        languageSwitcher.click();
        englishOption.click();
    }

    private void enterCredentials(String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(emailInput)).clear();
        emailInput.sendKeys(email);

        wait.until(ExpectedConditions.visibilityOf(passwordInput)).clear();
        passwordInput.sendKeys(password);

        //Update

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(signInSubmitButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
    }

    private void signOut() {
        wait.until(ExpectedConditions.visibilityOf(userMenuButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(signOutButton)).click();
        Assertions.assertTrue(wait.until(ExpectedConditions.visibilityOf(signInButton)).isDisplayed(), "Sign out was not successful.");
    }

    private void closeErrorModal() {
        wait.until(ExpectedConditions.elementToBeClickable(closeModalButton)).click();
    }

    @Test
    @DisplayName("Verify title of the page.")
    public void verifyTitle() {
        Assertions.assertEquals("GreenCity", driver.getTitle());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/valid-data.csv", numLinesToSkip = 1)
    @DisplayName("Verify valid sign in and sign out.")
    public void testValidSignInAndSignOut(String email, String password) {
        signInButton.click();
        enterCredentials(email, password);
        Assertions.assertTrue(wait.until(ExpectedConditions.visibilityOf(myHabitsTab)).isDisplayed(), "Login failed.");
        signOut();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/invalid-data.csv", numLinesToSkip = 1)
    @DisplayName("Verify invalid sign in.")
    public void testInvalidSignIn(String email, String password, String expectedMessage) {
        signInButton.click();
        enterCredentials(email, password);


        WebElement errorMessage = null;
        try {
            errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'alert-general-error') and contains(text(), 'Bad email or password')]")));
        } catch (TimeoutException e) {

            try {
                errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Please check that your e-mail address')]")));
            } catch (TimeoutException ex) {
                Assertions.fail("Error message not found.");
            }
        }

        Assertions.assertNotNull(errorMessage, "Error message element is null.");
        Assertions.assertTrue(errorMessage.isDisplayed(), "Error message is not visible.");
        Assertions.assertEquals(expectedMessage, errorMessage.getText(), "Error message does not match expected.");

        closeErrorModal();
    }

}
