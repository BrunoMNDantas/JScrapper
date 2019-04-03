package com.github.brunomndantas.jscrapper.support.parser.collection.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.*;

public class CollectionReferenceStringAttributeParserTest {


    @Test
    public void constructorTest() {
        String attribute = "";
        CollectionReferenceStringAttributeParser parser = new CollectionReferenceStringAttributeParser(attribute);
        assertNull( parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
         CollectionReferenceStringAttributeParser parser = new CollectionReferenceStringAttributeParser("");

        assertEquals("text", parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "text";
            }
        }));
    }

}
