package com.github.brunomndantas.jscrapper.support.parser.single.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.*;

public class SingleReferenceCharacterAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
        SingleReferenceCharacterAttributeParser parser = new SingleReferenceCharacterAttributeParser(attribute);
        assertNull( parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        SingleReferenceCharacterAttributeParser parser = new SingleReferenceCharacterAttributeParser("");

        assertEquals('\u0001', parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return ((char)1) + "";
            }
        }));
    }

}
