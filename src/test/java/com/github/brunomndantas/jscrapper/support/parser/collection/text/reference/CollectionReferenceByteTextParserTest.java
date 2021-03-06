package com.github.brunomndantas.jscrapper.support.parser.collection.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CollectionReferenceByteTextParserTest {

    @Test
    public void constructorTest() {
        CollectionReferenceByteTextParser parser = new CollectionReferenceByteTextParser();
        assertNull( parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
         CollectionReferenceByteTextParser parser = new CollectionReferenceByteTextParser();

        assertEquals((byte)2, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}