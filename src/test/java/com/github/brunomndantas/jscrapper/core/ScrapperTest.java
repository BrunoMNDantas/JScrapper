package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.config.ClassConfig;
import com.github.brunomndantas.jscrapper.core.config.FieldConfig;
import com.github.brunomndantas.jscrapper.core.config.ScrapperConfig;
import com.github.brunomndantas.jscrapper.core.driverLoader.DriverLoaderException;
import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.elementLoader.ElementLoaderException;
import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;
import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;
import com.github.brunomndantas.jscrapper.core.parser.IParser;
import com.github.brunomndantas.jscrapper.core.parser.ParserException;
import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.property.PropertyException;
import com.github.brunomndantas.jscrapper.core.selector.ISelector;
import com.github.brunomndantas.jscrapper.core.selector.SelectorException;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class ScrapperTest {

    public static class Person {
        public String name;
    }



    private static final String DRIVER_PATH = "phantomjs/phantomjs.exe";



    private static WebDriver getDriver() {
        String driverPath = ClassLoader.getSystemResource(DRIVER_PATH).getPath();

        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();

        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, driverPath);

        return new PhantomJSDriver(capabilities);
    }



    @Test
    public void nonExistentClassConfigTest() {
        Class klass = Person.class;
        ScrapperConfig config = new ScrapperConfig();

        try {
            new Scrapper().scrap(config, klass);
            fail("Exception should be thrown!");
        } catch(ScrapperException e) {
            assertTrue(e.getMessage().contains("No config found for class"));
            assertTrue(e.getMessage().contains(klass.getName()));
        }
    }

    @Test
    public void nonExistentFieldConfigTest() {
        Class klass = Person.class;
        ScrapperConfig config = new ScrapperConfig();
        config.registerInstanceFactory(klass, () -> null);
        config.registerDriverSupplier(klass, () -> null);
        config.registerDriverLoader(klass, (d) -> {});

        try {
            new Scrapper().scrap(config, klass);
            fail("Exception should be thrown!");
        } catch(ScrapperException e) {
            assertTrue(e.getMessage().contains("No config found for field"));
            assertTrue(e.getMessage().contains(klass.getName()));
            assertTrue(e.getMessage().contains("name"));
        }
    }

    @Test
    public void nonExistentInstanceFactoryTest() {
        Class klass = Person.class;
        ScrapperConfig config = new ScrapperConfig();
        config.registerClassConfig(new ClassConfig(klass));

        try {
            new Scrapper().scrap(config, klass);
            fail("Exception should be thrown!");
        } catch(ScrapperException e) {
            assertTrue(e.getMessage().contains("No InstanceFactory found"));
            assertTrue(e.getMessage().contains(klass.getName()));
        }
    }

    @Test
    public void nonExistentDriverSupplierTest() {
        Class klass = Person.class;
        ScrapperConfig config = new ScrapperConfig();
        config.registerInstanceFactory(klass, () -> null);

        try {
            new Scrapper().scrap(config, klass);
            fail("Exception should be thrown!");
        } catch(ScrapperException e) {
            assertTrue(e.getMessage().contains("No DriverSupplier found"));
            assertTrue(e.getMessage().contains(klass.getName()));
        }
    }

    @Test
    public void nonExistentClassDriverLoaderTest() {
        Class klass = Person.class;
        ScrapperConfig config = new ScrapperConfig();
        config.registerInstanceFactory(klass, () -> null);
        config.registerDriverSupplier(klass, () -> null);

        try {
            new Scrapper().scrap(config, klass);
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
        ScrapperConfig config = new ScrapperConfig();
        config.registerInstanceFactory(klass, Person::new);
        config.registerDriverSupplier(klass, () -> null);
        config.registerDriverLoader(klass, (d) -> {});
        config.registerFieldConfig(new FieldConfig(field));

        try {
            new Scrapper().scrap(config, klass);
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
        ScrapperConfig config = new ScrapperConfig();
        config.registerInstanceFactory(klass, Person::new);
        config.registerDriverSupplier(klass, () -> null);
        config.registerDriverLoader(klass, (d) -> {});
        config.registerDriverLoader(field, (d) -> {});

        try {
            new Scrapper().scrap(config, klass);
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
        ScrapperConfig config = new ScrapperConfig();
        config.registerInstanceFactory(klass, Person::new);
        config.registerDriverSupplier(klass, () -> null);
        config.registerDriverLoader(klass, (d) -> {});
        config.registerDriverLoader(field, (d) -> {});
        config.registerSelector(field, (d) -> null);

        try {
            new Scrapper().scrap(config, klass);
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
        ScrapperConfig config = new ScrapperConfig();
        config.registerInstanceFactory(klass, Person::new);
        config.registerDriverSupplier(klass, () -> null);
        config.registerDriverLoader(klass, (d) -> {});
        config.registerDriverLoader(field, (d) -> {});
        config.registerSelector(field, (d) -> null);
        config.registerElementLoader(field, (driver, elements) -> {});

        try {
            new Scrapper().scrap(config, klass);
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
        ScrapperConfig config = new ScrapperConfig();
        config.registerInstanceFactory(klass, Person::new);
        config.registerDriverSupplier(klass, () -> null);
        config.registerDriverLoader(klass, (d) -> {});
        config.registerDriverLoader(field, (d) -> {});
        config.registerSelector(field, (d) -> null);
        config.registerElementLoader(field, (driver, elements) -> {});
        config.registerParser(field, (driver, elements) -> null);

        try {
            new Scrapper().scrap(config, klass);
            fail("Exception should be thrown!");
        } catch(ScrapperException e) {
            assertTrue(e.getMessage().contains("No Property found"));
            assertTrue(e.getMessage().contains(klass.getName()));
            assertTrue(e.getMessage().contains(field.getName()));
        }
    }

    @Test
    public void wrapInstanceFactoryExceptionTest() {
        InstanceFactoryException exception = new InstanceFactoryException("");
        IInstanceFactory instanceFactory = () -> { throw exception; };

        Class klass = Person.class;
        ScrapperConfig config = new ScrapperConfig();
        config.registerInstanceFactory(klass, instanceFactory);
        config.registerDriverSupplier(klass, () -> null);
        config.registerDriverLoader(klass, (d) -> {});

        try {
            new Scrapper().scrap(config, klass);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapDriverSupplierExceptionTest() {
        DriverSupplierException exception = new DriverSupplierException("");
        IDriverSupplier driverSupplier = () -> { throw exception; };

        Class klass = Person.class;
        ScrapperConfig config = new ScrapperConfig();
        config.registerInstanceFactory(klass, Person::new);
        config.registerDriverSupplier(klass, driverSupplier);
        config.registerDriverLoader(klass, (d) -> {});

        try {
            new Scrapper().scrap(config, klass);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapClassDriverLoaderExceptionTest() {
        DriverLoaderException exception = new DriverLoaderException("");
        IDriverLoader driverLoader = (driver) -> { throw exception; };

        Class klass = Person.class;
        ScrapperConfig config = new ScrapperConfig();
        config.registerInstanceFactory(klass, Person::new);
        config.registerDriverSupplier(klass, () -> null);
        config.registerDriverLoader(klass, driverLoader);

        try {
            new Scrapper().scrap(config, klass);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapFieldDriverLoaderExceptionTest() throws NoSuchFieldException {
        DriverLoaderException exception = new DriverLoaderException("");
        IDriverLoader driverLoader = (driver) -> { throw exception; };

        Class klass = Person.class;
        Field field = klass.getDeclaredField("name");
        ScrapperConfig config = new ScrapperConfig();
        config.registerInstanceFactory(klass, Person::new);
        config.registerDriverSupplier(klass, () -> null);
        config.registerDriverLoader(klass, (d) -> {});
        config.registerDriverLoader(field, driverLoader);
        config.registerSelector(field, (d) -> null);
        config.registerElementLoader(field, (d, e) -> {});
        config.registerParser(field, (d, e) -> null);
        config.registerProperty(field, new IProperty() {
            @Override public Object get(Object instance) throws PropertyException { return null; }
            @Override public void set(Object instance, Object value) throws PropertyException { }
        });

        try {
            new Scrapper().scrap(config, klass);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapSelectorExceptionTest() throws NoSuchFieldException {
        SelectorException exception = new SelectorException("");
        ISelector selector = (driver) -> { throw exception; };

        Class klass = Person.class;
        Field field = klass.getDeclaredField("name");
        ScrapperConfig config = new ScrapperConfig();
        config.registerInstanceFactory(klass, Person::new);
        config.registerDriverSupplier(klass, () -> null);
        config.registerDriverLoader(klass, (d) -> {});
        config.registerDriverLoader(field, (d) -> {});
        config.registerSelector(field, selector);
        config.registerElementLoader(field, (d, e) -> {});
        config.registerParser(field, (d, e) -> null);
        config.registerProperty(field, new IProperty() {
            @Override public Object get(Object instance) throws PropertyException { return null; }
            @Override public void set(Object instance, Object value) throws PropertyException { }
        });

        try {
            new Scrapper().scrap(config, klass);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapElementLoaderExceptionTest() throws NoSuchFieldException {
        ElementLoaderException exception = new ElementLoaderException("");
        IElementLoader elementLoader = (driver, elements) -> { throw exception; };


        Class klass = Person.class;
        Field field = klass.getDeclaredField("name");
        ScrapperConfig config = new ScrapperConfig();
        config.registerInstanceFactory(klass, Person::new);
        config.registerDriverSupplier(klass, () -> null);
        config.registerDriverLoader(klass, (d) -> {});
        config.registerDriverLoader(field, (d) -> {});
        config.registerSelector(field, (d) -> null);
        config.registerElementLoader(field, elementLoader);
        config.registerParser(field, (d, e) -> null);
        config.registerProperty(field, new IProperty() {
            @Override public Object get(Object instance) throws PropertyException { return null; }
            @Override public void set(Object instance, Object value) throws PropertyException { }
        });

        try {
            new Scrapper().scrap(config, klass);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapParserExceptionTest() throws NoSuchFieldException {
        ParserException exception = new ParserException("");
        IParser parser = (driver, elements) -> { throw exception; };

        Class klass = Person.class;
        Field field = klass.getDeclaredField("name");
        ScrapperConfig config = new ScrapperConfig();
        config.registerInstanceFactory(klass, Person::new);
        config.registerDriverSupplier(klass, () -> null);
        config.registerDriverLoader(klass, (d) -> {});
        config.registerDriverLoader(field, (d) -> {});
        config.registerSelector(field, (d) -> null);
        config.registerElementLoader(field, (d, e) -> {});
        config.registerParser(field, parser);
        config.registerProperty(field, new IProperty() {
            @Override public Object get(Object instance) throws PropertyException { return null; }
            @Override public void set(Object instance, Object value) throws PropertyException { }
        });


        try {
            new Scrapper().scrap(config, klass);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapPropertyExceptionTest() throws NoSuchFieldException {
        PropertyException exception = new PropertyException("");
        IProperty property = new IProperty() {
            @Override public Object get(Object instance) throws PropertyException { throw exception; }
            @Override public void set(Object instance, Object value) throws PropertyException { throw exception; }
        };

        Class klass = Person.class;
        Field field = klass.getDeclaredField("name");
        ScrapperConfig config = new ScrapperConfig();
        config.registerInstanceFactory(klass, Person::new);
        config.registerDriverSupplier(klass, () -> null);
        config.registerDriverLoader(klass, (d) -> {});
        config.registerDriverLoader(field, (d) -> {});
        config.registerSelector(field, (d) -> null);
        config.registerElementLoader(field, (d, e) -> {});
        config.registerParser(field, (d, e) -> null);
        config.registerProperty(field, property);

        try {
            new Scrapper().scrap(config, klass);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void scrapTest() throws Exception {
        Class klass = Person.class;
        Field field = klass.getDeclaredField("name");
        Object result = new Object();
        WebDriver driver = getDriver();
        Collection<WebElement> elements = new LinkedList<>();
        Object value = new Person();

        boolean[] driverSupplierPassed = new boolean[1];
        boolean[] classDriverLoaderPassed = new boolean[1];
        boolean[] fieldDriverLoaderPassed = new boolean[1];
        boolean[] selectorPassed = new boolean[1];
        boolean[] elementElementLoaderPassed = new boolean[1];
        boolean[] parserPassed = new boolean[1];
        boolean[] propertyPassed = new boolean[1];

        IInstanceFactory instanceFactory = () -> result;
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

        ScrapperConfig config = new ScrapperConfig();
        config.registerInstanceFactory(klass, instanceFactory);
        config.registerDriverSupplier(klass, driverSupplier);
        config.registerDriverLoader(klass, classDriverLoader);
        config.registerDriverLoader(field, fieldDriverLoader);
        config.registerSelector(field, selector);
        config.registerElementLoader(field, elementLoader);
        config.registerParser(field, parser);
        config.registerProperty(field, property);

        Scrapper scrapper = new Scrapper();

        assertSame(result, scrapper.scrap(config, klass));

        assertTrue(driverSupplierPassed[0]);
        assertTrue(classDriverLoaderPassed[0]);
        assertTrue(fieldDriverLoaderPassed[0]);
        assertTrue(selectorPassed[0]);
        assertTrue(elementElementLoaderPassed[0]);
        assertTrue(parserPassed[0]);
        assertTrue(propertyPassed[0]);
    }

}
