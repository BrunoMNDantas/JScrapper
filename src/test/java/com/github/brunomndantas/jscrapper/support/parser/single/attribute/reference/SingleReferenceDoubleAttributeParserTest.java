package com.github.brunomndantas.jscrapper.support.parser.single.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.*;

public class SingleReferenceDoubleAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
        SingleReferenceDoubleAttributeParser parser = new SingleReferenceDoubleAttributeParser(attribute);
        assertNull( parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        SingleReferenceDoubleAttributeParser parser = new SingleReferenceDoubleAttributeParser("");

        assertEquals(2d, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}
