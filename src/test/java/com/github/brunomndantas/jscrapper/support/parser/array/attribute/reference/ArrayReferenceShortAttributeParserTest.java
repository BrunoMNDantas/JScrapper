package com.github.brunomndantas.jscrapper.support.parser.array.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayReferenceShortAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
        ArrayReferenceShortAttributeParser parser = new ArrayReferenceShortAttributeParser(attribute);
        assertSame(Short.class, parser.getKlass());
        assertNull( parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayReferenceShortAttributeParser parser = new ArrayReferenceShortAttributeParser("");

        assertEquals((short)2, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}
