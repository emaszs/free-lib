package com.psilonsoft.services.exceptions;

public class NoItemsLeftException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1731691247826007112L;

    public NoItemsLeftException(final String message) {
        super(message);
    }

    public NoItemsLeftException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}
