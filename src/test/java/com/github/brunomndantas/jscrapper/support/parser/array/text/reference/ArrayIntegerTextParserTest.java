package com.github.brunomndantas.jscrapper.support.parser.array.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayIntegerTextParserTest{

    @Test
    public void constructorTest() {
        ArrayIntegerTextParser parser = new ArrayIntegerTextParser();
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayIntegerTextParser parser = new ArrayIntegerTextParser();

        assertEquals(2, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}
