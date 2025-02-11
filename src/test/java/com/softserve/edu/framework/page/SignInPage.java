package com.softserve.edu.framework.page;

import org.hamcrest.CoreMatchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;


public class SignInPage {

  private final WebDriver driver;
  private final WebDriverWait wait;

    @FindBy(css = ".ng-star-inserted > h1")
    private    WebElement welcomeText;
    @FindBy(css = ".ng-star-inserted > h2")
    private    WebElement signInDetailsText;
    @FindBy(css = "label[for=email]")
    private    WebElement emailLabel;
    @FindBy(id = "email")
    private    WebElement emailInput;
    @FindBy(id = "password")
    private    WebElement passwordInput;
    @FindBy(css = "button.greenStyle")
    private    WebElement submitButton;
    @FindBy(css = ".alert-general-error")
    private    WebElement errorMessage;
    @FindBy(css = ".validation-password-error")
    private    WebElement errorPassword;
    @FindBy(xpath = "//*[@id=\"email-err-msg\"]/app-error/div")
    private    WebElement errorEmail;
    @FindBy(css = "span.show-hide-btn")
    private    WebElement showPassword;
    @FindBy(css = "a.header_sign-in-link")
    private    WebElement signInButton;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        PageFactory.initElements(driver,this);
    }


    public void clickSignIn(){
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    public void checkText(){

        wait.until(ExpectedConditions.visibilityOf(welcomeText));
        assertThat(signInDetailsText.getText(), CoreMatchers.is("Please enter your details to sign in."));
    }

    public void enterEmail(String email){
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password){
        passwordInput.sendKeys(password);
    }
    public void clickEmail(){
        wait.until(ExpectedConditions.elementToBeClickable(emailInput)).click();
    }
    public void clickPassword(){
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).click();
    }
    public void clickSubmit(){
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }
    public void clickShowPassword(){
        wait.until(ExpectedConditions.elementToBeClickable(showPassword)).click();
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public WebElement getErrorPassword() {
        return errorPassword;
    }

    public WebElement getErrorEmail() {
        return errorEmail;
    }
}
