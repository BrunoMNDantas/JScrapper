package com.github.brunomndantas.jscrapper.support.parser.single.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SinglePrimitiveBooleanTextParserTest {

    @Test
    public void constructorTest() {
        SinglePrimitiveBooleanTextParser parser = new SinglePrimitiveBooleanTextParser();
        assertEquals(false, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        SinglePrimitiveBooleanTextParser parser = new SinglePrimitiveBooleanTextParser();

        assertEquals(true, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "true";
            }
        }));
    }

}