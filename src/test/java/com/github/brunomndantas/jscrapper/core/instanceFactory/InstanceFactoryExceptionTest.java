package com.github.brunomndantas.jscrapper.core.instanceFactory;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class InstanceFactoryExceptionTest {

    @Test
    public void testConstructors() {
        String msg = "";
        Exception cause = new Exception();
        InstanceFactoryException exception;

        exception = new InstanceFactoryException(msg);
        assertSame(msg, exception.getMessage());

        exception = new InstanceFactoryException(cause);
        assertSame(cause, exception.getCause());

        exception = new InstanceFactoryException(msg, cause);
        assertSame(msg, exception.getMessage());
        assertSame(cause, exception.getCause());
    }

}
