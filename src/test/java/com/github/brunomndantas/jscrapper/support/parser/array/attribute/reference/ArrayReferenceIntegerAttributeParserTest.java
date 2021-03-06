package com.github.brunomndantas.jscrapper.support.parser.array.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayReferenceIntegerAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
        ArrayReferenceIntegerAttributeParser parser = new ArrayReferenceIntegerAttributeParser(attribute);
        assertSame(Integer.class, parser.getKlass());
        assertNull( parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayReferenceIntegerAttributeParser parser = new ArrayReferenceIntegerAttributeParser("");

        assertEquals(2, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}
