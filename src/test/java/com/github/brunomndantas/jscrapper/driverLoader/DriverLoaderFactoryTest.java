package com.github.brunomndantas.jscrapper.driverLoader;

import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.driverLoader.DriverLoaderException;
import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.driverLoader.annotation.DriverLoader;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class DriverLoaderFactoryTest {

    public static class LoaderWithoutEmptyConstructor implements IDriverLoader {

        public LoaderWithoutEmptyConstructor(String str) { }



        @Override
        public void load(WebDriver driver) throws DriverLoaderException { }

    }

    public static class LoaderWithEmptyConstructor implements IDriverLoader {

        public LoaderWithEmptyConstructor() { }



        @Override
        public void load(WebDriver driver) throws DriverLoaderException { }

    }


    @DriverLoader(LoaderWithEmptyConstructor.class)
    public static class EntityWithDriverLoaderAnnotationOnClass {

    }

    public static class EntityWithDriverLoaderAnnotationOnFlied {

        @DriverLoader(LoaderWithEmptyConstructor.class)
        public String f;

    }

    public static class EntityWithoutDriverLoaderAnnotationOnClass {

    }

    @DriverLoader(LoaderWithEmptyConstructor.class)
    public static class EntityWithoutDriverLoaderAnnotationOnField {

        public String f;

    }

    @DriverLoader(LoaderWithoutEmptyConstructor.class)
    public static class EntityWithNoEmptyConstructorDriverLoaderAnnotationOnClass {

    }

    @DriverLoader(LoaderWithEmptyConstructor.class)
    public static class EntityWithNoEmptyConstructorDriverLoaderAnnotationOnField {

        @DriverLoader(LoaderWithoutEmptyConstructor.class)
        public String f;

    }



    @Test
    public void createEntityWithDriverLoaderAnnotationOnClassTest() throws ScrapperException {
        Class<?> klass = EntityWithDriverLoaderAnnotationOnClass.class;
        IDriverLoader loader = DriverLoaderFactory.create(klass);

        assertNotNull(loader);
        assertTrue(loader instanceof LoaderWithEmptyConstructor);
    }

    @Test
    public void createEntityWithDriverLoaderAnnotationOnFliedTest() throws Exception {
        Class<?> klass = EntityWithDriverLoaderAnnotationOnFlied.class;
        Field field = klass.getDeclaredField("f");
        IDriverLoader loader = DriverLoaderFactory.create(klass, field);

        assertNotNull(loader);
        assertTrue(loader instanceof LoaderWithEmptyConstructor);
    }

    @Test
    public void createEntityWithoutDriverLoaderAnnotationOnClassTest() {
        Class<?> klass = EntityWithoutDriverLoaderAnnotationOnClass.class;

        try {
            DriverLoaderFactory.create(klass);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("found"));
        }
    }

    @Test
    public void createEntityWithoutDriverLoaderAnnotationOnFieldTest() throws Exception {
        Class<?> klass = EntityWithoutDriverLoaderAnnotationOnField.class;
        Field field = klass.getDeclaredField("f");

        try {
            DriverLoaderFactory.create(klass, field);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("found"));
        }
    }

    @Test
    public void createEntityWithNoEmptyConstructorDriverLoaderAnnotationOnClassTest() {
        Class<?> klass = EntityWithNoEmptyConstructorDriverLoaderAnnotationOnClass.class;

        try {
            DriverLoaderFactory.create(klass);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("empty constructor"));
        }
    }

    @Test
    public void createEntityWithNoEmptyConstructorDriverLoaderAnnotationOnFieldTest() throws Exception {
        Class<?> klass = EntityWithNoEmptyConstructorDriverLoaderAnnotationOnField.class;
        Field field = klass.getDeclaredField("f");

        try {
            DriverLoaderFactory.create(klass, field);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("empty constructor"));
        }
    }

}
