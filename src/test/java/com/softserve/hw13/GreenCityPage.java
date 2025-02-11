package com.softserve.hw13;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GreenCityPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jsExecutor;

    public GreenCityPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor jsExecutor) {
        this.driver = driver;
        this.wait = wait;
        this.jsExecutor = jsExecutor;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//img[contains(@class, 'ubs-header-sing-in-img') and @alt='sing in button']")
    private WebElement signInButton;

    @FindBy(xpath = "//h1[contains(text(), 'Welcome back!')]")
    private WebElement welcomeText;

    @FindBy(xpath = "//h2[contains(text(), 'Please enter your details to sign in.')]")
    private WebElement signInDetailsText;

    @FindBy(xpath = "//label[@for='email' and contains(text(), 'Email')]")
    private WebElement emailLabel;

    @FindBy(xpath = "//input[@id='email' and @type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='password' and @type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(@class, 'greenStyle') and @type='submit']")
    private WebElement signInSubmitButton;

    @FindBy(css = ".mat-simple-snackbar > span")
    private WebElement result;

    @FindBy(xpath = "//div[contains(@class, 'alert-general-error')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[contains(@class, 'alert-general-error') and contains(text(), 'Bad email or password')]")
    private WebElement errorPassword;

    @FindBy(xpath = "//div[@id='email-err-msg']//div[contains(text(), 'Please check that your e-mail address is indicated correctly')]")
    private WebElement errorEmail;

    @FindBy(xpath = "//ul[contains(@class, 'header_lang')]//li[@role='option' and contains(@aria-label, 'english')]")
    private WebElement languageSwitcher;

    @FindBy(xpath = "//span[text()='En']")
    private WebElement englishOption;

    @FindBy(xpath = "//div[contains(@class, 'mat-tab-label-content') and contains(text(), 'My habits')]")
    private WebElement myHabitsTab;

    @FindBy(xpath = "//a[contains(@class, 'header_user-name')]")
    private WebElement userMenuButton;

    @FindBy(xpath = "//li[@role='button' and contains(@aria-label, 'sign-out')]")
    private WebElement signOutButton;

    @FindBy(xpath = "//a[@class='close-modal-window']")
    private WebElement closeModalButton;

    private void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private void waitForPageToLoad() {
        wait.until(driver -> jsExecutor.executeScript("return document.readyState").equals("complete"));
    }

    public void clickWithJS(WebElement element) {
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public void sendKeysWithWait(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    public void switchToEnglish() {
        waitForVisibility(languageSwitcher);
        waitForClickable(languageSwitcher);
        clickWithJS(languageSwitcher);
        waitForVisibility(englishOption);
        waitForClickable(englishOption);
        clickWithJS(englishOption);

        waitForPageToLoad();
    }

    private boolean isElementVisible(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage(){
        if (isElementVisible(errorPassword)) {
            return errorPassword.getText();
        } else if (isElementVisible(errorEmail)) {
            return errorEmail.getText();
        }
        return "No error message displayed";
    }

    public void signIn(String email, String password) {
        clickWithJS(signInButton);
        sendKeysWithWait(emailInput, email);
        sendKeysWithWait(passwordInput, password);
        clickWithJS(signInSubmitButton);
    }

    public void signOut() {
        waitForVisibility(userMenuButton);
        userMenuButton.click();
        waitForVisibility(signOutButton);
        signOutButton.click();
    }

    public boolean isUserLoggedIn() {
        return wait.until(ExpectedConditions.visibilityOf(myHabitsTab)).isDisplayed();
    }

    public boolean isUserLoggedOut() {
        return wait.until(ExpectedConditions.visibilityOf(signInButton)).isDisplayed();
    }
}
