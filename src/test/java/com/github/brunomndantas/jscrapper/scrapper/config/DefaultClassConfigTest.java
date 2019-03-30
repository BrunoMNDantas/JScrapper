package com.github.brunomndantas.jscrapper.scrapper.config;

import com.github.brunomndantas.jscrapper.core.config.ClassConfig;
import com.github.brunomndantas.jscrapper.core.urlSupplier.IURLSupplier;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultClassConfigTest {

    private static class Person { }



    @Test
    public void getClassConfigTest() throws Exception {
        ClassConfig config = DefaultClassConfig.getClassConfig(Person.class);

        assertEquals(Person.class, config.getKlass());
        assertNotNull(config.getInstanceFactory());
        assertNotNull(config.getURLSupplier());
        assertNull(config.getDriverSupplier());
        assertNotNull(config.getDriverLoader());
    }

    @Test
    public void getInstanceFactoryTest() throws Exception {
        assertNotNull(DefaultClassConfig.getInstanceFactory(Person.class));
    }

    @Test
    public void getURLSupplierTest() throws Exception {
        IURLSupplier supplier = DefaultClassConfig.getURLSupplier(Person.class);
        assertNotNull(supplier);
        assertNull(supplier.get());
    }

    @Test
    public void getDriverSupplierTest() throws Exception {
        assertNull(DefaultClassConfig.getDriverSupplier(Person.class));
    }

    @Test
    public void getDriverLoaderTest() throws Exception {
        assertNotNull(DefaultClassConfig.getDriverLoader(Person.class));
    }

}
