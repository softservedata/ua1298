package homework14;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class LoginDataProvider {
    private static final Logger logger = Logger.getLogger(LoginDataProvider.class.getName());

    public static List<LoginData> getPositiveLoginData() {
        return Arrays.asList(new LoginData("gim33370@bcooq.com", "Anna$5685-ANNA", null));
    }

    public static List<LoginData> getNegativeLoginData() {
        return Arrays.asList(new LoginData("samplestest@greencity.com", "weyt3$Guew^", "Invalid credentials"));
    }
}
