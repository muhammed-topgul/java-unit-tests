package com.muhammedtopgul.junit.levelC.extension;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.util.logging.Logger;

/**
 * @author muhammed-topgul
 * @since 27/09/2022 22:15
 */
public class IllegalArgumentExceptionHandler implements TestExecutionExceptionHandler {
    private final static Logger logger = Logger.getLogger(IllegalArgumentExceptionHandler.class.getName());

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        String msg = String.format("IllegalArgumentException was thrown by a method %s with desc: %s",
                context.getRequiredTestClass(),
                throwable.getMessage());
        logger.severe(msg);
    }
}
