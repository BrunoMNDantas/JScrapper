package com.github.brunomndantas.jscrapper.support.property;

import com.github.brunomndantas.jscrapper.core.property.PropertyException;
import org.junit.Test;

import static org.junit.Assert.*;

public class PropertyTest {

    @Test
    public void getSuccessTest() throws PropertyException {
        Object instance = new Object();
        Object value = new Object();
        boolean[] passed = new boolean[1];
        Property property = new Property() {
            @Override
            protected Object getValue(Object i) throws Exception {
                passed[0] = i==instance;
                return value;
            }

            @Override
            protected void setValue(Object instance, Object value) throws Exception { }
        };

        assertSame(value, property.get(instance));
        assertTrue(passed[0]);
    }

    @Test
    public void getWrapsException() {
        Exception exception = new Exception();
        Property property = new Property() {
            @Override
            protected Object getValue(Object instance) throws Exception {
                throw exception;
            }

            @Override
            protected void setValue(Object instance, Object value) throws Exception {
                throw exception;
            }
        };

        try {
            property.get(new Object());
            fail("Exception should be thrown!");
        } catch(Exception e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void setSuccessTest() throws PropertyException {
        Object instance = new Object();
        Object value = new Object();
        boolean[] passed = new boolean[1];
        Property property = new Property() {
            @Override
            protected Object getValue(Object i) throws Exception { return null; }

            @Override
            protected void setValue(Object i, Object v) throws Exception {
                passed[0] = i==instance && v==value;
            }
        };

        property.set(instance, value);
        assertTrue(passed[0]);
    }

    @Test
    public void setWrapsException() {
        Exception exception = new Exception();
        Property property = new Property() {
            @Override
            protected Object getValue(Object instance) throws Exception {
                throw exception;
            }

            @Override
            protected void setValue(Object instance, Object value) throws Exception {
                throw exception;
            }
        };

        try {
            property.set(new Object(), new Object());
            fail("Exception should be thrown!");
        } catch(Exception e) {
            assertSame(exception, e.getCause());
        }
    }

}
