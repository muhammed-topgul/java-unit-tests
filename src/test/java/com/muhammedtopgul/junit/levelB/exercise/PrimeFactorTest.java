package com.muhammedtopgul.junit.levelB.exercise;

import com.muhammedtopgul.exercise.PrimeFactor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author muhammed-topgul
 * @since 25/09/2022 22:04
 */
@DisplayName("Level B (Intermediate Level) Prime Factor Test")
public class PrimeFactorTest {
    private final Map<Integer, List<Integer>> primeFactorExpectations = new HashMap<>();

    @BeforeEach
    void setUp() {
        primeFactorExpectations.put(1, Collections.emptyList());
        primeFactorExpectations.put(2, List.of(2));
        primeFactorExpectations.put(3, List.of(3));
        primeFactorExpectations.put(4, List.of(2, 2));
        primeFactorExpectations.put(5, List.of(5));
        primeFactorExpectations.put(6, List.of(2, 3));
        primeFactorExpectations.put(7, List.of(7));
        primeFactorExpectations.put(8, List.of(2, 2, 2));
        primeFactorExpectations.put(9, List.of(3, 3));
    }

    @Test
    @DisplayName("Generate PrimeFactors")
    void generateWithStandardTest() {
        assertEquals(Collections.emptyList(), PrimeFactor.generate(1));
        assertEquals(List.of(2), PrimeFactor.generate(2));
        assertEquals(List.of(3), PrimeFactor.generate(3));
        assertEquals(List.of(2, 2), PrimeFactor.generate(4));
        assertEquals(List.of(5), PrimeFactor.generate(5));
        assertEquals(List.of(2, 3), PrimeFactor.generate(6));
        assertEquals(List.of(7), PrimeFactor.generate(7));
        assertEquals(List.of(2, 2, 2), PrimeFactor.generate(8));
        assertEquals(List.of(3, 3), PrimeFactor.generate(9));
    }

    @RepeatedTest(9)
    @DisplayName("Repeated Test")
    void generateWithRepeatedTest(RepetitionInfo repetitionInfo) {
        int currentRepetition = repetitionInfo.getCurrentRepetition();
        assertEquals(primeFactorExpectations.get(currentRepetition), PrimeFactor.generate(currentRepetition));
    }

    @ParameterizedTest
    @DisplayName("Parametirized Test")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void generateWithParameterizedTest(Integer number) {
        assertEquals(primeFactorExpectations.get(number), PrimeFactor.generate(number));
    }

    @TestFactory
    @DisplayName("Dynamic Test")
    Stream<DynamicTest> generateWithDynamicTest() {
        return Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .map(number -> DynamicTest.dynamicTest("Generate prime factors for " + number,
                        () -> assertEquals(primeFactorExpectations.get(number), PrimeFactor.generate(number))
                ));
    }
}
