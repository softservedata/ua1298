package com.softserve.hw14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GreenCityTests extends TestRunner {

    // LOGGER
    private static final Logger logger = LoggerFactory.getLogger(GreenCityTests.class);
    private GreenCityPage page;

    @BeforeEach
    public void setUpPage() {
        page = new GreenCityPage(driver, wait, jsExecutor);
        page.switchToEnglish();
    }

    @DisplayName("Verify title of the page.")
    @Test
    public void verifyTitle() {
        logger.info("Starting test: verifyTitle()");
        assertEquals("GreenCity", driver.getTitle());
    }

    @DisplayName("Verify valid sign in and sign out.")
    @Test
    public void testValidSignInAndSignOut() {
        logger.info("Starting test: testValidSignInAndSignOut()");
        try {
            User user = UserRepository.getValidUser();
            page.signIn(user.getEmail(), user.getPassword());
            assertTrue(page.isUserLoggedIn(), "User is not logged in!");
            page.signOut();
        } catch (Exception e) {
            logger.error("Test failed: testValidSignInAndSignOut()", e);
            throw e;
        }
    }

    @ParameterizedTest
    @MethodSource("com.softserve.hw14.UserRepository#invalidUsers")
    @DisplayName("Verify invalid sign in.")
    public void testInvalidSignIn(User user, String expectedMessage) {
        logger.info("Starting test: testInvalidSignIn() | User: {}, Expected Message: {}", user.getEmail(), expectedMessage);
        try {
            page.signIn(user.getEmail(), user.getPassword());
            assertEquals(expectedMessage, page.getErrorMessage());
        } catch (Exception e) {
            logger.error("Test failed: testInvalidSignIn() | User: {}", user.getEmail(), e);
            throw e;
        }
    }
}