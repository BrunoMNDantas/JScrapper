package com.github.brunomndantas.jscrapper.instanceFactory;

import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmptyConstructorInstanceFactoryTest {

    public static class NoEmptyConstructor {
        public NoEmptyConstructor(String str) { }
    }

    public static class EmptyConstructor {

    }

    public static class ExceptionConstructor {

        public static RuntimeException exception = new RuntimeException();

        public ExceptionConstructor() {
            throw exception;
        }
    }



    @Test
    public void createInstanceWithoutEmptyConstructor() {
        Class<?> klass = NoEmptyConstructor.class;
        EmptyConstructorInstanceFactory factory = new EmptyConstructorInstanceFactory(klass);

        try {
            factory.create();
        } catch (Exception e) {
            assertTrue(e.getCause().getMessage().contains("empty constructor"));
        }
    }

    @Test
    public void createInstanceWithEmptyConstructor() throws InstanceFactoryException {
        Class<EmptyConstructor> klass = EmptyConstructor.class;
        EmptyConstructorInstanceFactory factory = new EmptyConstructorInstanceFactory(klass);

        EmptyConstructor instance = (EmptyConstructor) factory.create();
        assertNotNull(instance);
    }

    @Test
    public void createInstanceWithExceptionConstructor() {
        Class<?> klass = ExceptionConstructor.class;
        EmptyConstructorInstanceFactory factory = new EmptyConstructorInstanceFactory(klass);

        try {
            factory.create();
        } catch (Exception e) {
            assertSame(ExceptionConstructor.exception, e.getCause().getCause().getCause());
        }
    }

}
