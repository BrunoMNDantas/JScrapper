package com.github.brunomndantas.jscrapper.support.parser.collection.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CollectionFloatTextParserTest{

    @Test
    public void constructorTest() {
         CollectionFloatTextParser parser = new  CollectionFloatTextParser();
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
         CollectionFloatTextParser parser = new  CollectionFloatTextParser();

        assertEquals(2f, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}
