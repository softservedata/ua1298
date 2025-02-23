package com.softserve.edu.homework18;

public class GreenCityLoginResponce {

    private int userId;
    private String accessToken;
    private String refreshToken;
    private String name;
    private boolean ownRegistrations;

    public GreenCityLoginResponce(int userId, String accessToken, String refreshToken, String name, boolean ownRegistrations) {
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

    @Override
    public String toString() {
        return "\n\tgreenCityLoginResponce{" +
                "\n\t\tuserId=" + userId +
                "\n\t\taccessToken='" + accessToken + '\'' +
                "\n\t\trefreshToken='" + refreshToken + '\'' +
                "\n\t\tname='" + name + '\'' +
                "\n\t\townRegistrations=" + ownRegistrations +
                "\n\t}";
    }
}
