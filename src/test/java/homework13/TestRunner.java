package homework13;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class TestRunner {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(TestRunner.class.getName());

    @BeforeAll
    public static void setUp() {
        logger.info("Setting up WebDriver...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.manage().deleteAllCookies();
        logger.info("Opening initial page...");
        driver.get("http://localhost:4205/#/ubs");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            logger.info("Closing WebDriver...");
            driver.quit();
            driver = null;
        }
        wait = null;
    }
}