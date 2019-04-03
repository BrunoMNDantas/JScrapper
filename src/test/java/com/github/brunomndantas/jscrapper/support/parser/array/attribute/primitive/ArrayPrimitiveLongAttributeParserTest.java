package com.github.brunomndantas.jscrapper.support.parser.array.attribute.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayPrimitiveLongAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
        ArrayPrimitiveLongAttributeParser parser = new ArrayPrimitiveLongAttributeParser(attribute);
        assertSame(long.class, parser.getKlass());
        assertEquals(0L, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayPrimitiveLongAttributeParser parser = new ArrayPrimitiveLongAttributeParser("");

        assertEquals(2L, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}
