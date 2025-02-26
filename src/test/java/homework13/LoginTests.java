package homework13;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests extends TestRunner {
    private LoginActions loginActions;

    @FindBy(css = ".ubs-header-sign-in")
    private WebElement signInButton;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = ".ubsStyle")
    private WebElement signInSubmitButton;

    @FindBy(css = ".alert-general-error")
    private WebElement errorMessage;

    @FindBy(css = "a.ubs-header_user-name")
    private WebElement userProfileLink;

    @FindBy(css = ".drop-down-item")
    private WebElement signOutButton;

    @BeforeEach
    public void initPageFactory() {
        PageFactory.initElements(driver, this);
        loginActions = new LoginActions(wait);
    }

    @ParameterizedTest
    @MethodSource("homework13.LoginDataProvider#getPositiveLoginData")
    public void testSuccessfulLogin(LoginData loginData) {
        loginActions.navigateToSignIn(signInButton);
        loginActions.performLogin(emailInput, passwordInput, signInSubmitButton, loginData.getEmail(), loginData.getPassword());
        loginActions.waitForElementToBeVisible(userProfileLink);
        String actualEmail = userProfileLink.getText();
        assertEquals(loginData.getEmail(), actualEmail, "Email in profile does not match expected");
        userProfileLink.click();
        loginActions.waitForElementToBeClickable(signOutButton);
        signOutButton.click();
        loginActions.waitForElementToBeVisible(signInButton);
        assertTrue(signInButton.isDisplayed(), "Login button should be displayed after logging out.");
    }

    @ParameterizedTest
    @MethodSource("homework13.LoginDataProvider#getNegativeLoginData")
    public void testUnsuccessfulLogin(LoginData loginData) {
        loginActions.navigateToSignIn(signInButton);
        loginActions.performLogin(emailInput, passwordInput, signInSubmitButton, loginData.getEmail(), loginData.getPassword());
        loginActions.waitForElementToBeVisible(errorMessage);
        String actualError = errorMessage.getText();
        assertEquals(loginData.getExpectedError(), actualError, "Error message does not match expected");
    }

    @ParameterizedTest
    @MethodSource("homework13.LoginDataProvider#getMissingFieldsLoginData")
    public void testLoginMissingFields(LoginData loginData) {
        loginActions.navigateToSignIn(signInButton);
        loginActions.performLogin(emailInput, passwordInput, signInSubmitButton, loginData.getEmail(), loginData.getPassword());
        loginActions.waitForElementToBeVisible(errorMessage);
        String actualError = errorMessage.getText();
        assertEquals(loginData.getExpectedError(), actualError, "Error message does not match expected");
    }

    private static List<LoginData> getPositiveLoginData() throws IOException {
        return LoginDataProvider.getPositiveLoginData();
    }

    private static List<LoginData> getNegativeLoginData() throws IOException {
        return LoginDataProvider.getNegativeLoginData();
    }

    private static List<LoginData> getMissingFieldsLoginData() throws IOException {
        return LoginDataProvider.getMissingFieldsLoginData();
    }
}