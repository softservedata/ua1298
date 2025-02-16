package com.softserve.edu.framework.lib;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

public class WebUtils {
    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;

    public WebUtils(WebDriver driver){
        setDriver(driver);
    }

    public void setDriver(WebDriver driver){
        checkCondition(driver == null, "Error, WebDriver is null");
        this.driver = driver;
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    private void scrollToElement(WebElement webElement) {
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public void fillText(WebElement webElement, String text) {
        scrollToElement(webElement);
        webElement.click();
        webElement.clear();
        webElement.sendKeys(text);
    }

    public static void checkCondition(boolean condition, String errorMessage){
        if(condition){
            throw new RuntimeException(errorMessage);
        }
    }

    public void emptySignInPagePartClick(){
        driver.findElement(By.cssSelector(".ng-star-inserted")).click();
    }

    public void waitUntilElementVisible(Wait<WebDriver> wait, WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementClickable(Wait<WebDriver> wait, WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilElementInvisible(Wait<WebDriver> wait, WebElement element){
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public boolean isInvisible(String selector){
        String checkInVisibilityExpression = "return document.querySelectorAll(arguments[0]).length === 0";
        return (boolean) javascriptExecutor.executeScript(checkInVisibilityExpression, selector);
    }

    public boolean checkVisibilityOfManyElements(String[] selectors, boolean[] expectedResults){
        checkCondition(selectors.length != expectedResults.length, "Error: Arrays dimensions should have the same length!");
        return IntStream.range(0, selectors.length).allMatch(i -> isInvisible(selectors[i]) == expectedResults[i]);
    }

    public void clearAndClick(WebElement element){
        element.clear();
        element.click();
    }

    public void openNewTab(String link){
        javascriptExecutor.executeScript("window.open(arguments[0]);", link);
    }
}
