package com.github.brunomndantas.jscrapper.support.parser.single.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleDoubleTextParserTest {

    @Test
    public void constructorTest() {
        SingleDoubleTextParser parser = new SingleDoubleTextParser();
        assertEquals(0.0d, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        SingleDoubleTextParser parser = new SingleDoubleTextParser();

        assertEquals(2d, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}