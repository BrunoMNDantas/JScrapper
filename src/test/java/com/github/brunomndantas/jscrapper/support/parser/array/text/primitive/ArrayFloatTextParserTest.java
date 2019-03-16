package com.github.brunomndantas.jscrapper.support.parser.array.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayFloatTextParserTest{

    @Test
    public void constructorTest() {
        ArrayFloatTextParser parser = new ArrayFloatTextParser();
        assertEquals(0.0f, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayFloatTextParser parser = new ArrayFloatTextParser();

        assertEquals(2f, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}
