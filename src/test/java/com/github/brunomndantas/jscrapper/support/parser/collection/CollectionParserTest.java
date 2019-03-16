package com.github.brunomndantas.jscrapper.support.parser.collection;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class CollectionParserTest{

    @Test
    public void parseEmptyElementsTest() throws Exception {
        WebDriver driver = new DummyDriver();
        Collection<WebElement> elements = new LinkedList<>();
        boolean[] passed = { true };

        CollectionParser parser = new CollectionParser() {
            @Override
            protected Object parseElement(WebDriver d, WebElement e) {
                passed[0] = false;
                return null;
            }
        };

        Collection<Object> result = (Collection<Object>) parser.parse(driver, elements);
        assertTrue(result.isEmpty());
        assertTrue(passed[0]);
    }

    @Test
    public void parseSingleElementsTest() throws Exception {
        WebDriver driver = new DummyDriver();
        Collection<WebElement> elements = new LinkedList<>();
        elements.add(new DummyElement());
        boolean[] passed = new boolean[1];

        CollectionParser parser = new CollectionParser() {
            @Override
            protected Object parseElement(WebDriver d, WebElement e) {
                passed[0] = d==driver && e==elements.stream().findFirst().get();
                return e;
            }
        };

        Collection<Object> result = (Collection<Object>) parser.parse(driver, elements);
        assertEquals(1, result.size());
        assertSame(elements.stream().findFirst().get(), result.stream().findFirst().get());
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

        CollectionParser parser = new CollectionParser() {
            @Override
            protected Object parseElement(WebDriver d, WebElement e) {
                passed[0] = d==driver && (passed[0] ? e==elementB : e==elementA);
                return e;
            }
        };

        Collection<Object> result = (Collection<Object>) parser.parse(driver, elements);
        assertEquals(2, result.size());
        assertSame(elementA, result.stream().findFirst().get());
        assertSame(elementB, result.stream().skip(1).findFirst().get());
        assertTrue(passed[0]);
    }

}
