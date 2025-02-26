package homework13;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginActions {
    private final WebDriverWait wait;

    public LoginActions(WebDriverWait wait) {
        this.wait = wait;
    }

    public void navigateToSignIn(WebElement signInButton) {
        waitForElementToBeClickable(signInButton);
        signInButton.click();
    }

    public void performLogin(WebElement emailInput, WebElement passwordInput, WebElement signInSubmitButton,
                             String email, String password) {
        emailInput.clear();
        passwordInput.clear();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
    }

    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}