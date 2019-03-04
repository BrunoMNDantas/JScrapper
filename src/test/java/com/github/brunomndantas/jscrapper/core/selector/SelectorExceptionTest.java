package com.github.brunomndantas.jscrapper.core.selector;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class SelectorExceptionTest {

    @Test
    public void testConstructors() {
        String msg = "";
        Exception cause = new Exception();
        SelectorException exception;

        exception = new SelectorException(msg);
        assertSame(msg, exception.getMessage());

        exception = new SelectorException(cause);
        assertSame(cause, exception.getCause());

        exception = new SelectorException(msg, cause);
        assertSame(msg, exception.getMessage());
        assertSame(cause, exception.getCause());
    }

}
