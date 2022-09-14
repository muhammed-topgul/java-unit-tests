package com.muhammedtopgul.junit.levelA.exercise;

import com.muhammedtopgul.exercise.FibonacciNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author muhammed-topgul
 * @since 12/09/2022 00:53
 */
@DisplayName("Level A (Beginner) Fibonacci Number Tests")
public class FibonacciNumberTest {
    @Test
    @DisplayName("Find Fibonacci Numbers for Specific Order")
    void findFibonacciNumbers() {
        FibonacciNumber fibonacciNumber = new FibonacciNumber();
        // 1 1 2 3 5 8
        assertThrows(IllegalArgumentException.class, () -> fibonacciNumber.find(0));

        assertAll("Fibonacci Numbers",
                () -> assertEquals(1, fibonacciNumber.find(1)),
                () -> assertEquals(1, fibonacciNumber.find(2)),
                () -> assertEquals(2, fibonacciNumber.find(3)),
                () -> assertEquals(3, fibonacciNumber.find(4)),
                () -> assertEquals(5, fibonacciNumber.find(5)),
                () -> assertEquals(8, fibonacciNumber.find(6))
        );
    }
}
