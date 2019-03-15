package com.github.brunomndantas.jscrapper.driverSupplier;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertSame;

public class DriverSupplierTest {

    @Test
    public void wrapExceptionTest() {
        Exception exception = new Exception();
        DriverSupplier driverSupplier = new DriverSupplier() {
            @Override
            protected WebDriver getDriver() throws Exception {
                throw exception;
            }
        };

        try {
            driverSupplier.get();
        } catch (DriverSupplierException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void getTest() throws DriverSupplierException {
        WebDriver driver = new DummyDriver();

        DriverSupplier driverSupplier = new DriverSupplier() {
            @Override
            protected WebDriver getDriver() throws Exception {
                return driver;
            }
        };

        assertSame(driver, driverSupplier.get());
    }

}
