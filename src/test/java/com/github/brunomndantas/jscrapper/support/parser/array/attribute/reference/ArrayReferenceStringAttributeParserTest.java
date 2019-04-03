package com.github.brunomndantas.jscrapper.support.parser.array.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayReferenceStringAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
        ArrayReferenceStringAttributeParser parser = new ArrayReferenceStringAttributeParser(attribute);
        assertSame(String.class, parser.getKlass());
        assertEquals(null, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayReferenceStringAttributeParser parser = new ArrayReferenceStringAttributeParser("");

        assertEquals("text", parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "text";
            }
        }));
    }

}
