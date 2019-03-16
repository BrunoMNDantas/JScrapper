package com.github.brunomndantas.jscrapper.support.parser.collection.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CollectionDoubleTextParserTest{

    @Test
    public void constructorTest() {
         CollectionDoubleTextParser parser = new  CollectionDoubleTextParser();
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
         CollectionDoubleTextParser parser = new  CollectionDoubleTextParser();

        assertEquals(2d, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}