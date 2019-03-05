package com.github.brunomndantas.jscrapper.property;

import com.github.brunomndantas.jscrapper.core.property.PropertyException;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class PropertyTest {

    public static class Person {

        public String name;



        public Person(String name) {
            this.name = name;
        }

    }



    public static class DummyProperty extends Property {

        public DummyProperty(Field field) {
            super(field);
        }



        @Override
        protected Object getValue(Object instance) throws Exception {
            return null;
        }

        @Override
        protected void setValue(Object instance, Object value) throws Exception {

        }
    }



    @Test
    public void getFieldTest() throws Exception {
        Person target = new Person("");
        Field field = target.getClass().getField("name");

        Property property = new DummyProperty(field);

        assertSame(field, property.getField());
    }

    @Test
    public void getWithSuccessTest() throws Exception {
        String name = "";
        Person target = new Person(name);
        Field field = target.getClass().getField("name");

        Property property = new DummyProperty(field) {
            @Override
            protected Object getValue(Object instance) throws Exception {
                return super.field.get(instance);
            }
        };

        assertSame(name, property.get(target));
    }

    @Test
    public void setWithSuccessTest() throws Exception {
        Person target = new Person("");
        Field field = target.getClass().getField("name");

        Property property = new DummyProperty(field) {
            @Override
            protected Object getValue(Object instance) throws Exception {
                return super.field.get(instance);
            }

            @Override
            protected void setValue(Object instance, Object value) throws Exception {
                super.field.set(instance, value);
            }
        };

        String name = "name";
        property.set(target, name);
        assertSame(name, property.get(target));
    }

    @Test
    public void wrapGetExceptionTest() throws Exception {
        Person target = new Person("");
        Field field = target.getClass().getField("name");
        Exception exception = new Exception();

        Property property = new DummyProperty(field) {
            @Override
            protected Object getValue(Object instance) throws Exception {
                throw exception;
            }
        };

        try {
            property.get(target);
            fail("Exception should be thrown!");
        } catch (PropertyException e) {
            assertTrue(e.getMessage().contains("getting"));
            assertTrue(e.getMessage().contains(field.getName()));
            assertTrue(e.getMessage().contains(target.getClass().getName()));
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void wrapSetExceptionTest() throws Exception {
        Person target = new Person("");
        Field field = target.getClass().getField("name");
        Exception exception = new Exception();

        Property property = new DummyProperty(field) {
            @Override
            protected void setValue(Object instance, Object value) throws Exception {
                throw exception;
            }
        };

        try {
            property.set(target, null);
            fail("Exception should be thrown!");
        } catch (PropertyException e) {
            assertTrue(e.getMessage().contains("setting"));
            assertTrue(e.getMessage().contains(field.getName()));
            assertTrue(e.getMessage().contains(target.getClass().getName()));
            assertSame(exception, e.getCause());
        }
    }

}
