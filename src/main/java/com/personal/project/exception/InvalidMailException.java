package com.personal.project.exception;

/**
 * Invalid mail exception.
 * This exception is thrown when the mail is invalid.
 */
public class InvalidMailException extends RuntimeException {

    /**
     * Constructor.
     * @param message the message
     */
    public InvalidMailException(final String message) {
        super(message);
    }
}
