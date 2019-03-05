package com.github.brunomndantas.jscrapper;

import com.github.brunomndantas.jscrapper.core.ScrapperException;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class UtilsTest {

    public static class NoEmptyConstructor {
        public NoEmptyConstructor(String str) { }
    }

    public static class ImplicitEmptyConstructor {

    }

    public static class ExplicitPublicEmptyConstructor {
        public ExplicitPublicEmptyConstructor(String str) { }
        public ExplicitPublicEmptyConstructor() { }
    }

    public static class ExplicitPrivateEmptyConstructor {
        public ExplicitPrivateEmptyConstructor(String str) { }
        private ExplicitPrivateEmptyConstructor() { }
    }

    public static class ExplicitProtectedEmptyConstructor {
        public ExplicitProtectedEmptyConstructor(String str) { }
        protected ExplicitProtectedEmptyConstructor() { }
    }

    public static class ExceptionConstructor {

        public static RuntimeException exception = new RuntimeException();

        public ExceptionConstructor() {
            throw exception;
        }
    }



    @Test
    public void getEmptyConstructorTest() {
        Constructor<?> constructor;

        constructor = Utils.getEmptyConstructor(NoEmptyConstructor.class);
        assertNull(constructor);

        constructor = Utils.getEmptyConstructor(ExplicitPublicEmptyConstructor.class);
        assertNotNull(constructor);
        assertEquals(0, constructor.getParameterCount());

        constructor = Utils.getEmptyConstructor(ExplicitPrivateEmptyConstructor.class);
        assertNotNull(constructor);
        assertEquals(0, constructor.getParameterCount());

        constructor = Utils.getEmptyConstructor(ExplicitProtectedEmptyConstructor.class);
        assertNotNull(constructor);
        assertEquals(0, constructor.getParameterCount());

        constructor = Utils.getEmptyConstructor(ImplicitEmptyConstructor.class);
        assertNotNull(constructor);
        assertEquals(0, constructor.getParameterCount());

        constructor = Utils.getEmptyConstructor(ExceptionConstructor.class);
        assertNotNull(constructor);
        assertEquals(0, constructor.getParameterCount());
    }

    @Test
    public void createWithNoEmptyConstructorTest() {
        try {
            Utils.createInstance(NoEmptyConstructor.class);
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("has no empty constructor"));
        }
    }

    @Test
    public void createWithImplicitConstructorTest() throws ScrapperException {
        assertNotNull(Utils.createInstance(ImplicitEmptyConstructor.class));
    }

    @Test
    public void createWithExplicitPublicConstructorTest() throws ScrapperException {
        assertNotNull(Utils.createInstance(ExplicitPublicEmptyConstructor.class));
    }

    @Test
    public void createWithExplicitPrivateConstructorTest() throws ScrapperException {
        assertNotNull(Utils.createInstance(ExplicitPrivateEmptyConstructor.class));
    }

    @Test
    public void createWithExplicitProtectedConstructorTest() throws ScrapperException {
        assertNotNull(Utils.createInstance(ExplicitProtectedEmptyConstructor.class));
    }

    @Test
    public void wrapsExceptionTest() {
        try {
            Utils.createInstance(ExceptionConstructor.class);
        } catch (ScrapperException e) {
            assertTrue(e.getCause() instanceof InvocationTargetException);
            assertSame(ExceptionConstructor.exception, e.getCause().getCause());
        }
    }

}
