package com.github.brunomndantas.jscrapper.core.pageLoader;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class PageLoaderExceptionTest {

    @Test
    public void testConstructors() {
        String msg = "";
        Exception cause = new Exception();
        PageLoaderException exception;

        exception = new PageLoaderException(msg);
        assertSame(msg, exception.getMessage());

        exception = new PageLoaderException(cause);
        assertSame(cause, exception.getCause());

        exception = new PageLoaderException(msg, cause);
        assertSame(msg, exception.getMessage());
        assertSame(cause, exception.getCause());
    }

}
