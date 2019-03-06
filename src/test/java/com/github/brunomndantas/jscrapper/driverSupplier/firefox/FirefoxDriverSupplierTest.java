package com.github.brunomndantas.jscrapper.driverSupplier.firefox;

import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class FirefoxDriverSupplierTest {

    public static final String DRIVER_RELATIVE_PATH = "firefox/geckodriver.exe";
    public static final String DRIVER_ABSOLUTE_PATH = ClassLoader.getSystemResource(DRIVER_RELATIVE_PATH).getPath();



    @Test
    public void getSilent() {
        FirefoxDriverSupplier supplier = new FirefoxDriverSupplier(null, !FirefoxDriverSupplier.DEFAULT_SILENT,false);

        assertTrue(supplier.getSilent() != FirefoxDriverSupplier.DEFAULT_SILENT);
    }

    @Test
    public void getDriverPath() {
        String path = "";
        FirefoxDriverSupplier supplier = new FirefoxDriverSupplier(path, false,false);

        assertSame(path, supplier.getDriverPath());
    }

    @Test
    public void getHeadless() {
        FirefoxDriverSupplier supplier = new FirefoxDriverSupplier(null, false, !FirefoxDriverSupplier.DEFAULT_HEADLESS);

        assertTrue(supplier.getHeadless() != FirefoxDriverSupplier.DEFAULT_HEADLESS);
    }

    @Test
    public void testConstructors() {
        FirefoxDriverSupplier supplier = new FirefoxDriverSupplier(DRIVER_ABSOLUTE_PATH);

        assertSame(DRIVER_ABSOLUTE_PATH, supplier.getDriverPath());
        assertEquals(FirefoxDriverSupplier.DEFAULT_HEADLESS, supplier.getHeadless());
        assertEquals(FirefoxDriverSupplier.DEFAULT_SILENT, supplier.getSilent());

        String path = "";
        supplier = new FirefoxDriverSupplier(path, !FirefoxDriverSupplier.DEFAULT_SILENT, !FirefoxDriverSupplier.DEFAULT_HEADLESS);
        assertSame(path, supplier.getDriverPath());
        assertNotEquals(FirefoxDriverSupplier.DEFAULT_HEADLESS, supplier.getHeadless());
        assertNotEquals(FirefoxDriverSupplier.DEFAULT_SILENT, supplier.getSilent());
    }

    @Test
    public void relativePathTest() throws Exception {
        FirefoxDriverSupplier supplier = new FirefoxDriverSupplier(DRIVER_RELATIVE_PATH);
        WebDriver driver = supplier.get();

        assertNotNull(driver);

        driver.quit();

        supplier = new FirefoxDriverSupplier("not_existent_path");
        try {
            supplier.get();
        } catch (Exception e) {
            assertTrue(e.getCause().getMessage().contains("Path not found"));
        }
    }

    @Test
    public void getDriverTest() throws DriverSupplierException {
        FirefoxDriverSupplier supplier = new FirefoxDriverSupplier(DRIVER_ABSOLUTE_PATH);
        WebDriver driver = supplier.get();

        assertNotNull(driver);

        driver.quit();

        supplier = new FirefoxDriverSupplier(DRIVER_ABSOLUTE_PATH, !FirefoxDriverSupplier.DEFAULT_SILENT, !FirefoxDriverSupplier.DEFAULT_HEADLESS);
        driver = supplier.get();

        assertNotNull(driver);

        driver.quit();
    }

}
