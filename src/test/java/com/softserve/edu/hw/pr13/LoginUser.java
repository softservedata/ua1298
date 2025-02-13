package com.softserve.edu.hw.pr13;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginUser {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By signInButton = By.cssSelector(".ubs-header-sign-in");
    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By submitButton = By.cssSelector(".ubsStyle");
    private final By userHeader = By.cssSelector("a.ubs-header_user-name");
    private final By signOutButton = By.cssSelector(".drop-down-item");

    public LoginUser(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openLoginUser() {
        driver.get("http://localhost:4205/#/ubs");
    }
    public void login(String email, String password) {
        WebElement signUpBtn = wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signUpBtn);

        wait.until(ExpectedConditions.presenceOfElementLocated(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.presenceOfElementLocated(passwordField)).sendKeys(password);

        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);
    }

    public void logout() {
        WebElement headerUser = wait.until(ExpectedConditions.elementToBeClickable(userHeader));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", headerUser);

        WebElement signOut = wait.until(ExpectedConditions.elementToBeClickable(signOutButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signOut);
    }

}
