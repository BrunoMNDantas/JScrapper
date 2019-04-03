package com.github.brunomndantas.jscrapper.support.selector;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.core.selector.SelectorException;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class SelectorTest {

    @Test
    public void selectSuccessTest() throws SelectorException {
        WebDriver driver = new DummyDriver();
        Collection<WebElement> elements = new LinkedList<>();
        boolean[] passed = new boolean[1];
        Selector selector = new Selector() {
            @Override
            protected Collection<WebElement> selectElements(WebDriver d) throws Exception {
                passed[0] = d == driver;
                return elements;
            }
        };

        assertSame(elements, selector.select(driver));
        assertTrue(passed[0]);
    }

    @Test
    public void selectWrapsException() {
        Exception exception = new Exception();
        Selector selector = new Selector() {
            @Override
            protected Collection<WebElement> selectElements(WebDriver driver) throws Exception {
                throw  exception;
            }
        };

        try {
            selector.select(new DummyDriver());
            fail("Exception should be thrown!");
        } catch(Exception e) {
            assertSame(exception, e.getCause());
        }
    }

}
