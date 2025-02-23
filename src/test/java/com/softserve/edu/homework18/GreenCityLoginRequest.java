package com.softserve.edu.homework18;

public class GreenCityLoginRequest {
    private String email;
    private String password;
    private String secretKey;

    public GreenCityLoginRequest(String email, String password, String secretKey) {
        this.email = email;
        this.password = password;
        this.secretKey = secretKey;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSecretKey() {
        return secretKey;
    }

    @Override
    public String toString() {
        return "greenCityLoginRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }
}
