package com.github.brunomndantas.jscrapper.support.parser.array.attribute.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayPrimitiveBooleanAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
        ArrayPrimitiveBooleanAttributeParser parser = new ArrayPrimitiveBooleanAttributeParser(attribute);
        assertEquals(false, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayPrimitiveBooleanAttributeParser parser = new ArrayPrimitiveBooleanAttributeParser("");

        assertEquals(true, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "true";
            }
        }));
    }

}
