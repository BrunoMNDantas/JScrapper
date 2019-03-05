package com.github.brunomndantas.jscrapper.selector;

import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.selector.ISelector;
import com.github.brunomndantas.jscrapper.core.selector.SelectorException;
import com.github.brunomndantas.jscrapper.selector.annotation.Selector;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.Collection;

import static org.junit.Assert.*;

public class ElementFactoryTest {

    public static class SelectorWithoutEmptyConstructor implements ISelector {

        public SelectorWithoutEmptyConstructor(String str) { }



        @Override
        public Collection<WebElement> select(WebDriver driver) throws SelectorException { return null; }

    }

    public static class SelectorWithEmptyConstructor implements ISelector {

        public SelectorWithEmptyConstructor() { }



        @Override
        public Collection<WebElement> select(WebDriver driver) throws SelectorException { return null; }
    }


    public static class EntityWithSelectorAnnotation {

        @Selector(SelectorWithEmptyConstructor.class)
        public String f;

    }

    public static class EntityWithoutSelectorAnnotation {

        public String f;

    }

    public static class EntityWithNoEmptyConstructorSelectorAnnotation {

        @Selector(SelectorWithoutEmptyConstructor.class)
        public String f;

    }



    @Test
    public void createEntityWithSelectorAnnotationTest() throws Exception {
        Class<?> klass = EntityWithSelectorAnnotation.class;
        Field field = klass.getDeclaredField("f");
        ISelector selector = SelectorFactory.create(klass, field);

        assertNotNull(selector);
        assertTrue(selector instanceof SelectorWithEmptyConstructor);
    }

    @Test
    public void createEntityWithoutSelectorAnnotationTest() throws Exception {
        Class<?> klass = EntityWithoutSelectorAnnotation.class;
        Field field = klass.getDeclaredField("f");

        try {
            SelectorFactory.create(klass, field);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("found"));
        }
    }

    @Test
    public void createEntityWithNoEmptyConstructorSelectorAnnotationTest() throws Exception {
        Class<?> klass = EntityWithNoEmptyConstructorSelectorAnnotation.class;
        Field field = klass.getDeclaredField("f");

        try {
            SelectorFactory.create(klass, field);
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("empty constructor"));
        }
    }

}
