package com.softserve.edu.framework.tests;

import com.softserve.edu.framework.data.User;
import com.softserve.edu.framework.data.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class GreenCityDockerTest extends TestRunner{
    private static final String[] errorsLocators = new String[]{"div.alert-general-error.ng-star-inserted", "div#email-err-msg div", "div.validation-password-error.ng-star-inserted div"};
    private static Wait<WebDriver> waitFiveSec = new WebDriverWait(driver, Duration.ofSeconds(fiveSec));
    private static final String[] expectedErrorMessages = new String[]{"Email is required", "Password is required", "Please fill all red fields",
            "Please check that your e-mail address is indicated correctly", "Password must be at least 8 characters long without spaces"};

    @Test
    public void verifyTitleTest() {
        assertEquals("GreenCity", driver.getTitle());
    }

    @Test
    public void checkSignInGeneralTest(){
        assertTrue(signInUI.isSignOut());

        signInUI.signIn();
        assertTrue(signInUI.isSignIn());

        //TODO: try to fix this method and find reason why is doesn`t work
        //driver.findElement(By.cssSelector("a[ng-reflect-router-link='/profile']")).click();
        //assertEquals("TestUser", driver.findElement(By.cssSelector("p.name")).getText());
        assertEquals("TestUser", signInUI.getUIUserName());

        signInUI.signOut();
        assertTrue(signInUI.isSignOut());
    }

    @ParameterizedTest
    @MethodSource("com.softserve.edu.framework.data.UserRepository#getValidDataUsers")
    public void checkSignInWithValidDataTest(User user) {
        assertTrue(signInUI.isSignOut());
        signInUI.prepareSignInForm();

        webUtils.fillText(emailInput, user.getEmail());
        assertEquals(emailInput.getDomProperty("value"), user.getEmail());

        webUtils.fillText(passwordInput, user.getPassword());
        assertEquals(passwordInput.getDomProperty("value"), user.getPassword());

        webUtils.waitUntilElementClickable(waitFiveSec, signInForm);
        signInSubmitButton.click();
        webUtils.waitUntilElementInvisible(waitFiveSec, signInForm);

        assertTrue(signInUI.isSignIn());
        signInUI.signOut();
        assertTrue(signInUI.isSignOut());
    }

    @Test
    public void checkSignInCheckLabelTextsTest(){
        signInUI.prepareSignInForm();

        assertEquals("Welcome back!", welcomeText.getText());
        assertEquals("Please enter your details to sign in.", signInDetailsText.getText());
        assertEquals("Email", emailLabel.getText());
        assertEquals("Password", passwordLabel.getText());
        assertEquals("Sign in", signInSubmitButton.getText());
    }

    @Test
    public void checkSignInCheckErrorMessagesWhileEmptyFieldsTest() throws InterruptedException {
        signInUI.prepareSignInForm();

        emailInput.click();
        webUtils.emptySignInPagePartClick();
        webUtils.waitUntilElementVisible(waitFiveSec, errorEmail);

        assertTrue(webUtils.checkVisibilityOfManyElements(errorsLocators, new boolean[]{true, false, true}));
        assertEquals(expectedErrorMessages[0], errorEmail.getText());

        passwordInput.click();
        webUtils.emptySignInPagePartClick();
        webUtils.waitUntilElementVisible(waitFiveSec, errorMessage);

        assertTrue(webUtils.checkVisibilityOfManyElements(errorsLocators, new boolean[]{false, true, true}));
        assertEquals(expectedErrorMessages[2], errorMessage.getText());

        webUtils.fillText(emailInput, UserRepository.getUserWithIncorrectData().getEmail());
        webUtils.emptySignInPagePartClick();
        webUtils.waitUntilElementVisible(waitFiveSec, errorPassword);

        assertTrue(webUtils.checkVisibilityOfManyElements(errorsLocators, new boolean[]{true, true, false}));
        assertEquals(expectedErrorMessages[1], errorPassword.getText());

        emailInput.clear();
        webUtils.fillText(emailInput,"a");
        emailInput.sendKeys(Keys.BACK_SPACE);
        webUtils.emptySignInPagePartClick();

        webUtils.waitUntilElementVisible(waitFiveSec, errorMessage);
        assertTrue(webUtils.checkVisibilityOfManyElements(errorsLocators, new boolean[]{false, true, true}));
        assertEquals(expectedErrorMessages[2], errorMessage.getText());

        signInUI.closeSignInPage();
    }

    @Test
    public void checkSignInCheckErrorMessagesWhileIncorrectDataTest() throws InterruptedException {
        signInUI.prepareSignInForm();

        webUtils.fillText(emailInput, UserRepository.getUserWithIncorrectData().getEmail());
        webUtils.emptySignInPagePartClick();
        webUtils.waitUntilElementVisible(waitFiveSec, errorEmail);

        Thread.sleep(55000);

        assertTrue(webUtils.checkVisibilityOfManyElements(errorsLocators, new boolean[]{true, false, true}));
        assertEquals(expectedErrorMessages[3], errorEmail.getText());

        webUtils.fillText(passwordInput, UserRepository.getUserWithIncorrectData().getPassword());
        webUtils.emptySignInPagePartClick();
        assertTrue(webUtils.checkVisibilityOfManyElements(errorsLocators, new boolean[]{true, false, false}));
        assertEquals(expectedErrorMessages[4], errorPassword.getText());
        assertEquals(expectedErrorMessages[3], errorEmail.getText());

        signInUI.closeSignInPage();
    }

    @ParameterizedTest
    @MethodSource("com.softserve.edu.framework.data.UserRepository#getInvalidDataUsers")
    public void checkSignInWithInvalidCredentialsTest(String email, String password) {
        signInUI.prepareSignInForm();

        webUtils.fillText(emailInput, email);
        assertEquals(emailInput.getDomProperty("value"), email);

        passwordInput.clear();
        assertFalse(signInSubmitButton.isEnabled());

        webUtils.fillText(passwordInput, password);
        assertEquals(passwordInput.getDomProperty("value"), password);

        webUtils.waitUntilElementClickable(waitFiveSec, signInSubmitButton);
        assertTrue(signInSubmitButton.isEnabled());

        signInSubmitButton.click();
        webUtils.waitUntilElementClickable(waitFiveSec, errorMessage);
        assertEquals("Bad email or password", errorMessage.getText());

        assertTrue(emailInput.isDisplayed());
        assertTrue(passwordInput.isDisplayed());
        assertTrue(signInSubmitButton.isDisplayed());
        crossButton.click();
    }

    @Test
    public void checkButtonClickableTest(){
        String login = UserRepository.getDefault().getEmail();
        String password = UserRepository.getDefault().getPassword();
        signInUI.prepareSignInForm();

        assertFalse(signInSubmitButton.isEnabled());
        webUtils.fillText(emailInput, login);
        assertFalse(signInSubmitButton.isEnabled());

        webUtils.clearAndClick(passwordInput);
        emailInput.click();
        assertFalse(signInSubmitButton.isEnabled());
        emailInput.clear();

        webUtils.fillText(passwordInput, password);
        assertFalse(signInSubmitButton.isEnabled());

        emailInput.click();
        passwordInput.click();
        assertFalse(signInSubmitButton.isEnabled());

        webUtils.fillText(emailInput, login);
        webUtils.waitUntilElementClickable(waitFiveSec, signInSubmitButton);
        assertTrue(signInSubmitButton.isEnabled());
    }

    @Test
    public void checkElementsOnSignInFormClickableAndEnabledTest(){
        signInUI.prepareSignInForm();

        webUtils.waitUntilElementVisible(waitFiveSec, emailInput);
        assertTrue(emailInput.isEnabled());

        webUtils.waitUntilElementVisible(waitFiveSec, passwordInput);
        assertTrue(passwordInput.isEnabled());

        webUtils.waitUntilElementVisible(waitFiveSec, driver.findElement(By.className("google-sign-in")));
        assertTrue(driver.findElement(By.className("google-sign-in")).isEnabled());

        webUtils.waitUntilElementVisible(waitFiveSec, driver.findElement(By.className("green-link")));
        assertTrue(driver.findElement(By.className("green-link")).isEnabled());

        webUtils.waitUntilElementClickable(waitFiveSec, crossButton);
        assertTrue(crossButton.isEnabled());
    }
}
