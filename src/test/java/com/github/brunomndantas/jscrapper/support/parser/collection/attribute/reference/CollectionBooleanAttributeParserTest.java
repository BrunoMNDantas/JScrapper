package com.github.brunomndantas.jscrapper.support.parser.collection.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CollectionBooleanAttributeParserTest{

    @Test
    public void constructorTest() {
        String attribute = "";
        CollectionBooleanAttributeParser parser = new CollectionBooleanAttributeParser(attribute);
        assertEquals(null, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        CollectionBooleanAttributeParser parser = new CollectionBooleanAttributeParser("");

        assertEquals(true, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "true";
            }
        }));
    }

}
