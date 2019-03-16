package com.github.brunomndantas.jscrapper.support.parser.collection.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CollectionDoubleAttributeParserTest{

    @Test
    public void constructorTest() {
        String attribute = "";
         CollectionDoubleAttributeParser parser = new  CollectionDoubleAttributeParser(attribute);
        assertEquals(null, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
         CollectionDoubleAttributeParser parser = new  CollectionDoubleAttributeParser("");

        assertEquals(2d, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }


}
