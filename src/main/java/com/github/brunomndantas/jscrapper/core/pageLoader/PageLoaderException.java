package com.github.brunomndantas.jscrapper.core.pageLoader;

public class PageLoaderException extends Exception {

    public PageLoaderException(String message) {
        super(message);
    }

    public PageLoaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageLoaderException(Throwable cause) {
        super(cause);
    }

}
