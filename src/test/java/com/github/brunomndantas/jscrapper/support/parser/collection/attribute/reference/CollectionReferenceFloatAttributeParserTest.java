package com.github.brunomndantas.jscrapper.support.parser.collection.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.*;

public class CollectionReferenceFloatAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
        CollectionReferenceFloatAttributeParser parser = new CollectionReferenceFloatAttributeParser(attribute);
        assertNull( parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
         CollectionReferenceFloatAttributeParser parser = new CollectionReferenceFloatAttributeParser("");

        assertEquals(2f, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}
