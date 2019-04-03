package com.github.brunomndantas.jscrapper.support.parser.single.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SingleReferenceDoubleTextParserTest {

    @Test
    public void constructorTest() {
        SingleReferenceDoubleTextParser parser = new SingleReferenceDoubleTextParser();
        assertNull( parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        SingleReferenceDoubleTextParser parser = new SingleReferenceDoubleTextParser();

        assertEquals(2d, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}