package com.github.brunomndantas.jscrapper.core.selector;

public class SelectorException extends Exception {

    public SelectorException(String message) {
        super(message);
    }

    public SelectorException(String message, Throwable cause) {
        super(message, cause);
    }

    public SelectorException(Throwable cause) {
        super(cause);
    }

}
