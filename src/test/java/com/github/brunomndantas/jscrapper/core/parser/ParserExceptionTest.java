package com.github.brunomndantas.jscrapper.core.parser;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class ParserExceptionTest {

    @Test
    public void testConstructors() {
        String msg = "";
        Exception cause = new Exception();
        ParserException exception;

        exception = new ParserException(msg);
        assertSame(msg, exception.getMessage());

        exception = new ParserException(cause);
        assertSame(cause, exception.getCause());

        exception = new ParserException(msg, cause);
        assertSame(msg, exception.getMessage());
        assertSame(cause, exception.getCause());
    }

}
