package HW13.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class TestSetup {

    protected static WebDriver driver;

    @BeforeAll
    public static void setup() {
        driver = TestRunner.initializeDriver();
    }

    @AfterAll
    public static void tearDown() {
        TestRunner.tearDown();
    }
}
