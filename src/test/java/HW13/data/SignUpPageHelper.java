package HW13.data;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SignUpPageHelper {

    public static void openSignUpPage(WebDriver driver) {
        driver.get("http://localhost:4205/#/ubs");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.clickSignUpButton(wait);

        WebElement titleText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.title-text")));
        assertThat(titleText.getText(), is("Вітаємо!"));
    }

    public static void verifyPageTitle(WebDriver driver, String expectedTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        assertThat(driver.getTitle(), is(expectedTitle));
    }
}
