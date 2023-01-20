package com.pae.restbackend.exceptions;

public class PhoneNumberEmptyException extends RuntimeException {
    public PhoneNumberEmptyException() {
        super("Phone number is empty!");
    }

    public PhoneNumberEmptyException(String message) {
        super(message);
    }
}
