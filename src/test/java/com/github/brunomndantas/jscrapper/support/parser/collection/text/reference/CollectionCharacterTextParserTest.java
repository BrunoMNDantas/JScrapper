package com.github.brunomndantas.jscrapper.support.parser.collection.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CollectionCharacterTextParserTest{

    @Test
    public void constructorTest() {
         CollectionCharacterTextParser parser = new  CollectionCharacterTextParser();
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
         CollectionCharacterTextParser parser = new  CollectionCharacterTextParser();

        assertEquals('\u0001', parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return ((char)1) + "";
            }
        }));
    }

}