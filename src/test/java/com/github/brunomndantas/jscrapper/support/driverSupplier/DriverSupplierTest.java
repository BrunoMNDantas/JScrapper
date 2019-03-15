package com.github.brunomndantas.jscrapper.support.driverSupplier;

import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import com.github.brunomndantas.jscrapper.DummyDriver;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

public class DriverSupplierTest {

    @Test
    public void getSuccessTest() throws DriverSupplierException {
        WebDriver driver = new DummyDriver();
        DriverSupplier supplier = new DriverSupplier() {
            @Override
            protected WebDriver getDriver() throws Exception {
                return driver;
            }
        };

        assertSame(driver, supplier.get());
    }

    @Test
    public void getWrapsException() {
        Exception exception = new Exception();
        DriverSupplier supplier = new DriverSupplier() {
            @Override
            protected WebDriver getDriver() throws Exception {
                throw exception;
            }
        };

        try {
            supplier.get();
            fail("Exception should be thrown!");
        } catch(Exception e) {
            assertSame(exception, e.getCause());
        }
    }

}
