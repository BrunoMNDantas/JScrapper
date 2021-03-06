package com.github.brunomndantas.jscrapper.support.parser.collection.text;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertSame;

public class CollectionTextParserTest{

    @Test
    public void getDefaultValueTest() {
        Object defaultValue = new Object();
        CollectionTextParser parser = new CollectionTextParser(defaultValue) {
            @Override
            protected Object parseElement(WebElement element) throws Exception {
                return null;
            }
        };

        assertSame(defaultValue, parser.getDefaultValue());
    }

    @Test
    public void constructorTest() {
        Object defaultValue = new Object();
        CollectionTextParser parser = new CollectionTextParser(defaultValue) {
            @Override
            protected Object parseElement(WebElement element) throws Exception {
                return null;
            }
        };

        assertSame(defaultValue, parser.getDefaultValue());
    }

    @Test
    public void parseNullElementTest() throws Exception {
        Object defaultValue = new Object();
        CollectionTextParser parser = new CollectionTextParser(defaultValue) {
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
        CollectionTextParser parser = new CollectionTextParser(defaultValue) {
            @Override
            protected Object parseElement(WebElement element) throws Exception {
                return null;
            }
        };

        assertSame(defaultValue, parser.parseElement(null, new DummyElement(){
            @Override
            public String getText() {
                return null;
            }
        }));
    }

    @Test
    public void parseElementTest() throws Exception {
        Object defaultValue = new Object();
        Object value = new Object();
        CollectionTextParser parser = new CollectionTextParser(defaultValue) {
            @Override
            protected Object parseElement(WebElement element) throws Exception {
                return value;
            }
        };

        assertSame(value, parser.parseElement(null, new DummyElement(){
            @Override
            public String getText() {
                return "";
            }
        }));
    }

}
