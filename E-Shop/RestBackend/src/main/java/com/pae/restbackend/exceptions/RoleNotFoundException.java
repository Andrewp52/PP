package com.pae.restbackend.exceptions;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException() {
        super("Role not found!");
    }

    public RoleNotFoundException(String message) {
        super(message);
    }
}
