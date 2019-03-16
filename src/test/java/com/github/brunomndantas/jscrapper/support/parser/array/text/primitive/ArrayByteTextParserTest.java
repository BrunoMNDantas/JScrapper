package com.github.brunomndantas.jscrapper.support.parser.array.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayByteTextParserTest{

    @Test
    public void constructorTest() {
        ArrayByteTextParser parser = new ArrayByteTextParser();
        assertEquals((byte)0, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayByteTextParser parser = new ArrayByteTextParser();

        assertEquals((byte)2, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}