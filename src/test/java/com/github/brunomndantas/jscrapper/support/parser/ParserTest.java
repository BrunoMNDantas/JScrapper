package com.github.brunomndantas.jscrapper.support.parser;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.core.parser.ParserException;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void parseSuccessTest() throws ParserException {
        WebDriver driver = new DummyDriver();
        Collection<WebElement> elements = new LinkedList<>();
        Object value = new Object();
        boolean[] passed = new boolean[1];
        Parser parser = new Parser() {

            protected Object parseElements(WebDriver d, Collection<WebElement> e) throws Exception {
                passed[0] = d==driver && e==elements;
                return value;
            }
        };

        assertSame(value, parser.parse(driver, elements));
        assertTrue(passed[0]);
    }

    @Test
    public void parseWrapsException() {
        Exception exception = new Exception();
        Parser parser = new Parser() {

            protected Object parseElements(WebDriver driver, Collection<WebElement> elements) throws Exception {
                throw exception;
            }
        };

        try {
            parser.parse(new DummyDriver(), new LinkedList<>());
            fail("Exception should be thrown!");
        } catch(Exception e) {
            assertSame(exception, e.getCause());
        }
    }

}
