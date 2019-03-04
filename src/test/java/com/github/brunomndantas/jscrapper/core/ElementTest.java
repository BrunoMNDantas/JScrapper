package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;
import com.github.brunomndantas.jscrapper.core.pageLoader.IPageLoader;
import com.github.brunomndantas.jscrapper.core.parser.IParser;
import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.property.PropertyException;
import com.github.brunomndantas.jscrapper.core.selector.ISelector;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class ElementTest {

    @Test
    public void getIdTest() {
        String id = "";
        Element element = new Element(id, null, null, null, null, null);
        assertSame(id, element.getId());
    }

    @Test
    public void getPageLoaderTest() {
        IPageLoader pageLoader = driver -> {};
        Element element = new Element(null, pageLoader, null, null, null, null);
        assertSame(pageLoader, element.getPageLoader());
    }

    @Test
    public void getSelectorTest() {
        ISelector selector = driver -> null;
        Element element = new Element(null, null, selector, null, null, null);
        assertSame(selector, element.getSelector());
    }

    @Test
    public void getElementLoaderTest() {
        IElementLoader elementLoader = (driver, elements) -> {};
        Element element = new Element(null, null, null, elementLoader, null, null);
        assertSame(elementLoader, element.getElementLoader());
    }

    @Test
    public void getParserTest() {
        IParser parser = (driver, elements) -> null;
        Element element = new Element(null, null, null, null, parser, null);
        assertSame(parser, element.getParser());
    }

    @Test
    public void getPropertyTest() {
        IProperty property = new IProperty() {
            @Override public Object get() throws PropertyException { return null; }
            @Override public void set(Object value) throws PropertyException { }
        };
        Element element = new Element(null, null, null, null, null, property);
        assertSame(property, element.getProperty());

    }

    @Test
    public void constructorTest() {
        String id = "";
        IPageLoader pageLoader = driver -> {};
        ISelector selector = driver -> null;
        IElementLoader elementLoader = (driver, elements) -> {};
        IParser parser = (driver, elements) -> null;
        IProperty property = new IProperty() {
            @Override public Object get() throws PropertyException { return null; }
            @Override public void set(Object value) throws PropertyException { }
        };

        Element element = new Element(id, pageLoader, selector, elementLoader, parser, property);

        assertSame(id, element.getId());
        assertSame(pageLoader, element.getPageLoader());
        assertSame(selector, element.getSelector());
        assertSame(elementLoader, element.getElementLoader());
        assertSame(parser, element.getParser());
        assertSame(property, element.getProperty());
    }

}
