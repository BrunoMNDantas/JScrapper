package com.github.brunomndantas.jscrapper.support.parser.single.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SingleReferenceFloatTextParserTest {

    @Test
    public void constructorTest() {
        SingleReferenceFloatTextParser parser = new SingleReferenceFloatTextParser();
        assertNull( parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        SingleReferenceFloatTextParser parser = new SingleReferenceFloatTextParser();

        assertEquals(2f, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}