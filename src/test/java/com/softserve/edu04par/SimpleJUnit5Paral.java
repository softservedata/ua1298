package com.softserve.edu04par;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class SimpleJUnit5Paral {

    @BeforeAll
    public static void setup() {
        System.out.println("@BeforeAll executed, ThreadId = " + Thread.currentThread().getId());
    }

    @AfterAll
    public static void tear() {
        System.out.println("@AfterAll executed, ThreadId = " + Thread.currentThread().getId());
    }

    @BeforeEach
    public void setupThis() {
        System.out.println("\t@BeforeEach executed, ThreadId = " + Thread.currentThread().getId());
    }

    @AfterEach
    public void tearThis(TestInfo testInfo) {
        //System.out.println("\t\t\tgetTestMethod = " + testInfo.getTestMethod());
        //System.out.println("\t\t\tgetDisplayName = " + testInfo.getDisplayName());
        //
        System.out.println("\t@AfterEach executed, ThreadId = " + Thread.currentThread().getId());
    }

    @Test
    public void testOne() {
        System.out.println("\t\t@Test testOne(), ThreadId = " + Thread.currentThread().getId());
        Assertions.assertEquals(4, 2 + 2);
    }

    @Test
    public void testTwo() {
        System.out.println("\t\t@Test testTwo(), ThreadId = " + Thread.currentThread().getId());
        Assertions.assertTrue(6 == 2 + 4);
    }

}
