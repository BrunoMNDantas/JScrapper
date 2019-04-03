package com.github.brunomndantas.jscrapper.support.parser.array.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayPrimitiveBooleanTextParserTest {

    @Test
    public void constructorTest() {
        ArrayPrimitiveBooleanTextParser parser = new ArrayPrimitiveBooleanTextParser();
        assertSame(boolean.class, parser.getKlass());
        assertEquals(false, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayPrimitiveBooleanTextParser parser = new ArrayPrimitiveBooleanTextParser();

        assertEquals(true, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "true";
            }
        }));
    }

}
