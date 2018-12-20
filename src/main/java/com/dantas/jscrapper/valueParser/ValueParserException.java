package com.dantas.jscrapper.valueParser;

public class ValueParserException extends Exception {

    public ValueParserException(String message) {
        super(message);
    }

    public ValueParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValueParserException(Throwable cause) {
        super(cause);
    }

}
