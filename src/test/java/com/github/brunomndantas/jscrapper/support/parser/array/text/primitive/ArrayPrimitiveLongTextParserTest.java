package com.github.brunomndantas.jscrapper.support.parser.array.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayPrimitiveLongTextParserTest {

    @Test
    public void constructorTest() {
        ArrayPrimitiveLongTextParser parser = new ArrayPrimitiveLongTextParser();
        assertEquals(0L, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayPrimitiveLongTextParser parser = new ArrayPrimitiveLongTextParser();

        assertEquals(2L, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}
