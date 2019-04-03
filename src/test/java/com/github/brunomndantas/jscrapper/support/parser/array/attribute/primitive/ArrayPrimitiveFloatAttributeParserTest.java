package com.github.brunomndantas.jscrapper.support.parser.array.attribute.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayPrimitiveFloatAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
        ArrayPrimitiveFloatAttributeParser parser = new ArrayPrimitiveFloatAttributeParser(attribute);
        assertSame(float.class, parser.getKlass());
        assertEquals(0.0f, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayPrimitiveFloatAttributeParser parser = new ArrayPrimitiveFloatAttributeParser("");

        assertEquals(2f, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}
