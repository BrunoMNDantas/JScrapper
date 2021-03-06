package com.github.brunomndantas.jscrapper.support.parser.single.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SingleReferenceLongTextParserTest {

    @Test
    public void constructorTest() {
        SingleReferenceLongTextParser parser = new SingleReferenceLongTextParser();
        assertNull( parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        SingleReferenceLongTextParser parser = new SingleReferenceLongTextParser();

        assertEquals(2L, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}