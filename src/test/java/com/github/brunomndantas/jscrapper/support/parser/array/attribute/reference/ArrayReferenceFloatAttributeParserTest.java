package com.github.brunomndantas.jscrapper.support.parser.array.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayReferenceFloatAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
        ArrayReferenceFloatAttributeParser parser = new ArrayReferenceFloatAttributeParser(attribute);
        assertSame(Float.class, parser.getKlass());
        assertNull( parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayReferenceFloatAttributeParser parser = new ArrayReferenceFloatAttributeParser("");

        assertEquals(2f, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}
