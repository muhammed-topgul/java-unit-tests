package com.muhammedtopgul.exercise;

/**
 * @author muhammed-topgul
 * @since 11/09/2022 00:50
 */
public class FizzBuzz {
    public static String FIZZ = "Fizz";
    public static String BUZZ = "Fizz";
    public static String FIZZ_BUZZ = FIZZ + BUZZ;

    public String stringFor(int number) {
        if (number < 1 || number > 100) {
            throw new IllegalArgumentException("Number must be between 1 and 100.");
        }
        if (number % 15 == 0) {
            return FIZZ_BUZZ;
        }
        if (number % 3 == 0) {
            return FIZZ;
        }
        if (number % 5 == 0) {
            return BUZZ;
        }
        return String.valueOf(number);
    }
}
