package com.github.brunomndantas.jscrapper.support.parser.collection.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CollectionReferenceShortAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
         CollectionReferenceShortAttributeParser parser = new CollectionReferenceShortAttributeParser(attribute);
        assertEquals(null, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
         CollectionReferenceShortAttributeParser parser = new CollectionReferenceShortAttributeParser("");

        assertEquals((short)2, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}
