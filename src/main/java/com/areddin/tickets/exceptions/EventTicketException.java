package com.areddin.tickets.exceptions;

public class EventTicketException extends RuntimeException{
    /**
     * Constructs a new EventTicketException with no detail message or cause.
     */
    public EventTicketException() {
    }

    /**
     * Constructs a new EventTicketException with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception
     */
    public EventTicketException(String message) {
        super(message);
    }

    /**
     * Constructs a new EventTicketException with the specified detail message and cause.
     *
     * @param message the detail message explaining the reason for the exception, or null if none
     * @param cause the cause of the exception, or null if the cause is unknown
     */
    public EventTicketException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an EventTicketException with the specified cause.
     *
     * @param cause the throwable that caused this exception; may be {@code null}
     */
    public EventTicketException(Throwable cause) {
        super(cause);
    }

    /**
     * Create an EventTicketException with a detail message, cause, and explicit control over
     * suppression and stack trace writability.
     *
     * @param message               the detail message (may be null)
     * @param cause                 the cause of this exception (may be null)
     * @param enableSuppression     whether suppression is enabled or disabled
     * @param writableStackTrace    whether the stack trace should be writable
     */
    public EventTicketException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
