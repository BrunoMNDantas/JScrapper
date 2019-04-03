package com.github.brunomndantas.jscrapper.support.parser.array.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayReferenceFloatTextParserTest {

    @Test
    public void constructorTest() {
        ArrayReferenceFloatTextParser parser = new ArrayReferenceFloatTextParser();
        assertSame(Float.class, parser.getKlass());
        assertNull( parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayReferenceFloatTextParser parser = new ArrayReferenceFloatTextParser();

        assertEquals(2f, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}
