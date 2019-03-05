package com.github.brunomndantas.jscrapper.driverSupplier;

import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.driverSupplier.annotation.DriverSupplier;
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

}
