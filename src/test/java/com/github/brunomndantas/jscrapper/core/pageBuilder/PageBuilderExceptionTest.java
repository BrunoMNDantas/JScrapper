package com.github.brunomndantas.jscrapper.core.pageBuilder;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class PageBuilderExceptionTest {

    @Test
    public void testConstructors() {
        String msg = "";
        Exception cause = new Exception();
        PageBuilderException exception;

        exception = new PageBuilderException(msg);
        assertSame(msg, exception.getMessage());

        exception = new PageBuilderException(cause);
        assertSame(cause, exception.getCause());

        exception = new PageBuilderException(msg, cause);
        assertSame(msg, exception.getMessage());
        assertSame(cause, exception.getCause());
    }

}
