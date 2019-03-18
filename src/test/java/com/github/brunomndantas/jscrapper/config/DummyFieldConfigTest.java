package com.github.brunomndantas.jscrapper.config;

import com.github.brunomndantas.jscrapper.core.property.IProperty;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class DummyFieldConfigTest {

    private static class Person {
        public String name;
    }



    @Test
    public void dummyPropertyTest() throws Exception {
        IProperty property = new DummyFieldConfig.DummyProperty();

        property.set(null, null);
        assertNull(property.get(null));
    }

    @Test
    public void constructorTest() throws Exception {
        Field field = Person.class.getDeclaredField("name");
        DummyFieldConfig config = new DummyFieldConfig(field);

        assertSame(field, config.getField());
        assertNotNull(config.getDriverLoader());
        assertNotNull(config.getSelector());
        assertNotNull(config.getElementLoader());
        assertNotNull(config.getParser());
        assertNotNull(config.getProperty());
    }

}
