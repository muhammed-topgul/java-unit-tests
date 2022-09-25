package com.muhammedtopgul.junit.levelA.exercise;

import org.junit.jupiter.api.*;

import java.util.Random;

/**
 * @author muhammed-topgul
 * @since 11/09/2022 01:22
 */
@DisplayName("Level A (Beginner Level) Standard Tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StandardTest {
    private static String oneInstancePerClass;
    private Integer oneInstancePerMethod;

    @BeforeAll
    static void initAll() {
        oneInstancePerClass = String.valueOf(new Random().nextInt());
        System.out.println("Init before all test method.");
    }

    @AfterAll
    static void tearDownAfterAll() {
        oneInstancePerClass = null;
        System.out.println("Tear down after all test method.");
    }

    @BeforeEach
    void init() {
        oneInstancePerMethod = new Random().nextInt();
        System.out.println("Init before each test method.");
    }

    @AfterAll
    void tearDown() {
        oneInstancePerMethod = null;
        System.out.println("Tear down before each test method.");
    }

    @Test
    void test001() {
        System.out.println(">>> test001() -> " + oneInstancePerMethod + " : " + oneInstancePerClass);
    }

    @Test
    void test002() {
        System.out.println(">>> test002() -> " + oneInstancePerMethod + " : " + oneInstancePerClass);
    }

    @Test
    @Disabled("This test will not work")
    void test003() {
        System.out.println(">>> test001() -> " + oneInstancePerMethod + " : " + oneInstancePerClass);
    }
}
