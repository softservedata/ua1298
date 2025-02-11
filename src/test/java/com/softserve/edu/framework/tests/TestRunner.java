package com.softserve.edu.framework.tests;

import com.softserve.edu.framework.page.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

@ExtendWith(com.softserve.edu.framework.tests.ResultExtension.class)
public class TestRunner {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected static boolean isTestSuccessful = false;
    protected static WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:4205/#/greenCity");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info(testInfo.getTestMethod() + " Start test");

        PageFactory.initElements(driver, this);
        switchLanguageToEnglish();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterEach
    public void tearThis(TestInfo testInfo){
        if(!isTestSuccessful){
            logger.error("Test_Name = " + testInfo.getTestMethod() + " failed");

        }else{
            logger.info("Test_Name = " + testInfo.getTestMethod() + " done");
        }
    }


    public void switchLanguageToEnglish(){
        HomePage homePage = new HomePage(driver,wait);
        homePage.switchLanguage();
    }
}
