package com.github.brunomndantas.jscrapper.support.elementLoader;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.core.elementLoader.ElementLoaderException;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class ElementLoaderTest {

    @Test
    public void loadSuccessTest() throws ElementLoaderException {
        WebDriver driver = new DummyDriver();
        Collection<WebElement> elements = new LinkedList<>();
        boolean[] passed = new boolean[1];
        ElementLoader loader = new ElementLoader() {
            @Override
            protected void loadElements(WebDriver d, Collection<WebElement> e) throws Exception {
                passed[0] = d==driver && e==elements;
            }
        };

        loader.load(driver, elements);
        assertTrue(passed[0]);
    }

    @Test
    public void loadWrapsException() {
        Exception exception = new Exception();
        ElementLoader loader = new ElementLoader() {
            @Override
            protected void loadElements(WebDriver driver, Collection<WebElement> elements) throws Exception {
                throw exception;
            }
        };

        try {
            loader.load(new DummyDriver(), new LinkedList<>());
            fail("Exception should be thrown!");
        } catch(Exception e) {
            assertSame(exception, e.getCause());
        }
    }

}
