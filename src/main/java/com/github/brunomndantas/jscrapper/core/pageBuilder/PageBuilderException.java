package com.github.brunomndantas.jscrapper.core.pageBuilder;

public class PageBuilderException extends Exception {

    public PageBuilderException(String message) {
        super(message);
    }

    public PageBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageBuilderException(Throwable cause) {
        super(cause);
    }

}
