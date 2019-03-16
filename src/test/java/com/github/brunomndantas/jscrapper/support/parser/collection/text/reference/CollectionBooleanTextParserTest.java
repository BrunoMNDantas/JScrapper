package com.github.brunomndantas.jscrapper.support.parser.collection.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CollectionBooleanTextParserTest{

    @Test
    public void constructorTest() {
         CollectionBooleanTextParser parser = new  CollectionBooleanTextParser();
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
         CollectionBooleanTextParser parser = new  CollectionBooleanTextParser();

        assertEquals(true, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "true";
            }
        }));
    }
}
