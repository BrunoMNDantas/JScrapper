package com.github.brunomndantas.jscrapper.support.parser.collection.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CollectionShortTextParserTest{

    @Test
    public void constructorTest() {
         CollectionShortTextParser parser = new  CollectionShortTextParser();
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
         CollectionShortTextParser parser = new  CollectionShortTextParser();

        assertEquals((short)2, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}