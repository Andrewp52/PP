package com.pae.restbackend.exceptions;

public class NewEntityIdIsNotNullException extends RuntimeException {
    public NewEntityIdIsNotNullException() {
        super("New entity id must be null!");
    }

    public NewEntityIdIsNotNullException(String message) {
        super(message);
    }
}
