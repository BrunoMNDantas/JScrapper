package com.github.brunomndantas.jscrapper.support.parser.array;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class ArrayParserTest {


    @Test
    public void getKlassTest() {
        Class<?> klass = Object.class;
        ArrayParser parser = new ArrayParser(klass) {
            @Override
            protected Object parseElement(WebDriver driver, WebElement element) throws Exception {
                return null;
            }
        };

        assertSame(klass, parser.getKlass());
    }

    @Test
    public void constructorTest() {
        Class<?> klass = Object.class;
        ArrayParser parser = new ArrayParser(klass) {
            @Override
            protected Object parseElement(WebDriver driver, WebElement element) throws Exception {
                return null;
            }
        };

        assertSame(klass, parser.getKlass());
    }

    @Test
    public void parseEmptyElementsTest() throws Exception {
        WebDriver driver = new DummyDriver();
        Collection<WebElement> elements = new LinkedList<>();
        boolean[] passed = { true };

        ArrayParser parser = new ArrayParser(Integer.class) {
            @Override
            protected Object parseElement(WebDriver d, WebElement e) {
                passed[0] = false;
                return null;
            }
        };

        Object[] result = (Object[]) parser.parse(driver, elements);
        assertTrue(result instanceof Integer[]);
        assertTrue(result.length==0);
        assertTrue(passed[0]);
    }

    @Test
    public void parseSingleElementsTest() throws Exception {
        WebDriver driver = new DummyDriver();
        Collection<WebElement> elements = new LinkedList<>();
        elements.add(new DummyElement());
        boolean[] passed = new boolean[1];

        ArrayParser parser = new ArrayParser(WebElement.class) {
            @Override
            protected Object parseElement(WebDriver d, WebElement e) {
                passed[0] = d==driver && e==elements.stream().findFirst().get();
                return e;
            }
        };

        Object[] result = (Object[]) parser.parse(driver, elements);
        assertTrue(result instanceof WebElement[]);
        assertEquals(1, result.length);
        assertSame(elements.stream().findFirst().get(), result[0]);
        assertTrue(passed[0]);
    }

    @Test
    public void parseMultipleElementsTest() throws Exception {
        WebDriver driver = new DummyDriver();
        Collection<WebElement> elements = new LinkedList<>();
        WebElement elementA = new DummyElement();
        WebElement elementB = new DummyElement();
        elements.add(elementA);
        elements.add(elementB);
        boolean[] passed = new boolean[1];

        ArrayParser parser = new ArrayParser(WebElement.class) {
            @Override
            protected Object parseElement(WebDriver d, WebElement e) {
                passed[0] = d==driver && (passed[0] ? e==elementB : e==elementA);
                return e;
            }
        };

        Object[] result = (Object[]) parser.parse(driver, elements);
        assertTrue(result instanceof WebElement[]);
        assertEquals(2, result.length);
        assertSame(elementA, result[0]);
        assertSame(elementB, result[1]);
        assertTrue(passed[0]);
    }

}
