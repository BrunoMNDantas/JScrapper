package com.github.brunomndantas.jscrapper.support.parser.array.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayDoubleTextParserTest{

    @Test
    public void constructorTest() {
        ArrayDoubleTextParser parser = new ArrayDoubleTextParser();
        assertEquals(0.0d, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayDoubleTextParser parser = new ArrayDoubleTextParser();

        assertEquals(2d, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}