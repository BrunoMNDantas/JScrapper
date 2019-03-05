package com.github.brunomndantas.jscrapper.parser;

import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.parser.IParser;
import com.github.brunomndantas.jscrapper.core.parser.ParserException;
import com.github.brunomndantas.jscrapper.parser.annotation.Parser;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.Collection;

import static org.junit.Assert.*;

public class ParserFactoryTest {

    public static class ParserWithoutEmptyConstructor implements IParser {

        public ParserWithoutEmptyConstructor(String str) { }



        @Override
        public Object parse(WebDriver driver, Collection<WebElement> elements) throws ParserException { return null; }

    }

    public static class ParserWithEmptyConstructor implements IParser {

        public ParserWithEmptyConstructor() { }



        @Override
        public Object parse(WebDriver driver, Collection<WebElement> elements) throws ParserException { return null; }

    }



    public static class EntityWithParserAnnotation {

        @Parser(ParserWithEmptyConstructor.class)
        public String f;

    }

    public static class EntityWithoutParserAnnotation {

        public String f;

    }

    public static class EntityWithNoEmptyConstructorParserAnnotation {

        @Parser(ParserWithoutEmptyConstructor.class)
        public String f;

    }



    @Test
    public void createEntityWithParserAnnotationTest() throws Exception {
        Class<?> klass = EntityWithParserAnnotation.class;
        Field field = klass.getDeclaredField("f");
        IParser parser = ParserFactory.create(klass, field);

        assertNotNull(parser);
        assertTrue(parser instanceof ParserWithEmptyConstructor);
    }

    @Test
    public void createEntityWithoutParserAnnotationTest() throws Exception {
        Class<?> klass = EntityWithoutParserAnnotation.class;
        Field field = klass.getDeclaredField("f");

        try {
            ParserFactory.create(klass, field);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("found"));
        }
    }

    @Test
    public void createEntityWithNoEmptyConstructorParserAnnotationTest() throws Exception {
        Class<?> klass = EntityWithNoEmptyConstructorParserAnnotation.class;
        Field field = klass.getDeclaredField("f");

        try {
            ParserFactory.create(klass, field);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("empty constructor"));
        }
    }

}
