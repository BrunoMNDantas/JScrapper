package com.github.brunomndantas.jscrapper.core.property;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class PropertyExceptionTest {

    @Test
    public void testConstructors() {
        String msg = "";
        Exception cause = new Exception();
        PropertyException exception;

        exception = new PropertyException(msg);
        assertSame(msg, exception.getMessage());

        exception = new PropertyException(cause);
        assertSame(cause, exception.getCause());

        exception = new PropertyException(msg, cause);
        assertSame(msg, exception.getMessage());
        assertSame(cause, exception.getCause());
    }

}
