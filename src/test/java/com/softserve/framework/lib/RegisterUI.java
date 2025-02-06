package com.softserve.framework.lib;

import com.softserve.framework.data.User;
import com.softserve.framework.data.UserResponse;
import com.softserve.framework.tests.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class RegisterUI {

    protected final Logger logger = LoggerFactory.getLogger(RegisterUI.class);

    private WebDriver driver;
    private WebUtils webUtils;
    private RegisterRest registerRest;

    public RegisterUI(WebDriver driver) {
        WebUtils.checkConditions(driver == null, "Error, WebDriver is null");
        this.driver = driver;
        webUtils = new WebUtils(driver);
        registerRest = new RegisterRest();
    }

    private void closePopup() {
        logger.debug("Start closePopup()");
        //
        // Close popup window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestRunner.MIN_IMPLICITLY_WAIT));
        List<WebElement> iframe = driver.findElements(By.cssSelector("iframe"));
        if (iframe.size() > 0) {
            driver.switchTo().frame(iframe.get(0));
            List<WebElement> popupButton = driver.findElements(By.id("close"));
            if (popupButton.size() > 0) {
                popupButton.get(0).click();
            }
            driver.switchTo().defaultContent();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestRunner.MAX_IMPLICITLY_WAIT));
        TestRunner.presentationSleep(); // For Presentation ONLY
        logger.debug("Done closePopup()");
    }

    public boolean isSignout() {
        logger.debug("Start isSignout()");
        //
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestRunner.MIN_IMPLICITLY_WAIT));
        List<WebElement> singinButton = driver.findElements(By.cssSelector("div.main-content.app-container img.ubs-header-sing-in-img.ng-star-inserted"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestRunner.MAX_IMPLICITLY_WAIT));
        logger.debug("Done isSignout()");
        //
        return singinButton.size() > 0;
    }

    public void signin(User user, String urlPost) {
        // Click Signin button
        driver.findElement(By.cssSelector("div.main-content.app-container img.ubs-header-sing-in-img.ng-star-inserted")).click();
        TestRunner.presentationSleep(); // For Presentation ONLY
        //
        // Close popup window
        closePopup();
        //
        // Clear email placeholder and type email
        webUtils.fillText(driver.findElement(By.id("email")), user.getEmail());
        //
        // Clear password placeholder
        webUtils.fillText(driver.findElement(By.id("password")), user.getPassword());
        //
        // Activate Sign in Button
        webUtils.activateWebElement("button.ubsStyle");
        //
        // Signin by post method
        UserResponse userResponse = registerRest.signinPost(user, urlPost);
        //
        // Fill Local Storage
        webUtils.fillLocalStorage(userResponse, user);
        //
        driver.findElement(By.cssSelector("button.ubsStyle")).click();
        TestRunner.presentationSleep(); // For Presentation ONLY
        //
        // Refresh browser
        webUtils.refreshBrowser();
    }

    public String getUIName() {
        // Get name
        WebElement userName = driver.findElement(By.cssSelector("div.main-content.app-container li.ubs-user-name"));
        TestRunner.presentationSleep(); // For Presentation ONLY
        //
        return userName.getText();
    }

    public void signout() {
        // Open dropdown
        driver.findElement(By.cssSelector("div.main-content.app-container li.ubs-user-name")).click();
        TestRunner.presentationSleep(); // For Presentation ONLY
        //
        // Signout
        driver.findElement(By.cssSelector("div.main-content.app-container li[aria-label='sign-out'] > a")).click();
        TestRunner.presentationSleep(); // For Presentation ONLY
    }
}
