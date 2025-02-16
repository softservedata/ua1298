package com.softserve.edu.framework.data;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public final class UserRepository {
    private UserRepository(){

    }

    public static User getDefault(){
        return getUserWithValidData();
    }

    public static User getUserWithValidData(){
        return new User("somovoy449@myweblaw.com", "Qwerty1!");
    }

    public static User getUserWithInvalidData(){
        return new User("test.login@sth.com", "invalid pass");
    }

    public static User getUserWithInvalidEmail(){
        return new User("test.login@sth.com", "Qwerty1!");
    }

    public static User getUserWithInvalidPassword(){
        return new User("somovoy449@mywebw.com", "invalid pass");
    }

    public static Stream<Arguments> getInvalidDataUsers(){
        return Stream.of(
                Arguments.of(getUserWithInvalidEmail().getEmail(), getUserWithInvalidEmail().getPassword()),
                Arguments.of(getUserWithInvalidData().getEmail(), getUserWithInvalidData().getPassword()),
                Arguments.of(getUserWithInvalidPassword().getEmail(), getUserWithInvalidPassword().getPassword())
        );
    }

    public static Stream<Arguments> getValidDataUsers(){
        return Stream.of(
                Arguments.of(getDefault()),
                Arguments.of(new User("brulonneiffouwi-4597@yopmail.com", "Qwerty2@")),
                Arguments.of(new User("yautouddeinnuvi-7148@yopmail.com", "Qwerty3#"))
        );
    }

    public static User getUserWithIncorrectData(){
        return new User("justTest.com", "pass");
    }
}
