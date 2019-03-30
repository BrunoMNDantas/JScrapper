package com.github.brunomndantas.jscrapper.support.driverSupplier;

import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class PhantomDriverSupplierTest {

    public static final String DRIVER_RELATIVE_PATH = "phantomjs/phantomjs.exe";
    public static final String DRIVER_ABSOLUTE_PATH = ClassLoader.getSystemResource(DRIVER_RELATIVE_PATH).getPath();



    @Test
    public void getSilent() {
        PhantomDriverSupplier supplier = new PhantomDriverSupplier(null, !PhantomDriverSupplier.DEFAULT_SILENT);

        assertTrue(supplier.getSilent() != PhantomDriverSupplier.DEFAULT_SILENT);
    }

    @Test
    public void getDriverPath() {
        String path = "";
        PhantomDriverSupplier supplier = new PhantomDriverSupplier(path, false);

        assertSame(path, supplier.getDriverPath());
    }

    @Test
    public void testConstructors() {
        PhantomDriverSupplier supplier = new PhantomDriverSupplier(DRIVER_ABSOLUTE_PATH);

        assertSame(DRIVER_ABSOLUTE_PATH, supplier.getDriverPath());
        assertEquals(PhantomDriverSupplier.DEFAULT_SILENT, supplier.getSilent());

        String path = "";
        supplier = new PhantomDriverSupplier(path, !PhantomDriverSupplier.DEFAULT_SILENT);
        assertSame(path, supplier.getDriverPath());
        assertNotEquals(PhantomDriverSupplier.DEFAULT_SILENT, supplier.getSilent());
    }

    @Test
    public void relativePathTest() throws Exception {
        PhantomDriverSupplier supplier = new PhantomDriverSupplier(DRIVER_RELATIVE_PATH);
        WebDriver driver = supplier.get();

        assertNotNull(driver);

        driver.quit();

        supplier = new PhantomDriverSupplier("not_existent_path");
        try {
            supplier.get();
            fail("Exception should be thrown!");
        } catch (Exception e) {
            assertTrue(e.getCause().getMessage().contains("Path not found"));
        }
    }

    @Test
    public void getDriverTest() throws DriverSupplierException {
        PhantomDriverSupplier supplier = new PhantomDriverSupplier(DRIVER_ABSOLUTE_PATH);
        WebDriver driver = supplier.get();

        assertNotNull(driver);

        driver.quit();

        supplier = new PhantomDriverSupplier(DRIVER_ABSOLUTE_PATH, !PhantomDriverSupplier.DEFAULT_SILENT);
        driver = supplier.get();

        assertNotNull(driver);

        driver.quit();
    }

}
