package com.github.brunomndantas.jscrapper.support.parser.array.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayReferenceStringTextParserTest {
    @Test
    public void constructorTest() {
        ArrayReferenceStringTextParser parser = new ArrayReferenceStringTextParser();
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayReferenceStringTextParser parser = new ArrayReferenceStringTextParser();

        assertEquals("text", parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "text";
            }
        }));
    }


}
