package com.github.brunomndantas.jscrapper.support.parser.array.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayPrimitiveDoubleTextParserTest {

    @Test
    public void constructorTest() {
        ArrayPrimitiveDoubleTextParser parser = new ArrayPrimitiveDoubleTextParser();
        assertSame(double.class, parser.getKlass());
        assertEquals(0.0d, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayPrimitiveDoubleTextParser parser = new ArrayPrimitiveDoubleTextParser();

        assertEquals(2d, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}