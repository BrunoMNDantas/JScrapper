package com.github.brunomndantas.jscrapper.driverSupplier;

import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.assertSame;

public class DriverSupplierTest {

    private static final String DRIVER_PATH = "phantomjs/phantomjs.exe";



    private static WebDriver getDriver() {
        String driverPath = ClassLoader.getSystemResource(DRIVER_PATH).getPath();

        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();

        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, driverPath);

        return new PhantomJSDriver(capabilities);
    }



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
        Collection<WebElement> elements = new LinkedList<>();
        WebDriver driver = getDriver();

        DriverSupplier driverSupplier = new DriverSupplier() {
            @Override
            protected WebDriver getDriver() throws Exception {
                return driver;
            }
        };

        assertSame(driver, driverSupplier.get());
    }

}
