package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.core.config.ClassConfig;
import com.github.brunomndantas.jscrapper.core.driverLoader.DriverLoaderException;
import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;
import com.github.brunomndantas.jscrapper.core.urlSupplier.URLSupplierException;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class ClassScrapperTest {
    
    private static ClassConfig createDummyClassConfig() {
        ClassConfig config = new ClassConfig(Object.class);
        config.setInstanceFactory(() -> null);
        config.setURLSupplier(() -> null);
        config.setDriverSupplier(() -> null);
        config.setDriverLoader((d) -> {});

        return config;
    }



    @Test
    public void getConfigTest() throws ScrapperException {
        ClassConfig config = createDummyClassConfig();
        ClassScrapper scrapper = new ClassScrapper(config);

        assertSame(config, scrapper.getConfig());
    }

    @Test
    public void nullInstanceFactoryTest() {
        ClassConfig config = createDummyClassConfig();
        config.setInstanceFactory(null);

        try {
            new ClassScrapper(config);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("No InstanceFactory found"));
            assertTrue(e.getMessage().contains(config.getKlass().getName()));
        }
    }

    @Test
    public void nullURLSupplierTest() {
        ClassConfig config = createDummyClassConfig();
        config.setURLSupplier(null);

        try {
            new ClassScrapper(config);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("No URLSupplier found"));
            assertTrue(e.getMessage().contains(config.getKlass().getName()));
        }
    }

    @Test
    public void nullDriverSupplierTest() {
        ClassConfig config = createDummyClassConfig();
        config.setDriverSupplier(null);

        try {
            new ClassScrapper(config);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("No DriverSupplier found"));
            assertTrue(e.getMessage().contains(config.getKlass().getName()));
        }
    }

    @Test
    public void nullDriverLoaderTest() {
        ClassConfig config = createDummyClassConfig();
        config.setDriverLoader(null);

        try {
            new ClassScrapper(config);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("No DriverLoader found"));
            assertTrue(e.getMessage().contains(config.getKlass().getName()));
        }
    }

    @Test
    public void constructorTest() throws ScrapperException {
        ClassConfig config = createDummyClassConfig();
        ClassScrapper scrapper = new ClassScrapper(config);

        assertSame(config, scrapper.getConfig());
    }

    @Test
    public void createInstanceTest() throws Exception {
        ClassConfig config = createDummyClassConfig();
        Object instance = new Object();
        config.setInstanceFactory(() -> instance);

        assertSame(instance, new ClassScrapper(config).createInstance());
    }

    @Test
    public void wrapsInstanceFactoryExceptionTest() {
        InstanceFactoryException exception = new InstanceFactoryException("");
        ClassConfig config = createDummyClassConfig();
        config.setInstanceFactory(() -> { throw exception; });

        try {
            new ClassScrapper(config).createInstance();
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertSame(e.getCause(), exception);
        }
    }

    @Test
    public void getURLTest() throws Exception {
        ClassConfig config = createDummyClassConfig();
        String url = "";
        config.setURLSupplier(() -> url);

        assertSame(url, new ClassScrapper(config).getURL());
    }

    @Test
    public void wrapsURLSupplierExceptionTest() {
        ClassConfig config = createDummyClassConfig();
        URLSupplierException exception = new URLSupplierException("");
        config.setURLSupplier(() -> { throw exception; });

        try {
            new ClassScrapper(config).getURL();
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertSame(e.getCause(), exception);
        }
    }

    @Test
    public void getDriverTest() throws Exception {
        ClassConfig config = createDummyClassConfig();
        WebDriver driver = new DummyDriver();
        config.setDriverSupplier(() -> driver);

        assertSame(driver, new ClassScrapper(config).getDriver());
    }

    @Test
    public void wrapsDriverSupplierExceptionTest() {
        DriverSupplierException exception = new DriverSupplierException("");
        ClassConfig config = createDummyClassConfig();
        config.setDriverSupplier(() -> { throw exception; });

        try {
            new ClassScrapper(config).getDriver();
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertSame(e.getCause(), exception);
        }

    }

    @Test
    public void loadDriverTest() throws Exception {
        ClassConfig config = createDummyClassConfig();
        WebDriver driver = new DummyDriver();

        boolean[] passed = new boolean[1];
        config.setDriverLoader((d) -> passed[0] = d==driver);

        new ClassScrapper(config).loadDriver(driver);
        assertTrue(passed[0]);
    }

    @Test
    public void wrapsDriverLoaderExceptionTest() {
        DriverLoaderException exception = new DriverLoaderException("");
        ClassConfig config = createDummyClassConfig();
        config.setDriverLoader((d) -> { throw exception; });

        try {
            new ClassScrapper(config).loadDriver(null);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertSame(e.getCause(), exception);
        }
    }

}
