package com.github.brunomndantas.jscrapper.support.parser.array.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayReferenceByteAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
        ArrayReferenceByteAttributeParser parser = new ArrayReferenceByteAttributeParser(attribute);
        assertEquals(null, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayReferenceByteAttributeParser parser = new ArrayReferenceByteAttributeParser("");

        assertEquals((byte)2, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }


}
