package com.dantas.jscrapper;

public class ScrapperException extends Exception {

    public ScrapperException(String message) {
        super(message);
    }

    public ScrapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScrapperException(Throwable cause) {
        super(cause);
    }

}
