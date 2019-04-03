package com.github.brunomndantas.jscrapper.support.parser.array.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayReferenceShortTextParserTest {

    @Test
    public void constructorTest() {
        ArrayReferenceShortTextParser parser = new ArrayReferenceShortTextParser();
        assertSame(Short.class, parser.getKlass());
        assertNull( parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayReferenceShortTextParser parser = new ArrayReferenceShortTextParser();

        assertEquals((short)2, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}