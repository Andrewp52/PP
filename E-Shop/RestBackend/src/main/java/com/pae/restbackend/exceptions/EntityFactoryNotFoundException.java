package com.pae.restbackend.exceptions;

public class EntityFactoryNotFoundException extends Exception{
    public EntityFactoryNotFoundException() {
        super("Entity factory not found! Inject factory in service before adding entity from dto.");
    }
}
