package com.github.brunomndantas.jscrapper.support.parser.array.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayBooleanAttributeParserTest{

    @Test
    public void constructorTest() {
        String attribute = "";
        ArrayBooleanAttributeParser parser = new ArrayBooleanAttributeParser(attribute);
        assertEquals(null, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayBooleanAttributeParser parser = new ArrayBooleanAttributeParser("");

        assertEquals(true, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "true";
            }
        }));
    }

}
