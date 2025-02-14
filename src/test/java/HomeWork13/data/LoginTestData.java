package HomeWork13.data;

public class LoginTestData {
    private String email;
    private String password;
    private String expectedMessage;

    public LoginTestData(String email, String password, String expectedMessage) {
        this.email = email;
        this.password = password;
        this.expectedMessage = expectedMessage;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getExpectedMessage() {
        return expectedMessage;
    }
}
