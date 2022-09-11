package com.muhammedtopgul.exercise;

/**
 * @author muhammed-topgul
 * @since 12/09/2022 00:54
 */
public class FibonacciNumber {
    public int find(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException();
        }
        if (number == 1 || number == 2) {
            return 1;
        }
        return find(number - 2) + find(number - 1);
    }
}
