package com.github.brunomndantas.jscrapper.support.parser.array.attribute.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayFloatAttributeParserTest{

    @Test
    public void constructorTest() {
        String attribute = "";
        ArrayFloatAttributeParser parser = new ArrayFloatAttributeParser(attribute);
        assertEquals(0.0f, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayFloatAttributeParser parser = new ArrayFloatAttributeParser("");

        assertEquals(2f, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}
