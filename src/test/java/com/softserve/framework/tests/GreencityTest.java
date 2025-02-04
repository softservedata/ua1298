package com.softserve.framework.tests;

import com.softserve.framework.data.User;
import com.softserve.framework.data.UserRepository;
import com.softserve.framework.data.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class GreencityTest extends TestRunner {

    /*
    private static Stream<Arguments> userProvider() {
        return Stream.of(
                Arguments.of(new User("lsd09559@kisoq.com", "Qwerty_1",
                        "UD~3tDW<$K.rEk$IELFTVQwWU$-tN%IX~q>`NuMpxhUMb$D",
                        "Qwerty1", "3753"))
        );
    }
    */

    private static Stream<Arguments> userProvider() {
        return Stream.of(
                Arguments.of(UserRepository.getValidUser())
        );
    }

    @DisplayName("Should successful sigin")
    @ParameterizedTest(name = "{index} => userProvider={0}")
    @MethodSource("userProvider")
        public void checkSignin(User user) {
        // Singin
        registerUI.signin(user, POST_URL);
        //
        // Get UI Name
        String uiName = registerUI.getUIName();
        //
        // Check UI Name
        Assertions.assertEquals(user.getName(), uiName);
        //
        // Signout
        registerUI.signout();
        //
        // is signout
        Assertions.assertTrue(registerUI.isSignout());
    }
}
