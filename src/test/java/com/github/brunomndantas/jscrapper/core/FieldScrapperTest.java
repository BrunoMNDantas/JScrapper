package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.config.FieldConfig;
import com.github.brunomndantas.jscrapper.core.driverLoader.DriverLoaderException;
import com.github.brunomndantas.jscrapper.core.elementLoader.ElementLoaderException;
import com.github.brunomndantas.jscrapper.core.parser.ParserException;
import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.property.PropertyException;
import com.github.brunomndantas.jscrapper.core.selector.SelectorException;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class FieldScrapperTest {

    public class Person {
        public String name;
    }



    private static final String DRIVER_PATH = "phantomjs/phantomjs.exe";



    private static WebDriver getDriver() {
        String driverPath = ClassLoader.getSystemResource(DRIVER_PATH).getPath();

        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();

        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, driverPath);

        return new PhantomJSDriver(capabilities);
    }

    private static FieldConfig createDummyFieldConfig() throws NoSuchFieldException {
        FieldConfig config = new FieldConfig(Person.class.getDeclaredField("name"));
        config.setDriverLoader((d) -> {});
        config.setSelector((d) -> null);
        config.setElementLoader((d, e) -> {});
        config.setParser((d, e) -> null);
        config.setProperty(new IProperty() {
            @Override public Object get(Object instance) throws PropertyException { return null; }
            @Override public void set(Object instance, Object value) throws PropertyException { }
        });

        return config;
    }



    @Test
    public void getConfigTest() throws Exception {
        FieldConfig config = createDummyFieldConfig();
        FieldScrapper scrapper = new FieldScrapper(config, Person.class);

        assertSame(config, scrapper.getConfig());
    }

    @Test
    public void nullDriverLoaderTest() throws Exception {
        Class klass = Person.class;
        FieldConfig config = createDummyFieldConfig();
        config.setDriverLoader(null);

        try {
            new FieldScrapper(config, Person.class);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("No DriverLoader found"));
            assertTrue(e.getMessage().contains(klass.getName()));
            assertTrue(e.getMessage().contains(config.getField().getName()));
        }
    }

    @Test
    public void nullSelectorTest() throws Exception {
        Class klass = Person.class;
        FieldConfig config = createDummyFieldConfig();
        config.setSelector(null);
        
        try {
            new FieldScrapper(config, Person.class);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("No Selector found"));
            assertTrue(e.getMessage().contains(klass.getName()));
            assertTrue(e.getMessage().contains(config.getField().getName()));
        }
    }

    @Test
    public void nullElementLoaderTest() throws Exception {
        Class klass = Person.class;
        FieldConfig config = createDummyFieldConfig();
        config.setElementLoader(null);

        try {
            new FieldScrapper(config, Person.class);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("No ElementLoader found"));
            assertTrue(e.getMessage().contains(klass.getName()));
            assertTrue(e.getMessage().contains(config.getField().getName()));
        }
    }

    @Test
    public void nullParserTest() throws Exception {
        Class klass = Person.class;
        FieldConfig config = createDummyFieldConfig();
        config.setParser(null);
        
        try {
            new FieldScrapper(config, Person.class);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("No Parser found"));
            assertTrue(e.getMessage().contains(klass.getName()));
            assertTrue(e.getMessage().contains(config.getField().getName()));
        }
    }

    @Test
    public void nullPropertyTest() throws Exception {
        Class klass = Person.class;
        FieldConfig config = createDummyFieldConfig();
        config.setProperty(null);

        try {
            new FieldScrapper(config, Person.class);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("No Property found"));
            assertTrue(e.getMessage().contains(klass.getName()));
            assertTrue(e.getMessage().contains(config.getField().getName()));
        }
    }

    @Test
    public void constructorTest() throws Exception {
        FieldConfig config = createDummyFieldConfig();
        FieldScrapper scrapper = new FieldScrapper(config, Person.class);

        assertSame(config, scrapper.getConfig());
    }

    @Test
    public void loadDriverTest() throws Exception {
        FieldConfig config = createDummyFieldConfig();
        WebDriver driver = getDriver();
        boolean[] passed = new boolean[1];
        config.setDriverLoader((d) -> passed[0] = d==driver);

        FieldScrapper scrapper = new FieldScrapper(config, Person.class);

        scrapper.loadDriver(driver);
        assertTrue(passed[0]);
    }

    @Test(expected = DriverLoaderException.class)
    public void loadDriverFailTest() throws Exception {
        DriverLoaderException exception = new DriverLoaderException("");
        FieldConfig config = createDummyFieldConfig();
        config.setDriverLoader((d) -> { throw exception; });

        FieldScrapper scrapper = new FieldScrapper(config, Person.class);

        scrapper.loadDriver(null);
    }

    @Test
    public void selectElementsTest() throws Exception {
        FieldConfig config = createDummyFieldConfig();
        WebDriver driver = getDriver();
        Collection<WebElement> elements = new LinkedList<>();
        boolean[] passed = new boolean[1];
        config.setSelector((d) -> { passed[0] = d==driver; return elements; });

        FieldScrapper scrapper = new FieldScrapper(config, Person.class);

        assertSame(elements, scrapper.selectElements(driver));
        assertTrue(passed[0]);
    }

    @Test(expected = SelectorException.class)
    public void selectElementsFailTest() throws Exception {
        SelectorException exception = new SelectorException("");
        FieldConfig config = createDummyFieldConfig();
        config.setSelector((d) -> { throw exception; });

        FieldScrapper scrapper = new FieldScrapper(config, Person.class);

        scrapper.selectElements(null);
    }

    @Test
    public void loadElementsTest() throws Exception {
        FieldConfig config = createDummyFieldConfig();
        WebDriver driver = getDriver();
        Collection<WebElement> elements = new LinkedList<>();
        boolean[] passed = new boolean[1];
        config.setElementLoader((d, e) -> passed[0] = d==driver && e==elements);

        FieldScrapper scrapper = new FieldScrapper(config, Person.class);
        scrapper.loadElements(driver, elements);

        assertTrue(passed[0]);
    }

    @Test(expected = ElementLoaderException.class)
    public void loadElementsFailTest() throws Exception {
        ElementLoaderException exception = new ElementLoaderException("");
        FieldConfig config = createDummyFieldConfig();
        config.setElementLoader((d, e) -> { throw exception; });

        FieldScrapper scrapper = new FieldScrapper(config, Person.class);

        scrapper.loadElements(null, null);
    }

    @Test
    public void parseElementsTest() throws Exception {
        FieldConfig config = createDummyFieldConfig();
        WebDriver driver = getDriver();
        Collection<WebElement> elements = new LinkedList<>();
        Object value = new Object();
        boolean[] passed = new boolean[1];
        config.setParser((d, e) -> { passed[0] = d==driver && e==elements; return value; });

        FieldScrapper scrapper = new FieldScrapper(config, Person.class);

        assertSame(value, scrapper.parseElements(driver, elements));
        assertTrue(passed[0]);
    }

    @Test(expected = ParserException.class)
    public void parseElementsFailTest() throws Exception {
        ParserException exception = new ParserException("");
        FieldConfig config = createDummyFieldConfig();
        config.setParser((d, e) -> { throw exception; });

        FieldScrapper scrapper = new FieldScrapper(config, Person.class);

        scrapper.parseElements(null, null);
    }

    @Test
    public void getValueTest() throws Exception {
        FieldConfig config = createDummyFieldConfig();
        Object value = new Object();
        Object instance = new Object();
        boolean[] passed = new boolean[1];
        config.setProperty(new IProperty() {
            @Override public Object get(Object i) { passed[0] = i==instance; return value; }
            @Override public void set(Object instance, Object value) { }
        });

        FieldScrapper scrapper = new FieldScrapper(config, Person.class);

        assertSame(value, scrapper.getValue(instance));
        assertTrue(passed[0]);
    }

    @Test(expected = PropertyException.class)
    public void getValueFailTest() throws Exception {
        PropertyException exception = new PropertyException("");
        FieldConfig config = createDummyFieldConfig();
        config.setProperty(new IProperty() {
            @Override public Object get(Object instance) throws PropertyException { throw exception; }
            @Override public void set(Object instance, Object value) throws PropertyException { }
        });

        FieldScrapper scrapper = new FieldScrapper(config, Person.class);

        scrapper.getValue(null);
    }

    @Test
    public void setValueTest() throws Exception {
        FieldConfig config = createDummyFieldConfig();
        Object value = new Object();
        Object instance = new Object();
        boolean[] passed = new boolean[1];
        config.setProperty(new IProperty() {
            @Override public Object get(Object instance) { return null; }
            @Override public void set(Object i, Object v) { passed[0] = i==instance && v==value; }
        });

        FieldScrapper scrapper = new FieldScrapper(config, Person.class);

        scrapper.setValue(instance, value);
        assertTrue(passed[0]);
    }

    @Test(expected = PropertyException.class)
    public void setValueFailTest() throws Exception {
        PropertyException exception = new PropertyException("");
        FieldConfig config = createDummyFieldConfig();
        config.setProperty(new IProperty() {
            @Override public Object get(Object instance) throws PropertyException { return null; }
            @Override public void set(Object instance, Object value) throws PropertyException { throw exception; }
        });

        FieldScrapper scrapper = new FieldScrapper(config, Person.class);

        scrapper.setValue(null, null);
    }

}
