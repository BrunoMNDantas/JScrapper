package com.github.brunomndantas.jscrapper.core.urlSupplier;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class URLSupplierExceptionTest {

    @Test
    public void testConstructors() {
        String msg = "";
        Exception cause = new Exception();
        URLSupplierException exception;

        exception = new URLSupplierException(msg);
        assertSame(msg, exception.getMessage());

        exception = new URLSupplierException(cause);
        assertSame(cause, exception.getCause());

        exception = new URLSupplierException(msg, cause);
        assertSame(msg, exception.getMessage());
        assertSame(cause, exception.getCause());
    }

}
