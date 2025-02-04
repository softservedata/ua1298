package com.softserve.framework.data;

import java.util.List;

public final class UserRepository {

    private UserRepository() {
    }

    public static User getDefault() {
        return getValidUser();
    }

    public static User getValidUser() {
        return new User("lsd09559@kisoq.com",
                "Qwerty_1",
                "UD~3tDW<$K.rEk$IELFTVQwWU$-tN%IX~q>`NuMpxhUMb$D",
                    "Qwerty1",
                    "3753");
    }

    //public static User getInvalidUser() {
    //}

    //public static User getNewUser() {
    //}

    //public static List<User> getDBUsers() {
    //}

}
