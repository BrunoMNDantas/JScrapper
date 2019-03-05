package com.github.brunomndantas.jscrapper.property;

import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.property.PropertyException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComposedPropertyTest {

    @Test
    public void getGetterTest() {
        FieldProperty getter = new FieldProperty(null);

        ComposedProperty property = new ComposedProperty(getter, null);

        assertSame(getter, property.getGetter());
    }

    @Test
    public void getSetterTest() {
        FieldProperty setter = new FieldProperty(null);

        ComposedProperty property = new ComposedProperty(null, setter);

        assertSame(setter, property.getSetter());
    }

    @Test
    public void constructorTest() {
        FieldProperty getter = new FieldProperty(null);
        FieldProperty setter = new FieldProperty(null);

        ComposedProperty property = new ComposedProperty(getter, setter);

        assertSame(getter, property.getGetter());
        assertSame(setter, property.getSetter());
    }

    @Test
    public void getTest() throws Exception {
        String name = "";
        MethodPropertyTest.PersonPublic personGet = new MethodPropertyTest.PersonPublic(name);
        MethodPropertyTest.PersonPublic personSet = new MethodPropertyTest.PersonPublic(name);

        MethodProperty getter = new MethodProperty(personGet.getClass().getDeclaredField("name"));
        MethodProperty setter = new MethodProperty(personSet.getClass().getDeclaredField("name"));

        ComposedProperty property = new ComposedProperty(getter, setter);

        assertSame(name, property.get(personGet));
        assertTrue(personGet.getterUsed);
        assertFalse(personSet.getterUsed);
    }

    @Test
    public void setTest() throws  Exception {
        MethodPropertyTest.PersonPublic personGet = new MethodPropertyTest.PersonPublic("");
        MethodPropertyTest.PersonPublic personSet = new MethodPropertyTest.PersonPublic("");

        MethodProperty getter = new MethodProperty(personGet.getClass().getDeclaredField("name"));
        MethodProperty setter = new MethodProperty(personSet.getClass().getDeclaredField("name"));

        ComposedProperty property = new ComposedProperty(getter, setter);

        property.set(personSet, "");
        assertFalse(personGet.setterUsed);
        assertTrue(personSet.setterUsed);
    }

    @Test
    public void getFail() throws Exception {
        PropertyException exception = new PropertyException("", null);
        IProperty prop = new IProperty() {
            @Override
            public Object get(Object instance) throws PropertyException {
                throw exception;
            }

            @Override
            public void set(Object instance, Object value) throws PropertyException {
                throw exception;
            }
        };

        ComposedProperty property = new ComposedProperty(prop, prop);

        try {
            property.get(new Object());
            fail("Exception should be thrown!");
        } catch (PropertyException e) {
           assertSame(exception, e);
        }
    }

    @Test
    public void setFail() throws Exception {
        PropertyException exception = new PropertyException("", null);
        IProperty prop = new IProperty() {
            @Override
            public Object get(Object instance) throws PropertyException {
                throw exception;
            }

            @Override
            public void set(Object instance, Object value) throws PropertyException {
                throw exception;
            }
        };

        ComposedProperty property = new ComposedProperty(prop, prop);

        try {
            property.set(new Object(), "");
            fail("Exception should be thrown!");
        } catch (PropertyException e) {
            assertSame(exception, e);
        }
    }

}
