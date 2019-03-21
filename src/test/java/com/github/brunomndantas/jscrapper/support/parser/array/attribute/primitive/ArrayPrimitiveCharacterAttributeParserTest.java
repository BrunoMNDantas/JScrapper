package com.github.brunomndantas.jscrapper.support.parser.array.attribute.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayPrimitiveCharacterAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
        ArrayPrimitiveCharacterAttributeParser parser = new ArrayPrimitiveCharacterAttributeParser(attribute);
        assertEquals('\u0000', parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayPrimitiveCharacterAttributeParser parser = new ArrayPrimitiveCharacterAttributeParser("");

        assertEquals('\u0001', parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return ((char)1) + "";
            }
        }));
    }

}
