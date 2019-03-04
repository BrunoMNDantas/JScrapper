package com.github.brunomndantas.jscrapper.core.driverSupplier;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class DriverSupplierExceptionTest {

    @Test
    public void testConstructors() {
        String msg = "";
        Exception cause = new Exception();
        DriverSupplierException exception;

        exception = new DriverSupplierException(msg);
        assertSame(msg, exception.getMessage());

        exception = new DriverSupplierException(cause);
        assertSame(cause, exception.getCause());

        exception = new DriverSupplierException(msg, cause);
        assertSame(msg, exception.getMessage());
        assertSame(cause, exception.getCause());
    }

}
