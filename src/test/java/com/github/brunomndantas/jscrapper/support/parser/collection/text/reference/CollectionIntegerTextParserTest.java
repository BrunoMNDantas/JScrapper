package com.github.brunomndantas.jscrapper.support.parser.collection.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CollectionIntegerTextParserTest{

    @Test
    public void constructorTest() {
         CollectionIntegerTextParser parser = new  CollectionIntegerTextParser();
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
         CollectionIntegerTextParser parser = new  CollectionIntegerTextParser();

        assertEquals(2, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}
