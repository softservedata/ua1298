package HW13.lib;

import org.openqa.selenium.*;

import java.util.*;

public class RegistrationFormHelper {

    public static void fillAndSubmitForm(WebDriver driver, String email, String username, String password, String confirmPassword) {
        RegistrationForm registrationForm = new RegistrationForm(driver);
        registrationForm.fillForm(email, username, password, confirmPassword);
        registrationForm.submitForm();
    }

    public static void checkValidationErrors(WebDriver driver, String email, String username, String password, String confirmPassword) {
        RegistrationForm registrationForm = new RegistrationForm(driver);
        registrationForm.fillForm(email, username, password, confirmPassword);
        List<String> errorMessages = registrationForm.collectValidationErrors();
        errorMessages.forEach(System.out::println);
    }
}
