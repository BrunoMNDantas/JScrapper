package com.github.brunomndantas.jscrapper.support.parser.array.attribute;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class ArrayAttributeParserTest{

    @Test
    public void getAttributeTest() {
        String attribute = "";
        ArrayAttributeParser parser = new ArrayAttributeParser(Integer.class, attribute, null) {
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
        ArrayAttributeParser parser = new ArrayAttributeParser(Integer.class, null, defaultValue) {
            @Override
            protected Object parseElement(WebElement element) throws Exception {
                return null;
            }
        };

        assertSame(defaultValue, parser.getDefaultValue());
    }

    @Test
    public void constructorTest() {
        Class<?> klass = Integer.class;
        String attribute = "";
        Object defaultValue = new Object();
        ArrayAttributeParser parser = new ArrayAttributeParser(klass, attribute, defaultValue) {
            @Override
            protected Object parseElement(WebElement element) throws Exception {
                return null;
            }
        };

        assertSame(klass, parser.getKlass());
        assertSame(attribute, parser.getAttribute());
        assertSame(defaultValue, parser.getDefaultValue());
    }

    @Test
    public void parseNullElementTest() throws Exception {
        Object defaultValue = new Object();
        ArrayAttributeParser parser = new ArrayAttributeParser(Integer.class, null, defaultValue) {
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
        ArrayAttributeParser parser = new ArrayAttributeParser(Integer.class, null, defaultValue) {
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
        ArrayAttributeParser parser = new ArrayAttributeParser(Integer.class, attribute, defaultValue) {
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
