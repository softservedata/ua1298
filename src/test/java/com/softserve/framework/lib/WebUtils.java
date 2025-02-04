package com.softserve.framework.lib;

import com.softserve.framework.data.User;
import com.softserve.framework.data.UserResponse;
import com.softserve.framework.tests.TestRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WebUtils {

    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;

    public WebUtils(WebDriver driver) {
        setDriver(driver);
    }

    public void setDriver(WebDriver driver) {
        checkConditions(driver == null, "Error, WebDriver is null");
        this.driver = driver;
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    public static void checkConditions(boolean term, String text) {
        if (term) {
            throw new RuntimeException(text);
        }
    }

    public void refreshBrowser() {
        // Refresh browser
        driver.navigate().refresh();
        //
        TestRunner.presentationSleep(); // For Presentation ONLY
    }

    private void scrollToElement(WebElement webElement) {
        // Scrolling to an element by JavaScript
        //javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
        //
        // Scrolling by Action class
        Actions action = new Actions(driver);
        action.moveToElement(webElement).perform();
        //
        TestRunner.presentationSleep(); // For Presentation ONLY
    }

    public void activateWebElement(String locator) {
        javascriptExecutor.executeScript("document.querySelector('" + locator + "').removeAttribute('disabled')");
        //
        TestRunner.presentationSleep(); // For Presentation ONLY
    }

    protected void setItemLocalStorage(String item, String value) {
        javascriptExecutor.executeScript(String.format("window.localStorage.setItem('%s','%s');", item, value));
    }

    public void removeItemLocalStorage(String item) {
        javascriptExecutor.executeScript(String.format("window.localStorage.removeItem('%s');", item));
    }

    public void fillLocalStorage(UserResponse userResponse, User user) {
        // Fill Local Storage
        setItemLocalStorage("accessToken", userResponse.getAccessToken());
        setItemLocalStorage("language", "en");
        setItemLocalStorage("name", userResponse.getName());
        setItemLocalStorage("refreshToken", userResponse.getRefreshToken());
        setItemLocalStorage("userId", user.getUserId());
        //
        TestRunner.presentationSleep(); // For Presentation ONLY
    }

    public void fillText(WebElement webElement, String text) {
        scrollToElement(webElement);
        webElement.click();
        webElement.clear();
        webElement.sendKeys(text);
        //
        TestRunner.presentationSleep(); // For Presentation ONLY
    }
}
