package com.github.brunomndantas.jscrapper.driverSupplier;

import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.driverSupplier.annotation.DriverSupplier;
import com.github.brunomndantas.jscrapper.driverSupplier.chrome.ChromeDriver;
import com.github.brunomndantas.jscrapper.driverSupplier.chrome.ChromeDriverSupplier;
import com.github.brunomndantas.jscrapper.driverSupplier.firefox.FirefoxDriver;
import com.github.brunomndantas.jscrapper.driverSupplier.firefox.FirefoxDriverSupplier;
import com.github.brunomndantas.jscrapper.driverSupplier.phantom.PhantomDriver;
import com.github.brunomndantas.jscrapper.driverSupplier.phantom.PhantomDriverSupplier;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class DriverSupplierFactoryTest {

    public static class SupplierWithoutEmptyConstructor implements IDriverSupplier {

        public SupplierWithoutEmptyConstructor(String str) { }



        @Override
        public WebDriver get() throws DriverSupplierException {
            return null;
        }

    }

    public static class SupplierWithEmptyConstructor implements IDriverSupplier {

        public SupplierWithEmptyConstructor() { }



        @Override
        public WebDriver get() throws DriverSupplierException {
            return null;
        }

    }


    @DriverSupplier(SupplierWithEmptyConstructor.class)
    public static class EntityWithDriverSupplierAnnotation { }

    public static class EntityWithoutDriverSupplierAnnotation { }

    @DriverSupplier(SupplierWithoutEmptyConstructor.class)
    public static class EntityWithNoEmptyConstructorDriverSupplierAnnotation { }

    @ChromeDriver(path = "chrome/chromedriver.exe")
    public static class EntityWithChromeAnnotation { }

    @FirefoxDriver(path = "firefox/geckodriver.exe")
    public static class EntityWithFirefoxAnnotation { }

    @PhantomDriver(path = "phantomjs/phantomjs.exe")
    public static class EntityWithPhantomAnnotation { }



    @Test
    public void createEntityWithDriverSupplierAnnotationTest() throws ScrapperException {
        Class<?> klass = EntityWithDriverSupplierAnnotation.class;
        IDriverSupplier supplier = DriverSupplierFactory.create(klass);

        assertNotNull(supplier);
        assertTrue(supplier instanceof SupplierWithEmptyConstructor);
    }

    @Test
    public void createEntityWithoutDriverSupplierAnnotationTest() {
        Class<?> klass = EntityWithoutDriverSupplierAnnotation.class;

        try {
            DriverSupplierFactory.create(klass);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("found"));
        }
    }

    @Test
    public void createEntityWithNoEmptyConstructorDriverSupplierAnnotationTest() {
        Class<?> klass = EntityWithNoEmptyConstructorDriverSupplierAnnotation.class;

        try {
            DriverSupplierFactory.create(klass);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("empty constructor"));
        }
    }

    @Test
    public void createEntityWithChromeAnnotationTest() throws Exception {
        Class<?> klass = EntityWithChromeAnnotation.class;
        IDriverSupplier supplier = DriverSupplierFactory.create(klass);

        assertNotNull(supplier);
        assertTrue(supplier instanceof ChromeDriverSupplier);
    }

    @Test
    public void createEntityWithFirefoxAnnotationTest() throws Exception {
        Class<?> klass = EntityWithFirefoxAnnotation.class;
        IDriverSupplier supplier = DriverSupplierFactory.create(klass);

        assertNotNull(supplier);
        assertTrue(supplier instanceof FirefoxDriverSupplier);
    }

    @Test
    public void createEntityWithPhantomAnnotationTest() throws Exception {
        Class<?> klass = EntityWithPhantomAnnotation.class;
        IDriverSupplier supplier = DriverSupplierFactory.create(klass);

        assertNotNull(supplier);
        assertTrue(supplier instanceof PhantomDriverSupplier);
    }

}
