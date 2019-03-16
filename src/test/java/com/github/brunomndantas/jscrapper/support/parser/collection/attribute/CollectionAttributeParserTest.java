package com.github.brunomndantas.jscrapper.support.parser.collection.attribute;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class CollectionAttributeParserTest{

    @Test
    public void getAttributeTest() {
        String attribute = "";
        CollectionAttributeParser parser = new CollectionAttributeParser(attribute, null) {
            @Override
            protected Object parseElement(WebElement element) throws Exception {
                return null;
            }
        };

        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void getDefaultValueTest() {
        Object defaultValue = new Object();
        CollectionAttributeParser parser = new CollectionAttributeParser(null, defaultValue) {
            @Override
            protected Object parseElement(WebElement element) throws Exception {
                return null;
            }
        };

        assertSame(defaultValue, parser.getDefaultValue());
    }

    @Test
    public void constructorTest() {
        String attribute = "";
        Object defaultValue = new Object();
        CollectionAttributeParser parser = new CollectionAttributeParser(attribute, defaultValue) {
            @Override
            protected Object parseElement(WebElement element) throws Exception {
                return null;
            }
        };

        assertSame(attribute, parser.getAttribute());
        assertSame(defaultValue, parser.getDefaultValue());
    }

    @Test
    public void parseNullElementTest() throws Exception {
        Object defaultValue = new Object();
        CollectionAttributeParser parser = new CollectionAttributeParser(null, defaultValue) {
            @Override
            protected Object parseElement(WebElement element) throws Exception {
                return null;
            }
        };

        assertSame(defaultValue, parser.parseElement(null, null));
    }

    @Test
    public void parseNullTestElementTest() throws Exception {
        Object defaultValue = new Object();
        CollectionAttributeParser parser = new CollectionAttributeParser(null, defaultValue) {
            @Override
            protected Object parseElement(WebElement element) throws Exception {
                return null;
            }
        };

        assertSame(defaultValue, parser.parseElement(null, new DummyElement(){
            @Override
            public String getAttribute(String attr) {
                return null;
            }
        }));
    }

    @Test
    public void parseElementTest() throws Exception {
        String attribute = "";
        Object defaultValue = new Object();
        Object value = new Object();
        boolean[] passed = new boolean[1];
        CollectionAttributeParser parser = new CollectionAttributeParser(attribute, defaultValue) {
            @Override
            protected Object parseElement(WebElement element) throws Exception {
                return value;
            }
        };

        assertSame(value, parser.parseElement(null, new DummyElement(){
            @Override
            public String getAttribute(String attr) {
                passed[0] = attr==attribute;
                return "";
            }
        }));
        assertTrue(passed[0]);
    }

}
