package com.github.brunomndantas.jscrapper.support.parser.array.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayPrimitiveByteTextParserTest {

    @Test
    public void constructorTest() {
        ArrayPrimitiveByteTextParser parser = new ArrayPrimitiveByteTextParser();
        assertSame(byte.class, parser.getKlass());
        assertEquals((byte)0, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayPrimitiveByteTextParser parser = new ArrayPrimitiveByteTextParser();

        assertEquals((byte)2, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}