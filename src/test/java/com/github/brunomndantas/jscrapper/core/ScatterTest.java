package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.elementLoader.ElementLoaderException;
import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;
import com.github.brunomndantas.jscrapper.core.pageLoader.IPageLoader;
import com.github.brunomndantas.jscrapper.core.pageLoader.PageLoaderException;
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
    public void nullPagePageLoaderTest() throws Exception {
        Page page = new Page("", "www.google.pt", null, ScatterTest::getDriver, new LinkedList<>());

        Scrapper scrapper = new Scrapper();

        scrapper.scrap(page);
    }

    @Test
    public void nullElementPageLoaderTest() throws Exception {
        IProperty property = new IProperty() {
            @Override public Object get() throws PropertyException { return null; }
            @Override public void set(Object value) throws PropertyException { }
        };
        Page page = new Page("", "www.google.pt", null, ScatterTest::getDriver, new LinkedList<>());
        Element element = new Element("", null, (driver) -> null, (driver, elements) -> {}, (driver, elements) -> null, property);
        page.getElements().add(element);

        Scrapper scrapper = new Scrapper();

        scrapper.scrap(page);
    }

    @Test
    public void nullElementElementLoaderTest() throws Exception {
        IProperty property = new IProperty() {
            @Override public Object get() throws PropertyException { return null; }
            @Override public void set(Object value) throws PropertyException { }
        };
        Page page = new Page("", "www.google.pt", null, ScatterTest::getDriver, new LinkedList<>());
        Element element = new Element("", (driver) -> {}, (driver) -> null, null, (driver, elements) -> null, property);
        page.getElements().add(element);

        Scrapper scrapper = new Scrapper();

        scrapper.scrap(page);
    }

    @Test
    public void wrapPageDriverSupplierExceptionTest() {
        DriverSupplierException exception = new DriverSupplierException("");
        IDriverSupplier driverSupplier = () -> { throw exception; };

        Page page = new Page("", "www.google.pt", null, driverSupplier, new LinkedList<>());
        Scrapper scrapper = new Scrapper();

        try {
            scrapper.scrap(page);
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapPagePageLoaderExceptionTest() {
        PageLoaderException exception = new PageLoaderException("");
        IPageLoader pageLoader = (driver) -> { throw exception; };

        Page page = new Page("", "www.google.pt", pageLoader, ScatterTest::getDriver, new LinkedList<>());
        Scrapper scrapper = new Scrapper();

        try {
            scrapper.scrap(page);
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapElementPageLoaderExceptionTest() {
        PageLoaderException exception = new PageLoaderException("");
        IPageLoader pageLoader = (driver) -> { throw exception; };

        Page page = new Page("", "www.google.pt", null, ScatterTest::getDriver, new LinkedList<>());
        Element element = new Element("", pageLoader, null, null, null, null);
        page.getElements().add(element);
        Scrapper scrapper = new Scrapper();

        try {
            scrapper.scrap(page);
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapElementSelectorExceptionTest() {
        SelectorException  exception = new SelectorException("");
        ISelector selector = (driver) -> { throw exception; };

        Page page = new Page("", "www.google.pt", null, ScatterTest::getDriver, new LinkedList<>());
        Element element = new Element("", null, selector, null, null, null);
        page.getElements().add(element);
        Scrapper scrapper = new Scrapper();

        try {
            scrapper.scrap(page);
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapElementElementLoaderExceptionTest() {
        ElementLoaderException exception = new ElementLoaderException("");
        IElementLoader elementLoader = (driver, elements) -> { throw exception; };

        Page page = new Page("", "www.google.pt", null, ScatterTest::getDriver, new LinkedList<>());
        Element element = new Element("", null, (driver) -> null, elementLoader, null, null);
        page.getElements().add(element);
        Scrapper scrapper = new Scrapper();

        try {
            scrapper.scrap(page);
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapElementParserExceptionTest() {
        ParserException exception = new ParserException("");
        IParser parser = (driver, elements) -> { throw exception; };

        Page page = new Page("", "www.google.pt", null, ScatterTest::getDriver, new LinkedList<>());
        Element element = new Element("", null, (driver) -> null, null, parser, null);
        page.getElements().add(element);
        Scrapper scrapper = new Scrapper();

        try {
            scrapper.scrap(page);
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapElementPropertyExceptionTest() {
        PropertyException exception = new PropertyException("");
        IProperty property = new IProperty() {
            @Override public Object get() throws PropertyException { throw exception; }
            @Override public void set(Object value) throws PropertyException { throw exception; }
        };

        Page page = new Page("", "www.google.pt", null, ScatterTest::getDriver, new LinkedList<>());
        Element element = new Element("", null, (driver) -> null, null, (driver, elements) -> null, property);
        page.getElements().add(element);
        Scrapper scrapper = new Scrapper();

        try {
            scrapper.scrap(page);
        } catch (ScrapperException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void scrapTest() throws ScrapperException {
        WebDriver driver = getDriver();
        Collection<WebElement> elements = new LinkedList<>();
        Object value = new Object();

        boolean[] driverSupplierPassed = new boolean[1];
        boolean[] pagePageLoaderPassed = new boolean[1];
        boolean[] elementPageLoaderPassed = new boolean[1];
        boolean[] selectorPassed = new boolean[1];
        boolean[] elementElementLoaderPassed = new boolean[1];
        boolean[] parserPassed = new boolean[1];
        boolean[] propertyPassed = new boolean[1];

        IDriverSupplier driverSupplier = () -> { driverSupplierPassed[0] = true; return driver; };
        IPageLoader pagePageLoader = (d) -> { pagePageLoaderPassed[0] = d == driver; };
        IPageLoader elementPageLoader = (d) -> { elementPageLoaderPassed[0] = d == driver; };
        ISelector selector = (d) -> { selectorPassed[0] = d == driver; return elements; };
        IElementLoader elementElementLoader = (d, e) -> { elementElementLoaderPassed[0] = d == driver && e == elements; };
        IParser parser = (d, e) -> { parserPassed[0] = d == driver && e == elements; return value; };
        IProperty property = new IProperty() {
            @Override public Object get() throws PropertyException { return null; }
            @Override public void set(Object v) throws PropertyException { propertyPassed[0] = v == value; }
        };

        Page page = new Page("", "www.google.pt", pagePageLoader, driverSupplier, new LinkedList<>());
        Element element = new Element("", elementPageLoader, selector, elementElementLoader, parser, property);
        page.getElements().add(element);

        Scrapper scrapper = new Scrapper();
        scrapper.scrap(page);

        assertTrue(driverSupplierPassed[0]);
        assertTrue(pagePageLoaderPassed[0]);
        assertTrue(elementPageLoaderPassed[0]);
        assertTrue(selectorPassed[0]);
        assertTrue(elementElementLoaderPassed[0]);
        assertTrue(parserPassed[0]);
        assertTrue(propertyPassed[0]);
    }

}
