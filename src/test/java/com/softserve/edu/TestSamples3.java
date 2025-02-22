package com.softserve.edu;

import com.softserve.edu.data.TestData;
import com.softserve.edu.data.TestDataRepository;
import com.softserve.edu.helpers.AuthHelper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

class TestSamples3 extends TestRunner {
    private static final Logger logger = LoggerFactory.getLogger(TestSamples3.class);
    private final AuthHelper authHelper;

    @FindBy(xpath = "//a[contains(@class, 'header_sign-in-link') and @role='link']")
    private WebElement signInButton;

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

    @FindBy(xpath = "//div[@id='email-err-msg']//div[contains(text(), 'Please check that your e-mail address is indicated correctly')]")
    private WebElement errorEmail;

    @FindBy(xpath = "//div[contains(@class, 'alert-general-error') and contains(text(), 'Bad email or password')]")
    private WebElement errorPassword;

    @FindBy(xpath = "//a[@class='close-modal-window']")
    private WebElement closeModalButton;

    public TestSamples3() {
        PageFactory.initElements(driver, this);
        authHelper = new AuthHelper(driver, wait);
    }

    static Stream<TestData> validCredentialsProvider() {
        return TestDataRepository.getValidCredentials().stream();
    }

    static Stream<TestData> invalidCredentialsProvider() {
        return TestDataRepository.getInvalidCredentials().stream();
    }

    @DisplayName("Verify title of the page.")
    @Test
    public void verifyTitle() {
        logger.info("Starting test: verifyTitle");
        try {
            Assertions.assertEquals("GreenCity — Build Eco-Friendly Habits Today", driver.getTitle());
        } catch (AssertionError e) {
            logger.error("Test failed: verifyTitle - Expected title mismatch.", e);
            throw e;
        }
    }

    @ParameterizedTest
    @MethodSource("validCredentialsProvider")
    @DisplayName("Verify valid sign in and sign out.")
    public void testValidSignInAndSignOut(TestData testData) {
        logger.info("Starting test: testValidSignInAndSignOut for user: {}", testData.getEmail());
        try {
            authHelper.login(signInButton, emailInput, passwordInput, signInSubmitButton,
                    testData.getEmail(), testData.getPassword());

            WebElement habitsTab = wait.until(ExpectedConditions.visibilityOf(myHabitsTab));
            Assertions.assertTrue(habitsTab.isDisplayed(), "The 'My habits' tab is not visible after login.");

            WebElement userMenu = wait.until(ExpectedConditions.visibilityOf(userMenuButton));
            userMenu.click();

            WebElement signOut = wait.until(ExpectedConditions.elementToBeClickable(signOutButton));
            signOut.click();

            WebElement signInVisible = wait.until(ExpectedConditions.visibilityOf(signInButton));
            Assertions.assertTrue(signInVisible.isDisplayed(), "Sign out was not successful.");
        } catch (Exception e) {
            logger.error("Test failed: testValidSignInAndSignOut for user: {}", testData.getEmail(), e);
            throw e;
        }
    }

    @ParameterizedTest
    @MethodSource("invalidCredentialsProvider")
    @DisplayName("Verify invalid sign in.")
    public void testInvalidSignIn(TestData testData) {
        logger.info("Starting test: testInvalidSignIn for user: {}", testData.getEmail());
        try {
            authHelper.login(signInButton, emailInput, passwordInput, signInSubmitButton,
                    testData.getEmail(), testData.getPassword());

            if (testData.getEmail().contains("testgreencity.com")) {
                wait.until(ExpectedConditions.visibilityOf(errorEmail));
                Assertions.assertEquals(testData.getExpectedMessage(), errorEmail.getText(),
                        "Error message does not match expected.");
            } else {
                wait.until(ExpectedConditions.visibilityOf(errorPassword));
                Assertions.assertEquals(testData.getExpectedMessage(), errorPassword.getText(),
                        "Error message does not match expected.");
            }

            wait.until(ExpectedConditions.elementToBeClickable(closeModalButton)).click();
        } catch (Exception e) {
            logger.error("Test failed: testInvalidSignIn for user: {}", testData.getEmail(), e);
            throw e;
        }
    }
}
