package com.softserve.edu.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthHelper {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public AuthHelper(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void login(WebElement signInButton, WebElement emailInput, WebElement passwordInput,
                      WebElement signInSubmitButton, String email, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
        WebElement emailField = wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(signInSubmitButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
    }
}
