package com.softserve.framework.data;

import java.util.Objects;

public class User {

    private String email;
    private String password;
    private String secretKey;
    private transient String name;
    private transient String userId;

    public User(String email, String password, String secretKey) {
        this.email = email;
        this.password = password;
        this.secretKey = secretKey;
        name = "";
        userId = "0";
    }

    public User(String email, String password, String secretKey, String name, String userId) {
        this.email = email;
        this.password = password;
        this.secretKey = secretKey;
        this.name = name;
        this.userId = userId;
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

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(secretKey, user.secretKey) && Objects.equals(name, user.name) && Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, secretKey, name, userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
