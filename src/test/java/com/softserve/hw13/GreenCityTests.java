package com.softserve.hw13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GreenCityTests extends TestRunner {

    private GreenCityPage page;

    @BeforeEach
    public void setUpPage() {
        page = new GreenCityPage(driver, wait, jsExecutor);
        page.switchToEnglish();
    }

    @DisplayName("Verify title of the page.")
    @Test
    public void verifyTitle() {
        assertEquals("GreenCity", driver.getTitle());
    }

    @DisplayName("Verify valid sign in and sign out.")
    @Test
    public void testValidSignInAndSignOut() {
        User user = UserRepository.getValidUser();
        page.signIn(user.getEmail(), user.getPassword());
        assertTrue(page.isUserLoggedIn(), "User is not logged in!");
        page.signOut();
    }

    @ParameterizedTest
    @MethodSource("com.softserve.hw13.UserRepository#invalidUsers")
    @DisplayName("Verify invalid sign in.")
    public void testInvalidSignIn(User user, String expectedMessage) {
        page.signIn(user.getEmail(), user.getPassword());
        assertEquals(expectedMessage, page.getErrorMessage());
    }
}