package com.github.brunomndantas.jscrapper.support.parser.array.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayStringTextParserTest{
    @Test
    public void constructorTest() {
        ArrayStringTextParser parser = new ArrayStringTextParser();
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayStringTextParser parser = new ArrayStringTextParser();

        assertEquals("text", parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "text";
            }
        }));
    }


}
