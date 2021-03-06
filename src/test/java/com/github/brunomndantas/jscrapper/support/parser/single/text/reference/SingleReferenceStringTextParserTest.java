package com.github.brunomndantas.jscrapper.support.parser.single.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SingleReferenceStringTextParserTest {

    @Test
    public void constructorTest() {
        SingleReferenceStringTextParser parser = new SingleReferenceStringTextParser();
        assertNull( parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        SingleReferenceStringTextParser parser = new SingleReferenceStringTextParser();

        assertEquals("text", parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "text";
            }
        }));
    }

}
