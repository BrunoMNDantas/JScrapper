package com.github.brunomndantas.jscrapper.support.parser.collection.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CollectionReferenceLongAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
         CollectionReferenceLongAttributeParser parser = new CollectionReferenceLongAttributeParser(attribute);
        assertEquals(null, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
         CollectionReferenceLongAttributeParser parser = new CollectionReferenceLongAttributeParser("");

        assertEquals(2L, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}
