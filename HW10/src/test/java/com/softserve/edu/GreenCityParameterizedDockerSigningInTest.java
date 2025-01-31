package com.softserve.edu;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class GreenCityParameterizedDockerSigningInTest {
    @FindBy(css = ".header_sign-in-link.tertiary-global-button")
    private WebElement signInButton;
    @FindBy(css = "div.right-side h1")
    private WebElement welcomeText;
    @FindBy(css = "div.right-side h2")
    private WebElement signInDetailsText;
    @FindBy(css = "label[for='email']")
    private WebElement emailLabel;
    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(css = "label[for='password']")
    private WebElement passwordLabel;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(css = ".greenStyle")
    private WebElement signInSubmitButton;


    @FindBy(css="a.header_user-name")
    private WebElement result;
    @FindBy(css = "div.alert-general-error.ng-star-inserted")
    private WebElement errorMessage;
    @FindBy(css = "div.validation-password-error.ng-star-inserted div")
    private WebElement errorPassword;
    @FindBy(css = "div#email-err-msg div")
    private WebElement errorEmail;
    @FindBy(css = "li.lang-option span")
    private WebElement curLang;
    @FindBy(css = "li[aria-label='En'] span")
    private WebElement lang;
    @FindBy(xpath = "//li[@class='drop-down-item']/a[contains(text(), 'Sign out')]")
    private WebElement signOutButton;

    @FindBy(css = ".cross-btn")
    private WebElement crossButton;

    private static final String BASE_URL = "http://localhost:4205/#/greenCity";
    private static WebDriver driver;
    private static final long oneSec = 1;
    private static final long threeSec = 3;
    private static final long fiveSec = 5;
    private static final long tenSec = 10;


    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(fiveSec));
        driver.manage().window().maximize();
    }
    @BeforeEach
    public void initPageElements() {
        PageFactory.initElements(driver, this);
    }

    @Test
    public void verifyTitleTest() {
        assertEquals("GreenCity", driver.getTitle());
    }

    private void closeIframeIfExists(){
        List<WebElement> iframe = driver.findElements(By.cssSelector("iframe"));
        if(!iframe.isEmpty()){
            driver.switchTo().frame(iframe.getFirst());
            List<WebElement> popupButton = driver.findElements(By.id("close"));
            if(!popupButton.isEmpty()){
                popupButton.getFirst().click();
            }
            driver.switchTo().defaultContent();
        }
    }

    private void changeLangToEn(){
        if(!Objects.equals(curLang.getText().toUpperCase(), "EN")){
            curLang.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(oneSec));
            lang.click();
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/validLoggingData.csv", delimiter = ';', numLinesToSkip = 1)
    public void checkSignInWithValidDataTest(String login, String pass) {
        //act
        changeLangToEn();
        signInButton.click();
        closeIframeIfExists();

        emailInput.clear();
        emailInput.sendKeys(login);
        assertEquals(emailInput.getDomProperty("value"), login);
        passwordInput.clear();
        assertFalse(signInSubmitButton.isEnabled());
        passwordInput.sendKeys(pass);
        assertEquals(passwordInput.getDomProperty("value"), pass);
        assertTrue(signInSubmitButton.isEnabled());
        signInSubmitButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(threeSec));
        assertTrue(result.isDisplayed());

        result.click();
        signOutButton.click();
    }

    @Test
    public void checkSignInCheckLabelTextsTest(){
        //arrange
        //act
        changeLangToEn();
        signInButton.click();
        closeIframeIfExists();

        assertEquals("Welcome back!", welcomeText.getText());
        assertEquals("Please enter your details to sign in.", signInDetailsText.getText());
        assertEquals("Email", emailLabel.getText());
        assertEquals("Password", passwordLabel.getText());
        assertEquals("Sign in", signInSubmitButton.getText());
        assertFalse(signInSubmitButton.isEnabled());
    }

    @Test   //If first time don`t work - rerun
    public void checkSignInCheckErrorMessagesExistsTest() throws InterruptedException {
        //arrange
        String invalidInputEmail = "justTest.com";
        String tooShortPassword = "pass";
        String[] errorsLocators = new String[]{"div.alert-general-error.ng-star-inserted", "div#email-err-msg div", "div.validation-password-error.ng-star-inserted div"};
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //act
        changeLangToEn();
        signInButton.click();
        closeIframeIfExists();

        emailInput.click();
        passwordInput.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(threeSec));
        new WebDriverWait(driver, Duration.ofSeconds(fiveSec)).until(ExpectedConditions.visibilityOf(errorEmail));
        assertTrue(errorEmail.isDisplayed());
        assertTrue((boolean) js.executeScript("return document.querySelectorAll(arguments[0]).length === 0"), errorsLocators[0]);
        assertTrue((boolean) js.executeScript("return document.querySelectorAll(arguments[0]).length === 0"), errorsLocators[2]);
        assertEquals("Email is required", errorEmail.getText());
        emailInput.click();
        assertTrue(errorMessage.isDisplayed());
        assertTrue((boolean) js.executeScript("return document.querySelectorAll(arguments[0]).length === 0"), errorsLocators[1]);
        assertTrue((boolean) js.executeScript("return document.querySelectorAll(arguments[0]).length === 0"), errorsLocators[2]);
        assertEquals("Please fill all red fields", errorMessage.getText());
        emailInput.sendKeys(invalidInputEmail);
        assertTrue(errorPassword.isDisplayed());
        assertTrue((boolean) js.executeScript("return document.querySelectorAll(arguments[0]).length === 0"), errorsLocators[1]);
        assertTrue((boolean) js.executeScript("return document.querySelectorAll(arguments[0]).length === 0"), errorsLocators[0]);
        assertEquals("Password is required", errorPassword.getText());
        emailInput.clear();
        emailInput.sendKeys("a");
        emailInput.sendKeys(Keys.BACK_SPACE);
        passwordInput.click();
        assertTrue(errorMessage.isDisplayed());
        assertTrue((boolean) js.executeScript("return document.querySelectorAll(arguments[0]).length === 0"), errorsLocators[1]);
        assertTrue((boolean) js.executeScript("return document.querySelectorAll(arguments[0]).length === 0"), errorsLocators[2]);
        assertEquals("Please fill all red fields", errorMessage.getText());

        crossButton.click();
        signInButton.click();

        emailInput.sendKeys(invalidInputEmail);
        passwordInput.click();
        assertTrue(errorEmail.isDisplayed());
        assertTrue((boolean) js.executeScript("return document.querySelectorAll(arguments[0]).length === 0"), errorsLocators[0]);
        assertTrue((boolean) js.executeScript("return document.querySelectorAll(arguments[0]).length === 0"), errorsLocators[2]);
        assertEquals("Please check that your e-mail address is indicated correctly", errorEmail.getText());

        passwordInput.sendKeys(tooShortPassword);
        emailInput.click();
        assertTrue(errorEmail.isDisplayed());
        assertTrue(errorPassword.isDisplayed());
        assertTrue((boolean) js.executeScript("return document.querySelectorAll(arguments[0]).length === 0"), errorsLocators[0]);
        assertEquals("Password must be at least 8 characters long without spaces", errorPassword.getText());
    }

    static Stream<Object[]> invalidDataSource(){
        String validEmail = "somovoy449@mywebw.com";
        String validPassword = "Qwerty1!";
        String invalidEmail = "test.login@sth.com";
        String invalidPassword = "invalid pass";
        return Stream.of(
                new Object[]{validEmail, invalidPassword},
                new Object[]{invalidEmail, validPassword},
                new Object[]{invalidEmail, invalidPassword}
        );
    }

    @ParameterizedTest
    @CsvSource({
            "somovoy449@mywebw.com, invalid pass",
            "test.login@sth.com, invalid pass",
            "test.login@sth.com, Qwerty1!"
    })
    public void checkSignInWithInvalidCredentialsTest(String email, String password) {
        // Arrange
        changeLangToEn();
        signInButton.click();
        closeIframeIfExists();

        emailInput.clear();
        emailInput.sendKeys(email);
        assertEquals(emailInput.getDomProperty("value"), email);

        passwordInput.clear();
        assertFalse(signInSubmitButton.isEnabled());

        passwordInput.sendKeys(password);
        assertEquals(passwordInput.getDomProperty("value"), password);
        assertTrue(signInSubmitButton.isEnabled());

        signInSubmitButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(tenSec))
                .until(ExpectedConditions.visibilityOf(errorMessage));

        assertTrue(errorMessage.isDisplayed());
        assertEquals("Bad email or password", errorMessage.getText());

        assertTrue(emailInput.isDisplayed());
        assertTrue(passwordInput.isDisplayed());
        assertTrue(signInSubmitButton.isDisplayed());
        crossButton.click();
    }

    @Test
    public void checkButtonClickableTest(){
        String login = System.getenv("USER_LOGIN");
        String pass = System.getenv("USER_PASSWORD");

        changeLangToEn();
        signInButton.click();
        closeIframeIfExists();

        assertFalse(signInSubmitButton.isEnabled());
        emailInput.clear();
        emailInput.sendKeys(login);
        assertFalse(signInSubmitButton.isEnabled());
        passwordInput.clear();
        passwordInput.click();
        emailInput.click();
        assertFalse(signInSubmitButton.isEnabled());
        emailInput.clear();
        passwordInput.click();
        passwordInput.sendKeys(pass);
        assertFalse(signInSubmitButton.isEnabled());
        emailInput.click();
        passwordInput.click();
        assertFalse(signInSubmitButton.isEnabled());
        emailInput.sendKeys(login);
        assertTrue(signInSubmitButton.isEnabled());
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

}
