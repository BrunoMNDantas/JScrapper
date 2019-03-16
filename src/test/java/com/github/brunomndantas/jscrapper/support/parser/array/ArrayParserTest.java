package com.github.brunomndantas.jscrapper.support.parser.array;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class ArrayParserTest{

    @Test
    public void parseEmptyElementsTest() throws Exception {
        WebDriver driver = new DummyDriver();
        Collection<WebElement> elements = new LinkedList<>();
        boolean[] passed = { true };

        ArrayParser parser = new ArrayParser() {
            @Override
            protected Object parseElement(WebDriver d, WebElement e) {
                passed[0] = false;
                return null;
            }
        };

        Object[] result = (Object[]) parser.parse(driver, elements);
        assertTrue(result.length==0);
        assertTrue(passed[0]);
    }

    @Test
    public void parseSingleElementsTest() throws Exception {
        WebDriver driver = new DummyDriver();
        Collection<WebElement> elements = new LinkedList<>();
        elements.add(new DummyElement());
        boolean[] passed = new boolean[1];

        ArrayParser parser = new ArrayParser() {
            @Override
            protected Object parseElement(WebDriver d, WebElement e) {
                passed[0] = d==driver && e==elements.stream().findFirst().get();
                return e;
            }
        };

        Object[] result = (Object[]) parser.parse(driver, elements);
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

        ArrayParser parser = new ArrayParser() {
            @Override
            protected Object parseElement(WebDriver d, WebElement e) {
                passed[0] = d==driver && (passed[0] ? e==elementB : e==elementA);
                return e;
            }
        };

        Object[] result = (Object[]) parser.parse(driver, elements);
        assertEquals(2, result.length);
        assertSame(elementA, result[0]);
        assertSame(elementB, result[1]);
        assertTrue(passed[0]);
    }

}
