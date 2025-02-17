package HW13.lib;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RegistrationForm {

    private WebDriver driver;

    public RegistrationForm(WebDriver driver) {
        this.driver = driver;
    }

    public void fillForm(String email, String username, String password, String confirmPassword) {
        if (email != null && !email.isEmpty()) {
            driver.findElement(By.id("email")).sendKeys(email);
        } else {
            System.out.println("Error: Email field is empty.");
        }

        if (username != null && !username.isEmpty()) {
            driver.findElement(By.id("firstName")).sendKeys(username);
        } else {
            System.out.println("Error: Username field is empty.");
        }

        if (password != null && !password.isEmpty()) {
            driver.findElement(By.id("password")).sendKeys(password);
        } else {
            System.out.println("Error: Password field is empty.");
        }

        if (confirmPassword != null && !confirmPassword.isEmpty()) {
            driver.findElement(By.id("repeatPassword")).sendKeys(confirmPassword);
        } else {
            System.out.println("Error: Confirm password field is empty.");
        }
    }

    public void submitForm() {
        WebElement submitButton = driver.findElement(By.cssSelector("button.ubsStyle"));
        submitButton.click();
    }

    public List<String> collectValidationErrors() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<String> errorMessages = new ArrayList<>();
        boolean hasErrors = false;

        hasErrors |= checkAndCollectError(wait, "email-err-msg", "Email error: ", errorMessages);
        hasErrors |= checkAndCollectError(wait, "firstname-err-msg", "Username error: ", errorMessages);
        hasErrors |= checkAndCollectError(wait, "password-err-msg", "Password error: ", errorMessages);
        hasErrors |= checkAndCollectError(wait, "confirm-err-msg", "Confirm password error: ", errorMessages);

        if (!hasErrors) {
            checkIfSignUpButtonClickable(wait, errorMessages);
        }

        if (errorMessages.isEmpty()) {
            System.out.println("Test passed successfully!");
        }

        return errorMessages;
    }

    private boolean checkAndCollectError(WebDriverWait wait, String errorElementId, String errorMessagePrefix, List<String> errorMessages) {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(errorElementId)));
            String errorText = errorElement.getText();
            errorMessages.add(errorMessagePrefix + errorText);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private void checkIfSignUpButtonClickable(WebDriverWait wait, List<String> errorMessages) {
        WebElement submitButton = driver.findElement(By.className("ubsStyle"));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(submitButton));
            submitButton.click();
        } catch (TimeoutException e) {
            errorMessages.add("Sign-up button is not clickable. Please check the form.");
        }

        boolean isLoggedIn = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("login-form")));
        if (!isLoggedIn) {
            errorMessages.add("Login did not happen. Invalid input data.");
        }
    }
}
