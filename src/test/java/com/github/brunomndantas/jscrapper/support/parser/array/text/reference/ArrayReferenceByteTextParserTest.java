package com.github.brunomndantas.jscrapper.support.parser.array.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayReferenceByteTextParserTest {

    @Test
    public void constructorTest() {
        ArrayReferenceByteTextParser parser = new ArrayReferenceByteTextParser();
        assertSame(Byte.class, parser.getKlass());
        assertNull( parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayReferenceByteTextParser parser = new ArrayReferenceByteTextParser();

        assertEquals((byte)2, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }
}