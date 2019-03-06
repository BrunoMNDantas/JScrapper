package com.github.brunomndantas.jscrapper.instanceFactory;

import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class InstanceFactoryTest {

    @Test
    public void wrapExceptionTest() {
        Exception exception = new Exception();
        InstanceFactory factory = new InstanceFactory() {
            @Override
            protected <T> T createInstance(Class<T> klass) throws Exception {
                throw exception;
            }
        };

        try {
            factory.create(Object.class);
        } catch (InstanceFactoryException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void createTest() throws InstanceFactoryException {
        Class<?> klass = Object.class;
        Object instance = new Object();
        boolean[] passed = new boolean[1];

        InstanceFactory factory = new InstanceFactory() {
            @Override
            protected <T> T createInstance(Class<T> k) throws Exception {
                passed[0] = k == klass;
                return (T)instance;
            }
        };

        assertSame(instance, factory.create(klass));
        assertTrue(passed[0]);
    }

}
