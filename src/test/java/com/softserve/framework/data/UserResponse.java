package com.softserve.framework.data;

import com.softserve.edu06sign.SigninResponse;

import java.util.Objects;

public class UserResponse {
    private int userId;
    private String accessToken;
    private String refreshToken;
    private String name;
    private boolean ownRegistrations;

    public UserResponse(int userId, String accessToken, String refreshToken, String name, boolean ownRegistrations) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.name = name;
        this.ownRegistrations = ownRegistrations;
    }

    public int getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getName() {
        return name;
    }

    public boolean isOwnRegistrations() {
        return ownRegistrations;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnRegistrations(boolean ownRegistrations) {
        this.ownRegistrations = ownRegistrations;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse that = (UserResponse) o;
        return userId == that.userId && ownRegistrations == that.ownRegistrations && Objects.equals(accessToken, that.accessToken) && Objects.equals(refreshToken, that.refreshToken) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, accessToken, refreshToken, name, ownRegistrations);
    }

    @Override
    public String toString() {
        return "SigninResponse{" +
                "userId=" + userId +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", name='" + name + '\'' +
                ", ownRegistrations=" + ownRegistrations +
                '}';
    }
}
