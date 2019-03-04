package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.driverLoader.DriverLoaderException;
import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.elementLoader.ElementLoaderException;
import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;
import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;
import com.github.brunomndantas.jscrapper.core.pageBuilder.IPageBuilder;
import com.github.brunomndantas.jscrapper.core.pageBuilder.PageBuilderException;
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

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class ScatterTest {

    private static final String DRIVER_PATH = "phantomjs/phantomjs.exe";



    private static WebDriver getDriver() {
        String driverPath = ClassLoader.getSystemResource(DRIVER_PATH).getPath();

        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();

        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, driverPath);

        return new PhantomJSDriver(capabilities);
    }


    @Test
    public void getInstanceFactoryTest() {
        IInstanceFactory instanceFactory = new IInstanceFactory() {
            @Override
            public <T> T create(Class<T> klass) throws InstanceFactoryException {
                return null;
            }
        };

        Scrapper scrapper = new Scrapper(instanceFactory, null);

        assertSame(instanceFactory, scrapper.getInstanceFactory());
    }

    @Test
    public void getPageBuilderTest() {
        IPageBuilder pageBuilder = (o) -> null;
        Scrapper scrapper = new Scrapper(null, pageBuilder);

        assertSame(pageBuilder, scrapper.getPageBuilder());
    }

    @Test
    public void constructorTest() {
        IInstanceFactory instanceFactory = new IInstanceFactory() {
            @Override
            public <T> T create(Class<T> klass) throws InstanceFactoryException {
                return null;
            }
        };
        IPageBuilder pageBuilder = (o) -> null;
        Scrapper scrapper = new Scrapper(instanceFactory, pageBuilder);

        assertSame(instanceFactory, scrapper.getInstanceFactory());
        assertSame(pageBuilder, scrapper.getPageBuilder());
    }

    @Test
    public void nullPageDriverLoaderTest() throws Exception {
        IInstanceFactory instanceFactory = new IInstanceFactory() {
            @Override public <T> T create(Class<T> klass) throws InstanceFactoryException { return null; }
        };
        Page page = new Page("", "www.google.pt", null, ScatterTest::getDriver, new LinkedList<>());

        Scrapper scrapper = new Scrapper(instanceFactory, (o) -> page);

        scrapper.scrap(Object.class);
    }

    @Test
    public void nullElementDriverLoaderTest() throws Exception {
        IInstanceFactory instanceFactory = new IInstanceFactory() {
            @Override public <T> T create(Class<T> klass) throws InstanceFactoryException { return null; }
        };
        IProperty property = new IProperty() {
            @Override public Object get() throws PropertyException { return null; }
            @Override public void set(Object value) throws PropertyException { }
        };
        Page page = new Page("", "www.google.pt", null, ScatterTest::getDriver, new LinkedList<>());
        Element element = new Element("", null, (driver) -> null, (driver, elements) -> {}, (driver, elements) -> null, property);
        page.getElements().add(element);

        Scrapper scrapper = new Scrapper(instanceFactory, (o) -> page);

        scrapper.scrap(Object.class);
    }

    @Test
    public void nullElementElementLoaderTest() throws Exception {
        IInstanceFactory instanceFactory = new IInstanceFactory() {
            @Override public <T> T create(Class<T> klass) throws InstanceFactoryException { return null; }
        };
        IProperty property = new IProperty() {
            @Override public Object get() throws PropertyException { return null; }
            @Override public void set(Object value) throws PropertyException { }
        };
        Page page = new Page("", "www.google.pt", null, ScatterTest::getDriver, new LinkedList<>());
        Element element = new Element("", (driver) -> {}, (driver) -> null, null, (driver, elements) -> null, property);
        page.getElements().add(element);

        Scrapper scrapper = new Scrapper(instanceFactory, (o) -> page);

        scrapper.scrap(Object.class);
    }

    @Test
    public void wrapInstanceFactoryExceptionTest() {
        InstanceFactoryException exception = new InstanceFactoryException("");
        IInstanceFactory instanceFactory = new IInstanceFactory() {
            @Override public <T> T create(Class<T> klass) throws InstanceFactoryException { throw exception; }
        };

        Scrapper scrapper = new Scrapper(instanceFactory, null);

        try {
            scrapper.scrap(Object.class);
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapPageBuilderExceptionTest() {
        IInstanceFactory instanceFactory = new IInstanceFactory() {
            @Override public <T> T create(Class<T> klass) throws InstanceFactoryException { return (T) new Object(); }
        };
        PageBuilderException exception = new PageBuilderException("");
        IPageBuilder pageBuilder = (o) -> { throw exception; };

        Scrapper scrapper = new Scrapper(instanceFactory, pageBuilder);

        try {
            scrapper.scrap(Object.class);
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapPageDriverSupplierExceptionTest() {
        IInstanceFactory instanceFactory = new IInstanceFactory() {
            @Override public <T> T create(Class<T> klass) throws InstanceFactoryException { return null; }
        };
        DriverSupplierException exception = new DriverSupplierException("");
        IDriverSupplier driverSupplier = () -> { throw exception; };

        Page page = new Page("", "www.google.pt", null, driverSupplier, new LinkedList<>());
        Scrapper scrapper = new Scrapper(instanceFactory, (o) -> page);

        try {
            scrapper.scrap(Object.class);
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapPageDriverLoaderExceptionTest() {
        IInstanceFactory instanceFactory = new IInstanceFactory() {
            @Override public <T> T create(Class<T> klass) throws InstanceFactoryException { return null; }
        };
        DriverLoaderException exception = new DriverLoaderException("");
        IDriverLoader driverLoader = (driver) -> { throw exception; };

        Page page = new Page("", "www.google.pt", driverLoader, ScatterTest::getDriver, new LinkedList<>());
        Scrapper scrapper = new Scrapper(instanceFactory, (o) -> page);

        try {
            scrapper.scrap(Object.class);
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapElementDriverLoaderExceptionTest() {
        IInstanceFactory instanceFactory = new IInstanceFactory() {
            @Override public <T> T create(Class<T> klass) throws InstanceFactoryException { return null; }
        };
        DriverLoaderException exception = new DriverLoaderException("");
        IDriverLoader driverLoader = (driver) -> { throw exception; };

        Page page = new Page("", "www.google.pt", null, ScatterTest::getDriver, new LinkedList<>());
        Element element = new Element("", driverLoader, null, null, null, null);
        page.getElements().add(element);
        Scrapper scrapper = new Scrapper(instanceFactory, (o) -> page);

        try {
            scrapper.scrap(Object.class);
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapElementSelectorExceptionTest() {
        IInstanceFactory instanceFactory = new IInstanceFactory() {
            @Override public <T> T create(Class<T> klass) throws InstanceFactoryException { return null; }
        };
        SelectorException  exception = new SelectorException("");
        ISelector selector = (driver) -> { throw exception; };

        Page page = new Page("", "www.google.pt", null, ScatterTest::getDriver, new LinkedList<>());
        Element element = new Element("", null, selector, null, null, null);
        page.getElements().add(element);
        Scrapper scrapper = new Scrapper(instanceFactory, (o) -> page);

        try {
            scrapper.scrap(Object.class);
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapElementElementLoaderExceptionTest() {
        IInstanceFactory instanceFactory = new IInstanceFactory() {
            @Override public <T> T create(Class<T> klass) throws InstanceFactoryException { return null; }
        };
        ElementLoaderException exception = new ElementLoaderException("");
        IElementLoader elementLoader = (driver, elements) -> { throw exception; };

        Page page = new Page("", "www.google.pt", null, ScatterTest::getDriver, new LinkedList<>());
        Element element = new Element("", null, (driver) -> null, elementLoader, null, null);
        page.getElements().add(element);
        Scrapper scrapper = new Scrapper(instanceFactory, (o) -> page);

        try {
            scrapper.scrap(Object.class);
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapElementParserExceptionTest() {
        IInstanceFactory instanceFactory = new IInstanceFactory() {
            @Override public <T> T create(Class<T> klass) throws InstanceFactoryException { return null; }
        };
        ParserException exception = new ParserException("");
        IParser parser = (driver, elements) -> { throw exception; };

        Page page = new Page("", "www.google.pt", null, ScatterTest::getDriver, new LinkedList<>());
        Element element = new Element("", null, (driver) -> null, null, parser, null);
        page.getElements().add(element);
        Scrapper scrapper = new Scrapper(instanceFactory, (o) -> page);

        try {
            scrapper.scrap(Object.class);
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapElementPropertyExceptionTest() {
        IInstanceFactory instanceFactory = new IInstanceFactory() {
            @Override public <T> T create(Class<T> klass) throws InstanceFactoryException { return null; }
        };
        PropertyException exception = new PropertyException("");
        IProperty property = new IProperty() {
            @Override public Object get() throws PropertyException { throw exception; }
            @Override public void set(Object value) throws PropertyException { throw exception; }
        };

        Page page = new Page("", "www.google.pt", null, ScatterTest::getDriver, new LinkedList<>());
        Element element = new Element("", null, (driver) -> null, null, (driver, elements) -> null, property);
        page.getElements().add(element);
        Scrapper scrapper = new Scrapper(instanceFactory, (o) -> page);

        try {
            scrapper.scrap(Object.class);
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void scrapTest() throws ScrapperException {
        Object result = new Object();
        IInstanceFactory instanceFactory = new IInstanceFactory() {
            @Override public <T> T create(Class<T> klass) throws InstanceFactoryException { return (T)result; }
        };
        WebDriver driver = getDriver();
        Collection<WebElement> elements = new LinkedList<>();
        Object value = new Object();

        boolean[] pageBuilderPassed = new boolean[1];
        boolean[] driverSupplierPassed = new boolean[1];
        boolean[] pageDriverLoaderPassed = new boolean[1];
        boolean[] elementDriverLoaderPassed = new boolean[1];
        boolean[] selectorPassed = new boolean[1];
        boolean[] elementElementLoaderPassed = new boolean[1];
        boolean[] parserPassed = new boolean[1];
        boolean[] propertyPassed = new boolean[1];

        IDriverSupplier driverSupplier = () -> { driverSupplierPassed[0] = true; return driver; };
        IDriverLoader pageDriverLoader = (d) -> { pageDriverLoaderPassed[0] = d == driver; };
        IDriverLoader elementDriverLoader = (d) -> { elementDriverLoaderPassed[0] = d == driver; };
        ISelector selector = (d) -> { selectorPassed[0] = d == driver; return elements; };
        IElementLoader elementElementLoader = (d, e) -> { elementElementLoaderPassed[0] = d == driver && e == elements; };
        IParser parser = (d, e) -> { parserPassed[0] = d == driver && e == elements; return value; };
        IProperty property = new IProperty() {
            @Override public Object get() throws PropertyException { return null; }
            @Override public void set(Object v) throws PropertyException { propertyPassed[0] = v == value; }
        };

        Page page = new Page("", "www.google.pt", pageDriverLoader, driverSupplier, new LinkedList<>());
        Element element = new Element("", elementDriverLoader, selector, elementElementLoader, parser, property);
        page.getElements().add(element);

        IPageBuilder pageBuilder = (o) -> { pageBuilderPassed[0] = o == result; return page; };

        Scrapper scrapper = new Scrapper(instanceFactory, pageBuilder);
        assertSame(result, scrapper.scrap(Object.class));

        assertTrue(pageBuilderPassed[0]);
        assertTrue(driverSupplierPassed[0]);
        assertTrue(pageDriverLoaderPassed[0]);
        assertTrue(elementDriverLoaderPassed[0]);
        assertTrue(selectorPassed[0]);
        assertTrue(elementElementLoaderPassed[0]);
        assertTrue(parserPassed[0]);
        assertTrue(propertyPassed[0]);
    }

}
