package com.muhammedtopgul.junit.levelA.exercise;

import com.muhammedtopgul.exercise.FizzBuzz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * @author muhammed-topgul
 * @since 11/09/2022 00:50
 */
@DisplayName("Level A (Beginner Level)  FizzBuzz Tests")
class FizzBuzzTest {
    private FizzBuzz fizzBuzz;

    @BeforeEach
    void setUp() {
        fizzBuzz = new FizzBuzz();
    }

    @Test
    void returnFizzWhenTheNumberIsDividedByThree() {
        assertEquals(FizzBuzz.FIZZ, fizzBuzz.stringFor(3));
    }

    @Test
    void returnFizzWhenTheNumberIsDividedByFive() {
        assertEquals(FizzBuzz.BUZZ, fizzBuzz.stringFor(5));
    }

    @Test
    void returnFizzBuzzWhenTheNumberIsDividedBothOfThreeAndFive() {
        assertEquals(FizzBuzz.FIZZ_BUZZ, fizzBuzz.stringFor(15));
    }

    @Test
    void returnTheNumberItselfWhenTheNumberIsNotDividedBothOfThreeAndFive() {
        assertEquals("17", fizzBuzz.stringFor(17));
    }

    @Test
    void throwsIllegalArgumentExceptionWhenTheNumberIsMoreThan100() {
        assertThrows(IllegalArgumentException.class, () -> fizzBuzz.stringFor(105));
    }

    @Test
    void throwsIllegalArgumentExceptionWhenTheNumberIsLessThen1() {
        assertThrows(IllegalArgumentException.class, () -> fizzBuzz.stringFor(-1));
    }
}