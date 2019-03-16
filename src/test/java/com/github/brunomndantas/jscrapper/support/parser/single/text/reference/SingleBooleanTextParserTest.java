package com.github.brunomndantas.jscrapper.support.parser.single.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleBooleanTextParserTest {

    @Test
    public void constructorTest() {
        SingleBooleanTextParser parser = new SingleBooleanTextParser();
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        SingleBooleanTextParser parser = new SingleBooleanTextParser();

        assertEquals(true, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "true";
            }
        }));
    }

}