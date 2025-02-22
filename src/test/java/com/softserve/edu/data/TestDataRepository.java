package com.softserve.edu.data;

import java.util.Arrays;
import java.util.List;

public class TestDataRepository {
    public static List<TestData> getValidCredentials() {
        return List.of(
                new TestData("levjuli98@gmail.com", "12345Yulia!", null)
        );
    }

    public static List<TestData> getInvalidCredentials() {
        return Arrays.asList(
                new TestData("testgreencity.com", "347593-!", "Please check that your e-mail address is indicated correctly"),
                new TestData("validemail@ggmail.com", "juli12345!", "Bad email or password")
        );
    }
}
