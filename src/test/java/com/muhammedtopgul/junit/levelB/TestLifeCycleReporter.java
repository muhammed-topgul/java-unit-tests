package com.muhammedtopgul.junit.levelB;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @author muhammed-topgul
 * @since 18/09/2022 00:57
 */
public interface TestLifeCycleReporter {
    @BeforeEach
    default void reportStart(TestInfo testInfo, TestReporter testReporter) {
        testInfo.getTestMethod()
                .ifPresent(method -> {
                    testReporter.publishEntry("Start", getMessage(method, "started"));
                });
    }

    @AfterEach
    default void reportEnd(TestInfo testInfo, TestReporter testReporter) {
        testInfo.getTestMethod()
                .ifPresent(method -> {
                    testReporter.publishEntry("End", getMessage(method, "ended"));
                });
    }

    private String getMessage(Method method, String status) {
        return method.getName() + " " + status + " at " + LocalDateTime.now();
    }
}
