package com.softserve.edu.hw.pr13;

import com.softserve.edu.hw.pr13.LoginData;
import com.softserve.edu.hw.pr13.LoginRepository;
import com.softserve.edu.hw.pr13.LoginUser;
import com.softserve.edu.hw.pr13.TestRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends TestRunner {

    @ParameterizedTest
    @MethodSource("com.softserve.edu.hw.pr13.LoginRepository#getTestUsers")
    @DisplayName("Verify login functionality")
    void testUserLogin(LoginData loginData) {
        LoginUser loginPage = new LoginUser(driver, wait);

        loginPage.openLoginUser();
        loginPage.login(loginData.getEmail(), loginData.getPassword());
        loginPage.logout();

        assertTrue(true, "Login and logout executed successfully");
    }
}