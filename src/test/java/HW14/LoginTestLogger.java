package HW14;

import HW13.lib.RegistrationFormHelper;
import HW13.data.SignUpPageHelper;
import HW13.test.TestSetup;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginTestLogger extends TestSetup {

    private static final Logger logger = Logger.getLogger(LoginTestLogger.class.getName());

    @BeforeEach
    public void setUp() {
        logger.info("Starting test: Opening Sign-Up Page");
        SignUpPageHelper.openSignUpPage(driver);
    }

    @ParameterizedTest
    @CsvSource({
            "validuser@example.com, ValidUser, ValidPass123!, ValidPass123!, GreenCity"
    })
    @Order(1)
    public void successfulRegistration(String email, String username, String password, String confirmPassword, String expectedTitle) {
        try {
            logger.info("Executing successful registration test");
            RegistrationFormHelper.fillAndSubmitForm(driver, email, username, password, confirmPassword);
            SignUpPageHelper.verifyPageTitle(driver, expectedTitle);
            logger.info("Test passed: Registration successful");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Test failed: Registration was not successful", e);
            throw e;
        }
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
        try {
            logger.info("Executing registration validation errors test");
            RegistrationFormHelper.checkValidationErrors(driver, email, username, password, confirmPassword);
            logger.info("Test passed: Validation errors handled correctly");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Test failed: Unexpected behavior in validation errors", e);
            throw e;
        }
    }
}
