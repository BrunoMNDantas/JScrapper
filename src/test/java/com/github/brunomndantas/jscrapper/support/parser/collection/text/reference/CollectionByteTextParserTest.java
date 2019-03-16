package com.github.brunomndantas.jscrapper.support.parser.collection.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CollectionByteTextParserTest{

    @Test
    public void constructorTest() {
         CollectionByteTextParser parser = new  CollectionByteTextParser();
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
         CollectionByteTextParser parser = new  CollectionByteTextParser();

        assertEquals((byte)2, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}