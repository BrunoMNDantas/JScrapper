package com.github.brunomndantas.jscrapper.support.parser.collection.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.*;

public class CollectionReferenceDoubleAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
        CollectionReferenceDoubleAttributeParser parser = new CollectionReferenceDoubleAttributeParser(attribute);
        assertNull( parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
         CollectionReferenceDoubleAttributeParser parser = new CollectionReferenceDoubleAttributeParser("");

        assertEquals(2d, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }


}
