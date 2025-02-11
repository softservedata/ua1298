package com.softserve.edu.framework.tests;


import com.softserve.edu.framework.data.SignInData;
import com.softserve.edu.framework.services.SignInService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class SignInTests extends TestRunner {

    @Test
    public void testVerifyTitle() {
        Assertions.assertEquals("GreenCity", driver.getTitle());
    }


    @ParameterizedTest
    @MethodSource("com.softserve.edu.framework.data.SignInDataBase#getValidData")
    public void testSignIn(SignInData data) {
        SignInService signInService = new SignInService(driver, wait);
        signInService.signIn(data.getEmail(), data.getPassword());
    }

    @ParameterizedTest
    @MethodSource("com.softserve.edu.framework.data.SignInDataBase#getInValidEmailData")
    public void testNotValidEmail(SignInData data) {
        SignInService signInService = new SignInService(driver, wait);
        signInService.signIn(data.getEmail(), data.getPassword());
        assertTrue(signInService.isEmailErrorDisplayed(data.getExeptedError()));

    }

    @ParameterizedTest
    @MethodSource("com.softserve.edu.framework.data.SignInDataBase#getInValidPassword")
    public void testNotValidPassword(SignInData data) {
        SignInService signInService = new SignInService(driver, wait);
        signInService.signInNotValid(data.getEmail(), data.getPassword());
        assertTrue(signInService.isPasswordErrorDisplayed(data.getExeptedError()));

    }

    
}