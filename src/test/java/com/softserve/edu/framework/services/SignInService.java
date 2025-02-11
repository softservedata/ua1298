package com.softserve.edu.framework.services;

import com.softserve.edu.framework.page.SignInPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInService {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final SignInPage signInPage;

    public SignInService(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.signInPage = new SignInPage(driver);
    }

    public void signIn(String email, String password) {
        signInPage.clickSignIn();
        signInPage.checkText();
        signInPage.enterEmail(email);
        signInPage.enterPassword(password);
        signInPage.clickSubmit();
    }
    public void signInNotValid(String email, String password) {
        signInPage.clickSignIn();
        signInPage.enterEmail(email);
        signInPage.enterPassword(password);
        signInPage.clickShowPassword();
        signInPage.clickSubmit();
    }

    public boolean isEmailErrorDisplayed(String expectedError) {
        return wait.until(ExpectedConditions.textToBePresentInElement(signInPage.getErrorEmail(), expectedError));
    }

    public boolean isPasswordErrorDisplayed(String expectedError) {
        return wait.until(ExpectedConditions.textToBePresentInElement(signInPage.getErrorPassword(), expectedError));
    }

}
