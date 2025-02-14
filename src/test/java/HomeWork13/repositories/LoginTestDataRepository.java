package HomeWork13.repositories;

import HomeWork13.data.LoginTestData;
import java.util.Arrays;
import java.util.List;

public class LoginTestDataRepository {

    public static List<LoginTestData> getValidLoginData() {
        return Arrays.asList(
                new LoginTestData("levjuli98@gmail.com", "12345Yulia!", null)
        );
    }

    public static List<LoginTestData> getInvalidLoginData() {
        return Arrays.asList(
                new LoginTestData("testgreencity.com", "347593-!", "Please check that your e-mail address is indicated correctly"),
                new LoginTestData("validemail@ggmail.com", "juli12345!", "Bad email or password")
        );
    }
}
