package com.github.brunomndantas.jscrapper.core.elementLoader;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class ElementLoaderExceptionTest {

    @Test
    public void testConstructors() {
        String msg = "";
        Exception cause = new Exception();
        ElementLoaderException exception;

        exception = new ElementLoaderException(msg);
        assertSame(msg, exception.getMessage());

        exception = new ElementLoaderException(cause);
        assertSame(cause, exception.getCause());

        exception = new ElementLoaderException(msg, cause);
        assertSame(msg, exception.getMessage());
        assertSame(cause, exception.getCause());
    }

}
