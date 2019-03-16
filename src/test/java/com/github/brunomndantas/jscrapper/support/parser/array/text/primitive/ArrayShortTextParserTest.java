package com.github.brunomndantas.jscrapper.support.parser.array.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayShortTextParserTest{

    @Test
    public void constructorTest() {
        ArrayShortTextParser parser = new ArrayShortTextParser();
        assertEquals((short)0, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayShortTextParser parser = new ArrayShortTextParser();

        assertEquals((short)2, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}