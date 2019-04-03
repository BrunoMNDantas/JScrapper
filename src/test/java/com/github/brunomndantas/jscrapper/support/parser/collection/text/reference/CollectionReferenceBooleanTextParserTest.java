package com.github.brunomndantas.jscrapper.support.parser.collection.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CollectionReferenceBooleanTextParserTest {

    @Test
    public void constructorTest() {
        CollectionReferenceBooleanTextParser parser = new CollectionReferenceBooleanTextParser();
        assertNull( parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
         CollectionReferenceBooleanTextParser parser = new CollectionReferenceBooleanTextParser();

        assertEquals(true, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "true";
            }
        }));
    }
}
