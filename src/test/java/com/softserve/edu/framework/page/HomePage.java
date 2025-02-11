package com.softserve.edu.framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

 private final WebDriver driver;
 private final WebDriverWait wait;

    @FindBy(css = "span[ng-reflect-ng-style]")
    private WebElement switchLanguage;
    @FindBy(css = "span[ng-reflect-ng-class]")
    private WebElement languageEn;
    @FindBy(css = "a.header_sign-in-link")
    private    WebElement signInButton;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        PageFactory.initElements(driver,this);
    }

    public void switchLanguage(){
        wait.until(ExpectedConditions.elementToBeClickable(switchLanguage)).click();
        wait.until(ExpectedConditions.elementToBeClickable(languageEn)).click();
    }


}
