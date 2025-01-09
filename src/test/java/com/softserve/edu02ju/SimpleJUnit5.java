package com.softserve.edu02ju;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SimpleJUnit5 {

    @BeforeAll
    public static void setup() {
        System.out.println("@BeforeAll executed");
    }

    @AfterAll
    public static void tear() {
        System.out.println("@AfterAll executed");
    }

    @BeforeEach
    public void setupThis() {
        System.out.println("\t@BeforeEach executed");
    }

    @AfterEach
    public void tearThis() {
        System.out.println("\t@AfterEach executed");
    }

    @Test
    @Order(3)
    public void testOne() {
        // Read from files
        System.out.println("\t\t@Test testOne()");
        Assertions.assertEquals(4, 2 + 2);
    }

    @Test
    @Order(1)
    public void testTwo() {
        System.out.println("\t\t@Test testTwo()");
        Assertions.assertEquals(6, 2 + 4);
    }

    @Test
    @Order(4)
    void testExpectedException() {
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            //Code under test
            int i = 0;
            i = 10 / (i + 0);
        });
        System.out.println("\t\tMessage = " + thrown.getMessage());
        Assertions.assertEquals("/ by zero", thrown.getMessage());
    }

    @Test
    @Order(2)
    void testExpectedException2() {
        NumberFormatException thrown = Assertions.assertThrows(NumberFormatException.class, () -> {
            int k = Integer.parseInt("One");
        }, "NumberFormatException was expected");
        System.out.println("\t\tMessage = " + thrown.getMessage());
        Assertions.assertEquals("For input string: \"One\"", thrown.getMessage());
    }
}
