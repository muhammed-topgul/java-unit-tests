package com.muhammedtopgul.junit.levelB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author muhammed-topgul
 * @since 18/09/2022 01:07
 */
public interface CreateDomain<T> {
    T createDomain();

    @Test
    default void createDomainShouldBeImplemented() {
        assertNotNull(createDomain());
    }
}
