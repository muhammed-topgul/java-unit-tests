package com.muhammedtopgul.exception;

/**
 * @author muhammed-topgul
 * @since 11/09/2022 02:10
 */
public class NoCourseFoundForStudentException extends RuntimeException {

    public NoCourseFoundForStudentException(String message) {
        super(message);
    }
}
