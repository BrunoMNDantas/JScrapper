package com.github.brunomndantas.jscrapper.support.parser.array.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayReferenceIntegerTextParserTest {

    @Test
    public void constructorTest() {
        ArrayReferenceIntegerTextParser parser = new ArrayReferenceIntegerTextParser();
        assertSame(Integer.class, parser.getKlass());
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayReferenceIntegerTextParser parser = new ArrayReferenceIntegerTextParser();

        assertEquals(2, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}
