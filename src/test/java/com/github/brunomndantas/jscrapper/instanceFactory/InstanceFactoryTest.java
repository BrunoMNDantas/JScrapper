package com.github.brunomndantas.jscrapper.instanceFactory;

import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class InstanceFactoryTest {

    @Test
    public void getKlassTest() {
        Class<?> klass = Object.class;
        InstanceFactory factory = new InstanceFactory(klass) {
            @Override protected Object createInstance() { return null; }
        };

        assertSame(klass, factory.getKlass());
    }

    @Test
    public void constructorTest() {
        Class<?> klass = Object.class;
        InstanceFactory factory = new InstanceFactory(klass) {
            @Override protected Object createInstance() { return null; }
        };

        assertSame(klass, factory.getKlass());
    }

    @Test
    public void wrapExceptionTest() {
        Exception exception = new Exception();
        InstanceFactory factory = new InstanceFactory(Object.class) {
            @Override
            protected Object createInstance() throws Exception {
                throw exception;
            }
        };

        try {
            factory.create();
        } catch (InstanceFactoryException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void createTest() throws InstanceFactoryException {
        Class<?> klass = Object.class;
        Object instance = new Object();

        InstanceFactory factory = new InstanceFactory(klass) {
            @Override
            protected Object createInstance() {
                return instance;
            }
        };

        assertSame(instance, factory.create());
    }

}
