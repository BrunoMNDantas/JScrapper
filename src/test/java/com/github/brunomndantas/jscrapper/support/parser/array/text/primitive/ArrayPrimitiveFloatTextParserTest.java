package com.github.brunomndantas.jscrapper.support.parser.array.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayPrimitiveFloatTextParserTest {

    @Test
    public void constructorTest() {
        ArrayPrimitiveFloatTextParser parser = new ArrayPrimitiveFloatTextParser();
        assertSame(float.class, parser.getKlass());
        assertEquals(0.0f, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayPrimitiveFloatTextParser parser = new ArrayPrimitiveFloatTextParser();

        assertEquals(2f, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}
