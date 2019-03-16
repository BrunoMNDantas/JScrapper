package com.github.brunomndantas.jscrapper.support.parser.array.attribute.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayByteAttributeParserTest{

    @Test
    public void constructorTest() {
        String attribute = "";
        ArrayByteAttributeParser parser = new ArrayByteAttributeParser(attribute);
        assertEquals((byte)0, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayByteAttributeParser parser = new ArrayByteAttributeParser("");

        assertEquals((byte)2, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}
