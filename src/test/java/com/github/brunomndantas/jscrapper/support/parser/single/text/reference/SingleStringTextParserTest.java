package com.github.brunomndantas.jscrapper.support.parser.single.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleStringTextParserTest {

    @Test
    public void constructorTest() {
        SingleStringTextParser parser = new SingleStringTextParser();
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        SingleStringTextParser parser = new SingleStringTextParser();

        assertEquals("text", parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "text";
            }
        }));
    }

}
