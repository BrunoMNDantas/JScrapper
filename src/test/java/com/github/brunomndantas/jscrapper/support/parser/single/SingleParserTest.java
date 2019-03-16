package com.github.brunomndantas.jscrapper.support.parser.single;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SingleParserTest {

    @Test
    public void parseEmptyElementsTest() throws Exception {
        WebDriver driver = new DummyDriver();
        Collection<WebElement> elements = new LinkedList<>();
        boolean[] passed = new boolean[1];
        Object value = new Object();

        SingleParser parser = new SingleParser() {
            @Override
            protected Object parseElement(WebDriver d, WebElement e) {
                passed[0] = d==driver && e==null;
                return value;
            }
        };

        assertSame(value, parser.parse(driver, elements));
        assertTrue(passed[0]);
    }

    @Test
    public void parseNotEmptyElementsTest() throws Exception {
        WebDriver driver = new DummyDriver();
        Collection<WebElement> elements = new LinkedList<>();
        elements.add(new DummyElement());
        boolean[] passed = new boolean[1];
        Object value = new Object();

        SingleParser parser = new SingleParser() {
            @Override
            protected Object parseElement(WebDriver d, WebElement e) {
                passed[0] = d==driver && e==elements.stream().findFirst().get();
                return value;
            }
        };

        assertSame(value, parser.parse(driver, elements));
        assertTrue(passed[0]);
    }

}
