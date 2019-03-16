package com.github.brunomndantas.jscrapper.support.parser.array.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayCharacterTextParserTest{

    @Test
    public void constructorTest() {
        ArrayCharacterTextParser parser = new ArrayCharacterTextParser();
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayCharacterTextParser parser = new ArrayCharacterTextParser();

        assertEquals('\u0001', parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return ((char)1) + "";
            }
        }));
    }

}