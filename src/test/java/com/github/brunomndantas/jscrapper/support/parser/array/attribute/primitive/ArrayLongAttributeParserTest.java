package com.github.brunomndantas.jscrapper.support.parser.array.attribute.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayLongAttributeParserTest{

    @Test
    public void constructorTest() {
        String attribute = "";
        ArrayLongAttributeParser parser = new ArrayLongAttributeParser(attribute);
        assertEquals(0L, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayLongAttributeParser parser = new ArrayLongAttributeParser("");

        assertEquals(2L, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}
