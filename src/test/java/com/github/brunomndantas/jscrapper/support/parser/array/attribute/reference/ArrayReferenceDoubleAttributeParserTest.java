package com.github.brunomndantas.jscrapper.support.parser.array.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayReferenceDoubleAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
        ArrayReferenceDoubleAttributeParser parser = new ArrayReferenceDoubleAttributeParser(attribute);
        assertSame(Double.class, parser.getKlass());
        assertNull( parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayReferenceDoubleAttributeParser parser = new ArrayReferenceDoubleAttributeParser("");

        assertEquals(2d, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}
