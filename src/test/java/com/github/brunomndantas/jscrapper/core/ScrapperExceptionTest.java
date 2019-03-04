package com.github.brunomndantas.jscrapper.core;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class ScrapperExceptionTest {

    @Test
    public void testConstructors() {
        String msg = "";
        Exception cause = new Exception();
        ScrapperException exception;

        exception = new ScrapperException(msg);
        assertSame(msg, exception.getMessage());

        exception = new ScrapperException(cause);
        assertSame(cause, exception.getCause());

        exception = new ScrapperException(msg, cause);
        assertSame(msg, exception.getMessage());
        assertSame(cause, exception.getCause());
    }

}
