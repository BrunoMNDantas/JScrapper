package com.github.brunomndantas.jscrapper.support.parser.array.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayPrimitiveIntegerTextParserTest {

    @Test
    public void constructorTest() {
        ArrayPrimitiveIntegerTextParser parser = new ArrayPrimitiveIntegerTextParser();
        assertSame(int.class, parser.getKlass());
        assertEquals(0, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayPrimitiveIntegerTextParser parser = new ArrayPrimitiveIntegerTextParser();

        assertEquals(2, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}
