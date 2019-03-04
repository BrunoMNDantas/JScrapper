package com.github.brunomndantas.jscrapper.core.instanceFactory;

public class InstanceFactoryException extends Exception {

    public InstanceFactoryException(String message) {
        super(message);
    }

    public InstanceFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public InstanceFactoryException(Throwable cause) {
        super(cause);
    }

}
