package HW13.data;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SignUpPage {

    private final WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSignUpButton(WebDriverWait wait) {
        List<WebElement> signUpButtons = driver.findElements(By.cssSelector("div.ubs-header_sign-up-btn"));
        if (!signUpButtons.isEmpty()) {
            WebElement signUpButton = signUpButtons.get(0);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signUpButton);
            wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signUpButton);
        } else {
            throw new NoSuchElementException("Sign Up button not found.");
        }
    }
}
