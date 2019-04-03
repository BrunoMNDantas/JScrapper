package com.github.brunomndantas.jscrapper.support.parser.array.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayReferenceBooleanAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
        ArrayReferenceBooleanAttributeParser parser = new ArrayReferenceBooleanAttributeParser(attribute);
        assertSame(Boolean.class, parser.getKlass());
        assertNull( parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayReferenceBooleanAttributeParser parser = new ArrayReferenceBooleanAttributeParser("");

        assertEquals(true, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "true";
            }
        }));
    }

}
