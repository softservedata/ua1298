package HomeWork13.tests;

import HomeWork13.TestRunner;
import HomeWork13.data.LoginTestData;
import HomeWork13.pages.LoginPage;
import HomeWork13.repositories.LoginTestDataRepository;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

public class TestSamples3 {
    private static WebDriver driver;
    private LoginPage loginPage;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://localhost:4205/#/greenCity");
    }

    @BeforeEach
    public void initPageElements() {
        loginPage = new LoginPage(driver);
        loginPage.switchToEnglish();
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Verify page title")
    public void verifyTitle() {
        Assertions.assertEquals("GreenCity", driver.getTitle());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/valid-data.csv", numLinesToSkip = 1)
    @DisplayName("Verify valid sign in and sign out.")
    public void testValidSignInAndSignOut(String email, String password) {
        loginPage.login(email, password);

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/invalid-data.csv", numLinesToSkip = 1)
    @DisplayName("Verify invalid sign in.")
    public void testInvalidSignIn(String email, String password, String expectedMessage) {
        loginPage.login(email, password);
        
    }
}