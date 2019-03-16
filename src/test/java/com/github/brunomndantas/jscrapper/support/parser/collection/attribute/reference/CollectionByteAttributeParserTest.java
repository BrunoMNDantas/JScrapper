package com.github.brunomndantas.jscrapper.support.parser.collection.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CollectionByteAttributeParserTest{

    @Test
    public void constructorTest() {
        String attribute = "";
         CollectionByteAttributeParser parser = new  CollectionByteAttributeParser(attribute);
        assertEquals(null, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
         CollectionByteAttributeParser parser = new  CollectionByteAttributeParser("");

        assertEquals((byte)2, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }


}
