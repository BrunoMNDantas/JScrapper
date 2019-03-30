package com.github.brunomndantas.jscrapper.support.driverSupplier;

import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class ChromeDriverSupplierTest {

    public static final String DRIVER_RELATIVE_PATH = "chrome/chromedriver.exe";
    public static final String DRIVER_ABSOLUTE_PATH = ClassLoader.getSystemResource(DRIVER_RELATIVE_PATH).getPath();



    @Test
    public void getSilent() {
        ChromeDriverSupplier supplier = new ChromeDriverSupplier(null, !ChromeDriverSupplier.DEFAULT_SILENT,false);

        assertTrue(supplier.getSilent() != ChromeDriverSupplier.DEFAULT_SILENT);
    }

    @Test
    public void getDriverPath() {
        String path = "";
        ChromeDriverSupplier supplier = new ChromeDriverSupplier(path, false,false);

        assertSame(path, supplier.getDriverPath());
    }

    @Test
    public void getHeadless() {
        ChromeDriverSupplier supplier = new ChromeDriverSupplier(null, false, !ChromeDriverSupplier.DEFAULT_HEADLESS);

        assertTrue(supplier.getHeadless() != ChromeDriverSupplier.DEFAULT_HEADLESS);
    }

    @Test
    public void testConstructors() {
        ChromeDriverSupplier supplier = new ChromeDriverSupplier(DRIVER_ABSOLUTE_PATH);

        assertSame(DRIVER_ABSOLUTE_PATH, supplier.getDriverPath());
        assertEquals(ChromeDriverSupplier.DEFAULT_HEADLESS, supplier.getHeadless());
        assertEquals(ChromeDriverSupplier.DEFAULT_SILENT, supplier.getSilent());

        String path = "";
        supplier = new ChromeDriverSupplier(path, !ChromeDriverSupplier.DEFAULT_SILENT, !ChromeDriverSupplier.DEFAULT_HEADLESS);
        assertSame(path, supplier.getDriverPath());
        assertNotEquals(ChromeDriverSupplier.DEFAULT_HEADLESS, supplier.getHeadless());
        assertNotEquals(ChromeDriverSupplier.DEFAULT_SILENT, supplier.getSilent());
    }

    @Test
    public void relativePathTest() throws Exception {
        ChromeDriverSupplier supplier = new ChromeDriverSupplier(DRIVER_RELATIVE_PATH);
        WebDriver driver = supplier.get();

        assertNotNull(driver);

        driver.quit();

        supplier = new ChromeDriverSupplier("not_existent_path");
        try {
            supplier.get();
            fail("Exception should be thrown!");
        } catch (Exception e) {
            assertTrue(e.getCause().getMessage().contains("Path not found"));
        }
    }

    @Test
    public void getDriverTest() throws DriverSupplierException {
        ChromeDriverSupplier supplier = new ChromeDriverSupplier(DRIVER_ABSOLUTE_PATH);
        WebDriver driver = supplier.get();

        assertNotNull(driver);

        driver.quit();

        supplier = new ChromeDriverSupplier(DRIVER_ABSOLUTE_PATH, !ChromeDriverSupplier.DEFAULT_SILENT, !ChromeDriverSupplier.DEFAULT_HEADLESS);
        driver = supplier.get();

        assertNotNull(driver);

        driver.quit();
    }

}
