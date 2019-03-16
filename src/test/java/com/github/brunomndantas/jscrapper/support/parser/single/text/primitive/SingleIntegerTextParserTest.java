package com.github.brunomndantas.jscrapper.support.parser.single.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleIntegerTextParserTest {

    @Test
    public void constructorTest() {
        SingleIntegerTextParser parser = new SingleIntegerTextParser();
        assertEquals(0, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        SingleIntegerTextParser parser = new SingleIntegerTextParser();

        assertEquals(2, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}