package com.muhammedtopgul.exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author muhammed-topgul
 * @since 25/09/2022 22:01
 */
public class PrimeFactor {
    public static List<Integer> generate(int i) {
        if (i <= 1) {
            return Collections.emptyList();
        }
        List<Integer> primeFactors = new ArrayList<>();
        for (int candidate = 2; i > 1 ; candidate++) {
            for (; i % candidate == 0; i /= candidate) {
                primeFactors.add(candidate);
            }
        }
        return primeFactors;
    }
}
