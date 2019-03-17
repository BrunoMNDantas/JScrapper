package com.github.brunomndantas.jscrapper.support.parser.array.attribute.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayDoubleAttributeParserTest{

    @Test
    public void constructorTest() {
        String attribute = "";
        ArrayDoubleAttributeParser parser = new ArrayDoubleAttributeParser(attribute);
        assertEquals(0.0d, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayDoubleAttributeParser parser = new ArrayDoubleAttributeParser("");

        assertEquals(2d, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}