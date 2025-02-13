package com.softserve.edu.hw.pr13;

import java.util.Arrays;
import java.util.List;

public class LoginRepository {
    public static List<LoginData> getTestUsers() {
        return Arrays.asList(
                new LoginData("tester0202@gmail.com", "Qwerty123!")
        );
    }
}
