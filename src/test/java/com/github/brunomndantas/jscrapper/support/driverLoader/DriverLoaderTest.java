package com.github.brunomndantas.jscrapper.support.driverLoader;

import com.github.brunomndantas.jscrapper.core.driverLoader.DriverLoaderException;
import com.github.brunomndantas.jscrapper.DummyDriver;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class DriverLoaderTest {

    @Test
    public void loadSuccessTest() throws DriverLoaderException {
        WebDriver driver = new DummyDriver();
        boolean[] passed = new boolean[1];

        DriverLoader loader = new DriverLoader() {
            @Override
            protected void loadDriver(WebDriver d) throws Exception {
                passed[0] = d == driver;
            }
        };

        loader.load(driver);
        assertTrue(passed[0]);
    }

    @Test
    public void loadWrapsException() {
        Exception exception = new Exception();
        DriverLoader loader = new DriverLoader() {
            @Override
            protected void loadDriver(WebDriver driver) throws Exception {
                throw exception;
            }
        };

        try {
            loader.load(new DummyDriver());
            fail("Exception should be thrown!");
        } catch(Exception e) {
            assertSame(exception, e.getCause());
        }
    }

}
