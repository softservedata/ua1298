package HW13.test;

import HW13.lib.RegistrationFormHelper;
import HW13.data.SignUpPageHelper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LoginTest extends TestSetup {

    @BeforeEach
    public void setUp() {
        SignUpPageHelper.openSignUpPage(driver);
    }

    @ParameterizedTest
    @CsvSource({
            "validuser@example.com, ValidUser, ValidPass123!, ValidPass123!, GreenCity"
    })
    @Order(1)
    public void successfulRegistration(String email, String username, String password, String confirmPassword, String expectedTitle) {
        RegistrationFormHelper.fillAndSubmitForm(driver, email, username, password, confirmPassword);
        SignUpPageHelper.verifyPageTitle(driver, expectedTitle);
    }

    @ParameterizedTest
    @CsvSource({
            ", ValidUser, ValidPass123!, ValidPass123!",
            "invalidemail@, ValidUser, ValidPass123!, ValidPass123!",
            "existinguser@example.com, ValidUser, ValidPass123!, ValidPass123!",
            "validuser@example.com, , ValidPass123!, ValidPass123!",
            "validuser@example.com, Invalid@User, ValidPass123!, ValidPass123!",
            "validuser@example.com, ValidUser, , ValidPass123!",
            "validuser@example.com, ValidUser, ValidPass123!, "
    })
    @Order(2)
    public void registrationValidationErrors(String email, String username, String password, String confirmPassword) {
        RegistrationFormHelper.checkValidationErrors(driver, email, username, password, confirmPassword);
    }
}
