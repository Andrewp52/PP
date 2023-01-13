package com.pae.restbackend.exceptions;

public class UpdatableEntityIdIsNullException extends RuntimeException {
    public UpdatableEntityIdIsNullException() {
        super("Updatable entity id is mandatory!");
    }

    public UpdatableEntityIdIsNullException(String message) {
        super(message);
    }
}
