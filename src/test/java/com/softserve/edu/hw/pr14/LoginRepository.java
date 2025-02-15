package com.softserve.edu.hw.pr14;

import com.softserve.edu.hw.pr13.LoginData;

import java.util.Arrays;
import java.util.List;

public class LoginRepository {
    public static List<com.softserve.edu.hw.pr13.LoginData> getTestUsers() {
        return Arrays.asList(
                new LoginData("tester0202@gmail.com", "Qwerty123!")
        );
    }
}
