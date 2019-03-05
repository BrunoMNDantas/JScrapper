package com.github.brunomndantas.jscrapper.elementLoader;

import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.elementLoader.ElementLoaderException;
import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;
import com.github.brunomndantas.jscrapper.elementLoader.annotation.ElementLoader;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.Collection;

import static org.junit.Assert.*;

public class ElementLoaderFactoryTest {

    public static class LoaderWithoutEmptyConstructor implements IElementLoader {

        public LoaderWithoutEmptyConstructor(String str) { }



        @Override
        public void load(WebDriver driver, Collection<WebElement> elements) throws ElementLoaderException { }

    }

    public static class LoaderWithEmptyConstructor implements IElementLoader {

        public LoaderWithEmptyConstructor() { }



        @Override
        public void load(WebDriver driver, Collection<WebElement> elements) throws ElementLoaderException { }

    }



    public static class EntityWithElementLoaderAnnotation {

        @ElementLoader(LoaderWithEmptyConstructor.class)
        public String f;

    }

    public static class EntityWithoutElementLoaderAnnotation {

        public String f;

    }

    public static class EntityWithNoEmptyConstructorElementLoaderAnnotation {

        @ElementLoader(LoaderWithoutEmptyConstructor.class)
        public String f;

    }



    @Test
    public void createEntityWithElementLoaderAnnotationTest() throws Exception {
        Class<?> klass = EntityWithElementLoaderAnnotation.class;
        Field field = klass.getDeclaredField("f");
        IElementLoader loader = ElementLoaderFactory.create(klass, field);

        assertNotNull(loader);
        assertTrue(loader instanceof LoaderWithEmptyConstructor);
    }

    @Test
    public void createEntityWithoutElementLoaderAnnotationTest() throws Exception {
        Class<?> klass = EntityWithoutElementLoaderAnnotation.class;
        Field field = klass.getDeclaredField("f");

        try {
            ElementLoaderFactory.create(klass, field);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("found"));
        }
    }

    @Test
    public void createEntityWithNoEmptyConstructorElementLoaderAnnotationTest() throws Exception {
        Class<?> klass = EntityWithNoEmptyConstructorElementLoaderAnnotation.class;
        Field field = klass.getDeclaredField("f");

        try {
            ElementLoaderFactory.create(klass, field);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("empty constructor"));
        }
    }

}
