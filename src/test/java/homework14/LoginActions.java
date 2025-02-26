package homework14;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class LoginActions {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(LoginActions.class.getName());

    public LoginActions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        logger.info("LoginActions initialized");
    }

    public void navigateToSignIn(WebElement signInButton) {
        logger.info("Navigating to sign-in page...");
        wait.until(d -> signInButton.isEnabled());
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signInButton);
        logger.info("Clicked sign-in button");
    }

    public void performLogin(WebElement emailInput, WebElement passwordInput, WebElement signInSubmitButton,
                             String email, String password) {
        logger.info("Performing login with email: " + email);
        wait.until(d -> emailInput.isEnabled());
        emailInput.clear();
        emailInput.sendKeys(email);

        wait.until(d -> passwordInput.isEnabled());
        passwordInput.clear();
        passwordInput.sendKeys(password);

        wait.until(d -> signInSubmitButton.isEnabled());
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signInSubmitButton);
        logger.info("Login button clicked");
    }
}