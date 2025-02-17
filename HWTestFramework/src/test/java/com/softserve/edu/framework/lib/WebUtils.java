package com.softserve.edu.framework.lib;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import java.util.stream.IntStream;

public class WebUtils {
    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;
    private static final Logger logger = LoggerFactory.getLogger(WebUtils.class);

    public WebUtils(WebDriver driver){
        setDriver(driver);
    }

    public void setDriver(WebDriver driver){
        checkCondition(driver == null, "Error, WebDriver is null");
        this.driver = driver;
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    private void scrollToElement(WebElement webElement) {
        logger.debug(() -> "Start scrollToElement()");
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
        logger.debug(() -> "End scrollToElement()");
    }

    public void fillText(WebElement webElement, String text) {
        logger.debug(() -> "Start fillText()");
        scrollToElement(webElement);
        webElement.click();
        webElement.clear();
        webElement.sendKeys(text);
        logger.debug(() -> "End fillText()");
    }

    public static void checkCondition(boolean condition, String errorMessage){
        logger.debug(() -> "Start checkCondition()");
        if(condition){
            logger.error(() -> "Oops, sth went wrong " + errorMessage);
            throw new RuntimeException(errorMessage);
        }
        logger.debug(() -> "End checkCondition()");
    }

    public void emptySignInPagePartClick(){
        logger.debug(() -> "Start emptySignInPagePartClick()");
        driver.findElement(By.cssSelector(".ng-star-inserted")).click();
        logger.debug(() -> "End emptySignInPagePartClick()");
    }

    public void waitUntilElementVisible(Wait<WebDriver> wait, WebElement element){
        logger.debug(() -> "Start waitUntilElementVisible()");
        wait.until(ExpectedConditions.visibilityOf(element));
        logger.debug(() -> "End waitUntilElementVisible()");
    }

    public void waitUntilElementClickable(Wait<WebDriver> wait, WebElement element){
        logger.debug(() -> "Start waitUntilElementClickable()");
        wait.until(ExpectedConditions.elementToBeClickable(element));
        logger.debug(() -> "End waitUntilElementClickable()");
    }

    public void waitUntilElementInvisible(Wait<WebDriver> wait, WebElement element){
        logger.debug(() -> "Start waitUntilElementInvisible()");
        wait.until(ExpectedConditions.invisibilityOf(element));
        logger.debug(() -> "End waitUntilElementInvisible()");
    }

    public boolean isInvisible(String selector){
        logger.debug(() -> "Start isInvisible()");
        String checkInVisibilityExpression = "return document.querySelectorAll(arguments[0]).length === 0";
        logger.debug(() -> "End isInvisible()");
        return (boolean) javascriptExecutor.executeScript(checkInVisibilityExpression, selector);
    }

    public boolean checkVisibilityOfManyElements(String[] selectors, boolean[] expectedResults){
        logger.debug(() -> "Start checkVisibilityOfManyElements()");
        checkCondition(selectors.length != expectedResults.length, "Error: Arrays dimensions should have the same length!");
        logger.debug(() -> "End checkVisibilityOfManyElements()");
        return IntStream.range(0, selectors.length).allMatch(i -> isInvisible(selectors[i]) == expectedResults[i]);
    }

    public void clearAndClick(WebElement element){
        logger.debug(() -> "Start clearAndClick()");
        element.clear();
        element.click();
        logger.debug(() -> "End clearAndClick()");
    }

    public void openNewTab(String link){
        logger.debug(() -> "Start openNewTab()");
        javascriptExecutor.executeScript("window.open(arguments[0]);", link);
        logger.debug(() -> "End openNewTab()");
    }
}
