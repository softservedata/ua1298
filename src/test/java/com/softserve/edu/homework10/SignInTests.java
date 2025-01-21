package com.softserve.edu.homework10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SignInTests {

    @FindBy(css = "span[ng-reflect-ng-style]")
    private WebElement switchLanguage;
    @FindBy(css = "span[ng-reflect-ng-class]")
    private WebElement languageEn;
    @FindBy(css = "a.header_sign-in-link")
    private WebElement signInButton;
    @FindBy(css = ".ng-star-inserted > h1")
    private WebElement welcomeText;
    @FindBy(css = ".ng-star-inserted > h2")
    private WebElement signInDetailsText;
    @FindBy(css = "label[for=email]")
    private WebElement emailLabel;
    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(css = "button.greenStyle")
    private WebElement submitButton;
    @FindBy(css=".mat-simple-snackbar > span")
    private WebElement result;
    @FindBy(css = ".alert-general-error")
    private WebElement errorMessage;
    @FindBy(css = ".validation-password-error")
    private WebElement errorPassword;
    @FindBy(xpath = "//*[@id=\"email-err-msg\"]/app-error/div")
    private WebElement errorEmail;
    @FindBy(css = "span.show-hide-btn")
    private WebElement showPassword;

    private static WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public  void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:4205/#/greenCity");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeEach
    public void initPageElements() {
        PageFactory.initElements(driver, this);
        switchLanguage.click();
        languageEn.click();

    }
    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testVerifyTitle() {
        Assertions.assertEquals("GreenCity", driver.getTitle());
        // assertThat(driver.getTitle(), is("GreenCity"));
    }

    @ParameterizedTest
    @CsvSource({
            "samplestest@greencity.com, weyt3$Guew^",
            "anotheruser@greencity.com, anotherpassword",
            "user13@gmail.com , passWord1!"
    })
    public void testSignIn(String email, String password){

        signInButton.click();
        assertThat(welcomeText.getText(), is("Welcome back!"));
        assertThat(signInDetailsText.getText(), is("Please enter your details to sign in."));
        assertThat(emailLabel.getText(), is("Email"));
        emailInput.sendKeys(email);
        assertThat(emailInput.getAttribute("value"), is(email));
        passwordInput.sendKeys(password);
        assertThat(passwordInput.getAttribute("value"), is(password));
        submitButton.click();

    }
    @ParameterizedTest
    @CsvSource({
            " samplestesgreencity.com , uT346^^^erw ",
            " емейл@gmail.сom ,uT346^^^erw ",
            " daffa@gmail,сom , uT346^^^erw"

    })
    public void testSignInNotValidEmail(String email,String password) {
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        assertThat(errorEmail.getText(), is("Please check that your e-mail address is indicated correctly"));


    }
    @ParameterizedTest
    @CsvSource({
            " samplestesgreencity@gmail.com , 1234567 , Password must be at least 8 characters long without spaces ",
            " samplestesgreencity@gmail.com , aaaaaaaaaaaaaaaaaaaaa , Password must be less than 20 characters long without spaces"
    })
    public void testSignInNotValidPassword(String email,String password,String expectedError){
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        showPassword.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), '" + expectedError + "')]")));
        assertTrue(errorPassword.isDisplayed(), "Expected email error message is not displayed.");

    }
    @Test
    public void testWithNUllValue(){
        signInButton.click();
        emailInput.click();
        passwordInput.click();
        showPassword.click();
        assertThat(errorMessage.getText(),is("Please fill all red fields"));
        assertFalse(submitButton.isEnabled(),"The 'Sign Up' button should be disabled.");
    }

}
