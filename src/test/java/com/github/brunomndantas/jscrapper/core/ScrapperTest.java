package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.core.config.ClassConfig;
import com.github.brunomndantas.jscrapper.core.config.FieldConfig;
import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;
import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.core.parser.IParser;
import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.selector.ISelector;
import com.github.brunomndantas.jscrapper.core.urlSupplier.IURLSupplier;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class ScrapperTest {

    public static class Person {
        public String name;
    }



    @Test
    public void nonExistentInstanceFactoryTest() {
        Class klass = Person.class;
        ClassConfig config = new ClassConfig(klass);

        try {
            new Scrapper().scrap(config);
            fail("Exception should be thrown!");
        } catch(ScrapperException e) {
            assertTrue(e.getMessage().contains("No InstanceFactory found"));
            assertTrue(e.getMessage().contains(klass.getName()));
        }
    }

    @Test
    public void nonExistentURLSupplierTest() {
        Class klass = Person.class;
        ClassConfig config = new ClassConfig(klass);
        config.setInstanceFactory(() -> null);

        try {
            new Scrapper().scrap(config);
            fail("Exception should be thrown!");
        } catch(ScrapperException e) {
            assertTrue(e.getMessage().contains("No URLSupplier found"));
            assertTrue(e.getMessage().contains(klass.getName()));
        }
    }

    @Test
    public void nonExistentDriverSupplierTest() {
        Class klass = Person.class;
        ClassConfig config = new ClassConfig(klass);
        config.setInstanceFactory(() -> null);
        config.setURLSupplier(() -> "");

        try {
            new Scrapper().scrap(config);
            fail("Exception should be thrown!");
        } catch(ScrapperException e) {
            assertTrue(e.getMessage().contains("No DriverSupplier found"));
            assertTrue(e.getMessage().contains(klass.getName()));
        }
    }

    @Test
    public void nonExistentClassDriverLoaderTest() {
        Class klass = Person.class;
        ClassConfig config = new ClassConfig(klass);
        config.setInstanceFactory(() -> null);
        config.setURLSupplier(() -> "");
        config.setDriverSupplier(DummyDriver::new);

        try {
            new Scrapper().scrap(config);
            fail("Exception should be thrown!");
        } catch(ScrapperException e) {
            assertTrue(e.getMessage().contains("No DriverLoader found"));
            assertTrue(e.getMessage().contains(klass.getName()));
        }
    }

    @Test
    public void nonExistentFieldDriverLoaderTest() throws NoSuchFieldException {
        Class klass = Person.class;
        Field field = Person.class.getDeclaredField("name");
        ClassConfig config = new ClassConfig(klass);
        config.setInstanceFactory(Person::new);
        config.setURLSupplier(() -> "");
        config.setDriverSupplier(DummyDriver::new);
        config.setDriverLoader((d) -> {});

        FieldConfig fieldConfig = new FieldConfig(field);
        config.getFieldsConfig().add(fieldConfig);

        try {
            new Scrapper().scrap(config);
            fail("Exception should be thrown!");
        } catch(ScrapperException e) {
            assertTrue(e.getMessage().contains("No DriverLoader found"));
            assertTrue(e.getMessage().contains(klass.getName()));
            assertTrue(e.getMessage().contains(field.getName()));
        }
    }

    @Test
    public void nonExistentSelectorTest() throws NoSuchFieldException {
        Class klass = Person.class;
        Field field = Person.class.getDeclaredField("name");
        ClassConfig config = new ClassConfig(klass);
        config.setInstanceFactory(Person::new);
        config.setURLSupplier(() -> "");
        config.setDriverSupplier(DummyDriver::new);
        config.setDriverLoader((d) -> {});

        FieldConfig fieldConfig = new FieldConfig(field);
        fieldConfig.setDriverLoader((d) -> {});
        config.getFieldsConfig().add(fieldConfig);

        try {
            new Scrapper().scrap(config);
            fail("Exception should be thrown!");
        } catch(ScrapperException e) {
            assertTrue(e.getMessage().contains("No Selector found"));
            assertTrue(e.getMessage().contains(klass.getName()));
            assertTrue(e.getMessage().contains(field.getName()));
        }
    }

    @Test
    public void nonExistentElementLoaderTest() throws NoSuchFieldException {
        Class klass = Person.class;
        Field field = Person.class.getDeclaredField("name");
        ClassConfig config = new ClassConfig(klass);
        config.setInstanceFactory(Person::new);
        config.setURLSupplier(() -> "");
        config.setDriverSupplier(DummyDriver::new);
        config.setDriverLoader((d) -> {});

        FieldConfig fieldConfig = new FieldConfig(field);
        fieldConfig.setDriverLoader((d) -> {});
        fieldConfig.setSelector((d) -> null);
        config.getFieldsConfig().add(fieldConfig);

        try {
            new Scrapper().scrap(config);
            fail("Exception should be thrown!");
        } catch(ScrapperException e) {
            assertTrue(e.getMessage().contains("No ElementLoader found"));
            assertTrue(e.getMessage().contains(klass.getName()));
            assertTrue(e.getMessage().contains(field.getName()));
        }
    }

    @Test
    public void nonExistentParserTest() throws NoSuchFieldException {
        Class klass = Person.class;
        Field field = Person.class.getDeclaredField("name");
        ClassConfig config = new ClassConfig(klass);
        config.setInstanceFactory(Person::new);
        config.setURLSupplier(() -> "");
        config.setDriverSupplier(DummyDriver::new);
        config.setDriverLoader((d) -> {});

        FieldConfig fieldConfig = new FieldConfig(field);
        fieldConfig.setDriverLoader((d) -> {});
        fieldConfig.setSelector((d) -> null);
        fieldConfig.setElementLoader((driver, elements) -> {});
        config.getFieldsConfig().add(fieldConfig);

        try {
            new Scrapper().scrap(config);
            fail("Exception should be thrown!");
        } catch(ScrapperException e) {
            assertTrue(e.getMessage().contains("No Parser found"));
            assertTrue(e.getMessage().contains(klass.getName()));
            assertTrue(e.getMessage().contains(field.getName()));
        }
    }

    @Test
    public void nonExistentPropertyTest() throws NoSuchFieldException {
        Class klass = Person.class;
        Field field = Person.class.getDeclaredField("name");
        ClassConfig config = new ClassConfig(klass);
        config.setInstanceFactory(Person::new);
        config.setURLSupplier(() -> "");
        config.setDriverSupplier(DummyDriver::new);
        config.setDriverLoader((d) -> {});

        FieldConfig fieldConfig = new FieldConfig(field);
        fieldConfig.setDriverLoader((d) -> {});
        fieldConfig.setSelector((d) -> null);
        fieldConfig.setElementLoader((driver, elements) -> {});
        fieldConfig.setParser((driver, elements) -> null);
        config.getFieldsConfig().add(fieldConfig);

        try {
            new Scrapper().scrap(config);
            fail("Exception should be thrown!");
        } catch(ScrapperException e) {
            assertTrue(e.getMessage().contains("No Property found"));
            assertTrue(e.getMessage().contains(klass.getName()));
            assertTrue(e.getMessage().contains(field.getName()));
        }
    }

    @Test
    public void ignoreNullURLTest() throws Exception {
        boolean[] urlIgnored = { true };

        ClassConfig config = new ClassConfig(Person.class);
        config.setInstanceFactory(() -> null);
        config.setURLSupplier(() -> null);
        config.setDriverSupplier(() -> new DummyDriver() {
            @Override
            public void get(String url) {
                urlIgnored[0] = false;
                super.get(url);
            }
        });
        config.setDriverLoader(driver -> {});

        FieldConfig fieldConfig = new FieldConfig(Person.class.getDeclaredField("name"));
        fieldConfig.setDriverLoader(driver -> {});
        fieldConfig.setSelector(driver -> null);
        fieldConfig.setElementLoader((driver, elements) -> {});
        fieldConfig.setParser((driver, elements) -> null);
        fieldConfig.setProperty(new IProperty() {
            @Override public Object get(Object instance) { return null; }
            @Override public void set(Object i, Object v) { }
        });
        config.getFieldsConfig().add(fieldConfig);

        Scrapper scrapper = new Scrapper();
        scrapper.scrap(config);

        assertTrue(urlIgnored[0]);
    }

    @Test
    public void scrapTest() throws Exception {
        boolean[] urlSupplierPassed = new boolean[1];
        boolean[] driverSupplierPassed = new boolean[1];
        boolean[] classDriverLoaderPassed = new boolean[1];
        boolean[] fieldDriverLoaderPassed = new boolean[1];
        boolean[] selectorPassed = new boolean[1];
        boolean[] elementElementLoaderPassed = new boolean[1];
        boolean[] parserPassed = new boolean[1];
        boolean[] propertyPassed = new boolean[1];

        Class klass = Person.class;
        Field field = klass.getDeclaredField("name");
        Object result = new Object();
        String url = "";
        WebDriver driver = new DummyDriver(){
            @Override
            public void get(String u) {
                urlSupplierPassed[0] = url == u;
                super.get(url);
            }
        };
        Collection<WebElement> elements = new LinkedList<>();
        Object value = new Person();

        IInstanceFactory instanceFactory = () -> result;
        IURLSupplier urlSupplier = () -> url;
        IDriverSupplier driverSupplier = () -> { driverSupplierPassed[0] = true; return driver; };
        IDriverLoader classDriverLoader = (d) -> { classDriverLoaderPassed[0] = d == driver; };
        IDriverLoader fieldDriverLoader = (d) -> { fieldDriverLoaderPassed[0] = d == driver; };
        ISelector selector = (d) -> { selectorPassed[0] = d == driver; return elements; };
        IElementLoader elementLoader = (d, e) -> { elementElementLoaderPassed[0] = d == driver && e == elements; };
        IParser parser = (d, e) -> { parserPassed[0] = d == driver && e == elements; return value; };
        IProperty property = new IProperty() {
            @Override public Object get(Object instance) { return null; }
            @Override public void set(Object i, Object v) { propertyPassed[0] = i == result && v == value; }
        };

        ClassConfig config = new ClassConfig(klass);
        config.setInstanceFactory(instanceFactory);
        config.setURLSupplier(urlSupplier);
        config.setDriverSupplier(driverSupplier);
        config.setDriverLoader(classDriverLoader);

        FieldConfig fieldConfig = new FieldConfig(field);
        fieldConfig.setDriverLoader(fieldDriverLoader);
        fieldConfig.setSelector(selector);
        fieldConfig.setElementLoader(elementLoader);
        fieldConfig.setParser(parser);
        fieldConfig.setProperty(property);
        config.getFieldsConfig().add(fieldConfig);

        Scrapper scrapper = new Scrapper();

        assertSame(result, scrapper.scrap(config));

        assertTrue(urlSupplierPassed[0]);
        assertTrue(driverSupplierPassed[0]);
        assertTrue(classDriverLoaderPassed[0]);
        assertTrue(fieldDriverLoaderPassed[0]);
        assertTrue(selectorPassed[0]);
        assertTrue(elementElementLoaderPassed[0]);
        assertTrue(parserPassed[0]);
        assertTrue(propertyPassed[0]);
    }

}
