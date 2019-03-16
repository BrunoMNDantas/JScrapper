package com.github.brunomndantas.jscrapper.support.parser.array.attribute.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayIntegerAttributeParserTest{

    @Test
    public void constructorTest() {
        String attribute = "";
        ArrayIntegerAttributeParser parser = new ArrayIntegerAttributeParser(attribute);
        assertEquals(0, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayIntegerAttributeParser parser = new ArrayIntegerAttributeParser("");

        assertEquals(2, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}
