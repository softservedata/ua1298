package homework13;

import java.util.Objects;

public class LoginData {
    private final String email;
    private final String password;
    private final String expectedError;

    public LoginData(String email, String password, String expectedError) {
        this.email = email;
        this.password = password;
        this.expectedError = expectedError;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getExpectedError() {
        return expectedError;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", expectedError='" + expectedError + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginData loginData = (LoginData) o;
        return Objects.equals(email, loginData.email) &&
                Objects.equals(password, loginData.password) &&
                Objects.equals(expectedError, loginData.expectedError);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, expectedError);
    }
}