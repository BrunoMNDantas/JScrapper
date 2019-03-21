package com.github.brunomndantas.jscrapper.support.parser.array.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayReferenceLongAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
        ArrayReferenceLongAttributeParser parser = new ArrayReferenceLongAttributeParser(attribute);
        assertEquals(null, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayReferenceLongAttributeParser parser = new ArrayReferenceLongAttributeParser("");

        assertEquals(2L, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}
