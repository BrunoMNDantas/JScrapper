package com.github.brunomndantas.jscrapper.core.config;

import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;
import com.github.brunomndantas.jscrapper.core.parser.IParser;
import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.property.PropertyException;
import com.github.brunomndantas.jscrapper.core.selector.ISelector;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class FieldConfigTest {

    @Test
    public void getFieldTest() throws NoSuchFieldException {
        Field field = FieldConfig.class.getDeclaredField("field");

        FieldConfig config = new FieldConfig(field, null, null, null, null, null);

        assertSame(field, config.getField());
    }

    @Test
    public void setFieldTest() throws NoSuchFieldException {
        Field field = FieldConfig.class.getDeclaredField("field");

        FieldConfig config = new FieldConfig(null, null, null, null, null, null);

        config.setField(field);
        assertSame(field, config.getField());
    }

    @Test
    public void getDriverLoaderTest() {
        IDriverLoader loader = driver -> {};

        FieldConfig config = new FieldConfig(null, loader, null, null, null, null);

        assertSame(loader, config.getDriverLoader());
    }

    @Test
    public void setDriverLoaderTest() {
        IDriverLoader loader = driver -> {};

        FieldConfig config = new FieldConfig(null, null, null, null, null, null);

        config.setDriverLoader(loader);
        assertSame(loader, config.getDriverLoader());
    }

    @Test
    public void getSelectorTest() {
        ISelector selector = driver -> null;

        FieldConfig config = new FieldConfig(null, null, selector, null, null, null);

        assertSame(selector, config.getSelector());
    }

    @Test
    public void setSelectorTest() {
        ISelector selector = driver -> null;

        FieldConfig config = new FieldConfig(null, null, null, null, null, null);

        config.setSelector(selector);
        assertSame(selector, config.getSelector());
    }

    @Test
    public void getElementLoaderTest() {
        IElementLoader loader = (driver, elements) -> {};

        FieldConfig config = new FieldConfig(null, null, null, loader, null, null);

        assertSame(loader, config.getElementLoader());
    }

    @Test
    public void setElementLoaderTest() {
        IElementLoader loader = (driver, elements) -> {};

        FieldConfig config = new FieldConfig(null, null, null, null, null, null);

        config.setElementLoader(loader);
        assertSame(loader, config.getElementLoader());
    }

    @Test
    public void getParserTest() {
        IParser parser = (driver, elements) -> null;

        FieldConfig config = new FieldConfig(null, null, null, null, parser, null);

        assertSame(parser, config.getParser());
    }

    @Test
    public void setParserTest() {
        IParser parser = (driver, elements) -> null;

        FieldConfig config = new FieldConfig(null, null, null, null, null, null);

        config.setParser(parser);
        assertSame(parser, config.getParser());
    }

    @Test
    public void getPropertyTest() {
        IProperty property = new IProperty() {
            @Override public Object get(Object instance) throws PropertyException { return null; }
            @Override public void set(Object instance, Object value) throws PropertyException { }
        };

        FieldConfig config = new FieldConfig(null, null, null, null, null, property);

        assertSame(property, config.getProperty());
    }

    @Test
    public void setPropertyTest() {
        IProperty property = new IProperty() {
            @Override public Object get(Object instance) throws PropertyException { return null; }
            @Override public void set(Object instance, Object value) throws PropertyException { }
        };

        FieldConfig config = new FieldConfig(null, null, null, null, null, null);

        config.setProperty(property);
        assertSame(property, config.getProperty());
    }

    @Test
    public void constructorsTest() throws NoSuchFieldException {
        Field field = FieldConfig.class.getDeclaredField("field");
        IDriverLoader driverLoader = driver -> {};
        ISelector selector = driver -> null;
        IElementLoader elementLoader = (driver, elements) -> {};
        IParser parser = (driver, elements) -> null;
        IProperty property = new IProperty() {
            @Override public Object get(Object instance) throws PropertyException { return null; }
            @Override public void set(Object instance, Object value) throws PropertyException { }
        };

        FieldConfig config = new FieldConfig();

        assertNull(config.getField());
        assertNull(config.getDriverLoader());
        assertNull(config.getSelector());
        assertNull(config.getElementLoader());
        assertNull(config.getParser());
        assertNull(config.getProperty());

        config = new FieldConfig(field);

        assertSame(field, config.getField());
        assertNull(config.getDriverLoader());
        assertNull(config.getSelector());
        assertNull(config.getElementLoader());
        assertNull(config.getParser());
        assertNull(config.getProperty());

        config = new FieldConfig(field, driverLoader, selector, elementLoader, parser, property);

        assertSame(field, config.getField());
        assertSame(driverLoader, config.getDriverLoader());
        assertSame(selector, config.getSelector());
        assertSame(elementLoader, config.getElementLoader());
        assertSame(parser, config.getParser());
        assertSame(property, config.getProperty());
    }

}

