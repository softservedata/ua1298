package HW18;

public class LoginRequest {
    private String email;
    private String password;
    private String secretKey;

    public LoginRequest(String email, String password, String secretKey) {
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
        return "\n\tLoginRequest{" +
                "\n\t\temail='" + email + '\'' +
                ",\n\t\tpassword='" + password + '\'' +
                ",\n\t\tsecretKey='" + secretKey + '\'' +
                "\n\t}";
    }
}