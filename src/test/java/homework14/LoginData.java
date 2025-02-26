package homework14;

public class LoginData {
    private final String email;
    private final String password;
    private final String expectedError;

    public LoginData(String email, String password, String expectedError) {
        this.email = email;
        this.password = password;
        this.expectedError = expectedError;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getExpectedError() { return expectedError; }
}