package com.softserve.edu.framework.lib;

import com.softserve.edu.framework.data.UserRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static com.softserve.edu.framework.tests.TestRunner.fiveSec;

public class SignInUI {
    private WebDriver driver;
    private WebUtils utils;

    private Wait<WebDriver> wait;

    public SignInUI(WebDriver driver){
        WebUtils.checkCondition(driver == null, "Error, WebDriver is null");
        this.driver = driver;
        utils = new WebUtils(driver);
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(fiveSec))
                .pollingEvery(Duration.ofSeconds(500));
    }

    public void closeIframeIfExists(){
        List<WebElement> iframe = driver.findElements(By.cssSelector("iframe"));
        if(!iframe.isEmpty()){
            driver.switchTo().frame(iframe.getFirst());
            WebElement popupButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("close")));
            popupButton.click();
            driver.switchTo().defaultContent();
        }
    }

    public void changeLangToEn(){
        if(!Objects.equals(driver.findElement(By.cssSelector("li.lang-option span")).getText().toUpperCase(), "EN")){
            driver.findElement(By.cssSelector("li.lang-option span")).click();
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("li[aria-label='En'] span"))));
            driver.findElement(By.cssSelector("li[aria-label='En'] span")).click();
        }
    }

    public void prepareSignInForm(){
        changeLangToEn();
        openSignInPage();
        closeIframeIfExists();
    }

    public boolean isSignIn(){
        List<WebElement> signOutList = driver.findElements(By.cssSelector("a.header_user-name"));
        if(signOutList.isEmpty()){
            return false;
        }
        else{
            signOutList.getFirst().click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='drop-down-item']/a[contains(text(), 'Sign out')]")));
            List<WebElement> signOutListOptions = driver.findElements(By.xpath("//li[@class='drop-down-item']/a[contains(text(), 'Sign out')]"));
            signOutList.getFirst().click();
            return !signOutListOptions.isEmpty();
        }
    }

    public void openSignInPage(){
        WebUtils.checkCondition(isSignIn(), "Error: User is already signed in!");
        List<WebElement> signInButtonList = driver.findElements(By.cssSelector(".header_sign-in-link.tertiary-global-button"));
        WebUtils.checkCondition(signInButtonList.isEmpty(), "Error: There is no button to sign in!");
        signInButtonList.getFirst().click();
        utils.waitUntilElementVisible(wait, driver.findElement(By.className("wrapper")));
    }

    public void closeSignInPage(){
        List<WebElement> closeSignInButtonList = driver.findElements(By.cssSelector(".cross-btn"));
        WebUtils.checkCondition(closeSignInButtonList.isEmpty(), "Error: There is no button to close form!");
        closeSignInButtonList.getFirst().click();
        utils.waitUntilElementInvisible(wait, driver.findElement(By.className("cross-btn")));
    }

    public void signIn(){
        changeLangToEn();
        openSignInPage();
        utils.waitUntilElementVisible(wait, driver.findElement(By.cssSelector("label[for='email']")));
        utils.fillText(driver.findElement(By.id("email")), UserRepository.getDefault().getEmail());
        utils.waitUntilElementVisible(wait, driver.findElement(By.cssSelector("label[for='password']")));
        utils.fillText(driver.findElement(By.id("password")), UserRepository.getDefault().getPassword());
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".greenStyle")));
        driver.findElement(By.cssSelector(".greenStyle")).click();
    }

    public boolean isSignOut(){
        List<WebElement> signInButton = driver.findElements(By.cssSelector(".header_sign-in-link.tertiary-global-button"));
        return !signInButton.isEmpty();
    }

    public void signOut(){
        List<WebElement> signOutList = driver.findElements(By.cssSelector("a.header_user-name"));
        WebUtils.checkCondition(signOutList.isEmpty(), "There is no button to sigh out!");
        signOutList.getFirst().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='drop-down-item']/a[contains(text(), 'Sign out')]")));
        List<WebElement> signOutListOptions = driver.findElements(By.xpath("//li[@class='drop-down-item']/a[contains(text(), 'Sign out')]"));
        WebUtils.checkCondition(signOutListOptions.isEmpty(), "Can not find the option to sign out");
        signOutListOptions.getFirst().click();
    }

    public String getUIUserName(){
        WebUtils.checkCondition(isSignOut(), "Error: Cannot get user name, while user is not signed in!");
        driver.findElement(By.cssSelector("a[ng-reflect-router-link='/profile']")).click();
        return driver.findElement(By.cssSelector("p.name")).getText();
    }
}
