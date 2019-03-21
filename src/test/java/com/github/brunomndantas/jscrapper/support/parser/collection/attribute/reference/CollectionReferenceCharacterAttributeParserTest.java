package com.github.brunomndantas.jscrapper.support.parser.collection.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CollectionReferenceCharacterAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
         CollectionReferenceCharacterAttributeParser parser = new CollectionReferenceCharacterAttributeParser(attribute);
        assertEquals(null, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
         CollectionReferenceCharacterAttributeParser parser = new CollectionReferenceCharacterAttributeParser("");

        assertEquals('\u0001', parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return ((char)1) + "";
            }
        }));
    }

}
