package com.github.brunomndantas.jscrapper.support.parser.single.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.*;

public class SingleReferenceIntegerAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
        SingleReferenceIntegerAttributeParser parser = new SingleReferenceIntegerAttributeParser(attribute);
        assertNull( parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        SingleReferenceIntegerAttributeParser parser = new SingleReferenceIntegerAttributeParser("");

        assertEquals(2, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}
