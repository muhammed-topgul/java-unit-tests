package com.muhammedtopgul.junit.levelC.extension;

import org.junit.jupiter.api.extension.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

/**
 * @author muhammed-topgul
 * @since 27/09/2022 21:39
 */
public class BenchmarkExtension implements BeforeAllCallback, AfterAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback {
    private static final String START_TIME = "Start Time";
    private static Logger logger = Logger.getLogger(BenchmarkExtension.class.getName());

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        getStoreContainerClass(context).put(START_TIME, LocalDateTime.now());
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        LocalDateTime startTime = getStoreContainerClass(context).remove(START_TIME, LocalDateTime.class);
        long diff = startTime.until(LocalDateTime.now(), ChronoUnit.MILLIS);
        logger.info(String.format("Run time test for class <%s>: %s ms", context.getRequiredTestClass().getName(), diff));
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        getStoreContainerClass(context).put(START_TIME, LocalDateTime.now());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        LocalDateTime startTime = getStoreContainerClass(context).remove(START_TIME, LocalDateTime.class);
        long diff = startTime.until(LocalDateTime.now(), ChronoUnit.MILLIS);
        logger.info(String.format("Run time test for method <%s>: %s ms", context.getRequiredTestClass().getName(), diff));
    }

    private ExtensionContext.Store getStoreContainerClass(ExtensionContext extensionContext) {
        return extensionContext.getStore(ExtensionContext.Namespace.create(getClass(), extensionContext.getRequiredTestClass()));
    }

    private ExtensionContext.Store getStoreContainerMethod(ExtensionContext extensionContext) {
        return extensionContext.getStore(ExtensionContext.Namespace.create(getClass(), extensionContext.getRequiredTestClass()));
    }
}
