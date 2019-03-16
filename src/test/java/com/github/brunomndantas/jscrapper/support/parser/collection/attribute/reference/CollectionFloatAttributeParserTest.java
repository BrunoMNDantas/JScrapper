package com.github.brunomndantas.jscrapper.support.parser.collection.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CollectionFloatAttributeParserTest{

    @Test
    public void constructorTest() {
        String attribute = "";
         CollectionFloatAttributeParser parser = new  CollectionFloatAttributeParser(attribute);
        assertEquals(null, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
         CollectionFloatAttributeParser parser = new  CollectionFloatAttributeParser("");

        assertEquals(2f, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}
