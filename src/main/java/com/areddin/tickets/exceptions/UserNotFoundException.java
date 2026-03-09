package com.areddin.tickets.exceptions;

public class UserNotFoundException extends EventTicketException{
    /**
     * Constructs a UserNotFoundException with no detail message or cause.
     */
    public UserNotFoundException() {
    }

    /**
     * Constructs a UserNotFoundException with the specified detail message.
     *
     * @param message the detail message describing the user-not-found condition
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a UserNotFoundException with the specified detail message and cause.
     *
     * @param message the detail message describing the error condition
     * @param cause the underlying cause of this exception, or {@code null} if none
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a UserNotFoundException with the specified cause.
     *
     * @param cause the underlying cause of this exception; may be {@code null}
     */
    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a UserNotFoundException with a detail message, cause, and control over
     * suppression and stack trace writability.
     *
     * @param message             the detail message
     * @param cause               the cause of this exception, or {@code null} if none
     * @param enableSuppression   {@code true} to enable suppression, {@code false} to disable it
     * @param writableStackTrace  {@code true} if the stack trace should be writable, {@code false} otherwise
     */
    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
