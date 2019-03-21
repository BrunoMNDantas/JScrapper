package com.github.brunomndantas.jscrapper.support.parser.array.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayReferenceCharacterAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
        ArrayReferenceCharacterAttributeParser parser = new ArrayReferenceCharacterAttributeParser(attribute);
        assertEquals(null, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayReferenceCharacterAttributeParser parser = new ArrayReferenceCharacterAttributeParser("");

        assertEquals('\u0001', parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return ((char)1) + "";
            }
        }));
    }

}
