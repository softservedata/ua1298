package com.softserve.hw13;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class UserRepository {

    public static User getValidUser() {
        return new User("bonkatari@gmail.com", "Gfhjkm2002!");
    }

    public static Stream<Arguments> invalidUsers() {
        return Stream.of(
                Arguments.of(new User("samplestesgreencity.com", "uT346^^^erw"), "Please check that your e-mail address is indicated correctly"),
                Arguments.of(new User("validemail@example.com", "gfhjkm12345678"), "Bad email or password")
        );
    }
}