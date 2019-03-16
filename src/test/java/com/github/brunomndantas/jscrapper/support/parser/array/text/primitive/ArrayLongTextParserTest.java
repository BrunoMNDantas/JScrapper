package com.github.brunomndantas.jscrapper.support.parser.array.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayLongTextParserTest{

    @Test
    public void constructorTest() {
        ArrayLongTextParser parser = new ArrayLongTextParser();
        assertEquals(0L, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayLongTextParser parser = new ArrayLongTextParser();

        assertEquals(2L, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}
