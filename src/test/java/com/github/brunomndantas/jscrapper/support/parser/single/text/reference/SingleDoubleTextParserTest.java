package com.github.brunomndantas.jscrapper.support.parser.single.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleDoubleTextParserTest {

    @Test
    public void constructorTest() {
        SingleDoubleTextParser parser = new SingleDoubleTextParser();
        assertEquals(null, parser.getDefaultValue());
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