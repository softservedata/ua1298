package HomeWork14.tests;

import HomeWork14.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class TestSamples3 {
    private static final Logger logger = LoggerFactory.getLogger(TestSamples3.class);
    private static WebDriver driver;
    private LoginPage loginPage;

    @BeforeAll
    public static void setUp() {
        logger.info("Setting up WebDriver before tests...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://localhost:4205/#/greenCity");
    }

    @BeforeEach
    public void initPageElements() {
        logger.info("Initializing page elements...");
        loginPage = new LoginPage(driver);
        loginPage.switchToEnglish();
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            logger.info("Closing WebDriver...");
            driver.quit();
        }
    }

    @Test
    @DisplayName("Verify page title")
    public void verifyTitle() {
        logger.info("Test: Verifying page title...");
        try {
            assertEquals("GreenCity", driver.getTitle());
            logger.info("Test passed!");
        } catch (AssertionError e) {
            logger.error("Test failed: Page title verification", e);
            throw e;
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/valid-data.csv", numLinesToSkip = 1)
    @DisplayName("Verify valid sign in and sign out.")
    public void testValidSignInAndSignOut(String email, String password) {
        logger.info("Test: Valid login attempt (email: {})", email);
        try {
            loginPage.login(email, password);
            logger.info("Test passed!");
        } catch (Exception e) {
            logger.error("Test failed: Valid login attempt", e);
            fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/invalid-data.csv", numLinesToSkip = 1)
    @DisplayName("Verify invalid sign in.")
    public void testInvalidSignIn(String email, String password, String expectedMessage) {
        logger.info("Test: Invalid login attempt (email: {})", email);
        try {
            loginPage.login(email, password);

            logger.info("Test passed!");
        } catch (Exception e) {
            logger.error("Test failed: Invalid login attempt", e);
            fail(e.getMessage());
        }
    }
}