package com.softserve.edu.framework.data;

import java.util.List;

public class SignInDataBase {

    public static List<SignInData> getValidData() {
        return List.of(
                new SignInData("samplestest@greencity.com", "weyt3$Guew^", null),
                new SignInData("anotheruser@greencity.com", "anotherpassword", null),
                new SignInData("user13@gmail.com", "passWord1!", null)
        );
    }

    public static List<SignInData> getInValidEmailData() {
        return List.of(
                new SignInData("samplestesgreencity.com", "uT346^^^erw", "Please check that your e-mail address is indicated correctly"),
                new SignInData("емейл@gmail.сom ", "uT346^^^erw", "Please check that your e-mail address is indicated correctly"),
                new SignInData("daffa@gmail", "uT346^^^erw", "Please check that your e-mail address is indicated correctly")
        );
    }

    public static List<SignInData> getInValidPassword(){
        return List.of(
               new SignInData("samplestesgreencity@gmail.com ","1234567","Password must be at least 8 characters long without spaces"),
               new SignInData("samplestesgreencity@gmail.com "," aaaaaaaaaaaaaaaaaaaaa","Password must be less than 20 characters long without spaces")
        );
    }
}
