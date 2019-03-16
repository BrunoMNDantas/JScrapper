package com.github.brunomndantas.jscrapper.support.parser.collection.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CollectionStringTextParserTest{

    @Test
    public void constructorTest() {
        CollectionStringTextParser parser = new  CollectionStringTextParser();
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        CollectionStringTextParser parser = new  CollectionStringTextParser();

        assertEquals("text", parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "text";
            }
        }));
    }

}
