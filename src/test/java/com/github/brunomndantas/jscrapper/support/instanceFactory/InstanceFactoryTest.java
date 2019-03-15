package com.github.brunomndantas.jscrapper.support.instanceFactory;

import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

public class InstanceFactoryTest {

    @Test
    public void createSuccessTest() throws InstanceFactoryException {
        Object instance = new Object();
        InstanceFactory factory = new InstanceFactory() {
            @Override
            protected Object createInstance() throws Exception {
                return instance;
            }
        };

        assertSame(instance, factory.create());
    }

    @Test
    public void createWrapsException() {
        Exception exception = new Exception();
        InstanceFactory factory = new InstanceFactory() {
            @Override
            protected Object createInstance() throws Exception {
                throw exception;
            }
        };

        try {
            factory.create();
            fail("Exception should be thrown!");
        } catch(Exception e) {
            assertSame(exception, e.getCause());
        }
    }

}
