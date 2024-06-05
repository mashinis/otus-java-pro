package ru.mashinis.db.interaction.exceptions;

public class ApplicationInitializationException extends RuntimeException {
    public ApplicationInitializationException() {
        super();
    }

    public ApplicationInitializationException(String message) {
        super(message);
    }

    public ApplicationInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationInitializationException(Throwable cause) {
        super(cause);
    }
}

