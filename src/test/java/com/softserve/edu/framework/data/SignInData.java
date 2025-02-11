package com.softserve.edu.framework.data;

public class SignInData {

    private final String email;
    private final String password;
    private final String expectedError;

    public SignInData(String email, String password, String exeptedError) {
        this.email = email;
        this.password = password;
        this.expectedError = exeptedError;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getExeptedError() {
        return expectedError;
    }
}
