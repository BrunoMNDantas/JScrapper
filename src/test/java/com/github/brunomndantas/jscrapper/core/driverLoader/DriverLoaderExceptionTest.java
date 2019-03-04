package com.github.brunomndantas.jscrapper.core.driverLoader;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class DriverLoaderExceptionTest {

    @Test
    public void testConstructors() {
        String msg = "";
        Exception cause = new Exception();
        DriverLoaderException exception;

        exception = new DriverLoaderException(msg);
        assertSame(msg, exception.getMessage());

        exception = new DriverLoaderException(cause);
        assertSame(cause, exception.getCause());

        exception = new DriverLoaderException(msg, cause);
        assertSame(msg, exception.getMessage());
        assertSame(cause, exception.getCause());
    }

}
