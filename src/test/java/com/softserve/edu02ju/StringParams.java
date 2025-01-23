package com.softserve.edu02ju;

import java.util.stream.Stream;

public class StringParams {

    public static Stream<String> blankStrings() {
        return Stream.of(null, "", "  ");
    }

}
