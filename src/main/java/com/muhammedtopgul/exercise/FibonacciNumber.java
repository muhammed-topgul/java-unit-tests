package com.muhammedtopgul.exercise;

import java.util.HashMap;
import java.util.Map;

/**
 * @author muhammed-topgul
 * @since 12/09/2022 00:54
 */
public class FibonacciNumber {
    private final Map<Integer, Integer> cache = new HashMap<>();

    public int find(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException();
        }
        if (number == 1 || number == 2) {
            cache.put(number, 1);
            return 1;
        }
        addCacheIfAbsent(number);
        return cache.get(number - 2) + cache.get(number - 1);
    }

    private void addCacheIfAbsent(Integer key) {
        if (cache.get(key - 2) == null) {
            cache.put(key - 2, find(key - 2));
        }
        if (cache.get(key - 1) == null) {
            cache.put(key - 1, find(key - 1));
        }
    }
}
