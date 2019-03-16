package com.github.brunomndantas.jscrapper.support.parser.array.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayShortAttributeParserTest{

    @Test
    public void constructorTest() {
        String attribute = "";
        ArrayShortAttributeParser parser = new ArrayShortAttributeParser(attribute);
        assertEquals(null, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayShortAttributeParser parser = new ArrayShortAttributeParser("");

        assertEquals((short)2, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}
