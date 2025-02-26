package homework14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class LoginTests extends TestRunner {
    private static final Logger logger = Logger.getLogger(LoginTests.class.getName());
    private LoginActions loginActions;

    @FindBy(xpath = "//*[contains(text(), 'Увійти')]")
    private WebElement signInButton;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement signInSubmitButton;

    @FindBy(css = ".alert-general-error")
    private WebElement errorMessage;

    @FindBy(css = ".body-user-name .drop-down-item") // Основний селектор для профілю
    private WebElement userProfileLink;

    @FindBy(css = ".drop-down-item") // Альтернатива
    private WebElement signOutButton;

    @BeforeEach
    public void initPageFactory() {
        logger.info("Initializing PageFactory and LoginActions...");
        PageFactory.initElements(driver, this);
        loginActions = new LoginActions(driver, wait);
        logger.info("LoginActions initialized successfully");
    }

    @Test
    public void testSuccessfulLogin() {
        logger.info("Starting successful login test...");
        String email = "gim33370@bcooq.com";
        String password = "Anna$5685-ANNA";
        String displayName = "Hey1";
        try {
            loginActions.navigateToSignIn(signInButton);
            loginActions.performLogin(emailInput, passwordInput, signInSubmitButton, email, password);

            // Перевірка, чи логін неуспішний
            try {
                wait.until(d -> errorMessage.isDisplayed());
                String actualError = errorMessage.getText().trim();
                logger.severe("Login failed with error: " + actualError);
                fail("Login failed unexpectedly with error: " + actualError);
            } catch (Exception e) {
                logger.info("No login error, proceeding to profile check...");
            }

            wait.until(d -> userProfileLink.isDisplayed());
            String actualDisplayName = userProfileLink.getText().trim();
            assertEquals(displayName, actualDisplayName, "Display name in profile does not match expected");

            userProfileLink.click();
            wait.until(d -> signOutButton.isDisplayed());
            signOutButton.click();

            wait.until(d -> signInButton.isDisplayed());
            logger.info("Login test passed successfully!");
        } catch (Exception e) {
            logger.severe("Test failed due to an exception: " + e.getMessage());
            fail("Login test failed due to an exception: " + e.getMessage());
        }
    }

    @Test
    public void testUnsuccessfulLogin() {
        logger.info("Starting unsuccessful login test...");
        String email = "samplestest@greencity.com";
        String password = "weyt3$Guew^";
        try {
            loginActions.navigateToSignIn(signInButton);
            loginActions.performLogin(emailInput, passwordInput, signInSubmitButton, email, password);

            wait.until(d -> errorMessage.isDisplayed());
            String actualError = errorMessage.getText().trim();
            assertTrue(actualError.length() > 0, "Expected an error message, but none found");
            logger.info("Unsuccessful login test passed with error: " + actualError);
        } catch (Exception e) {
            logger.severe("Test failed due to an exception: " + e.getMessage());
            fail("Unsuccessful login test failed due to an exception: " + e.getMessage());
        }
    }
}