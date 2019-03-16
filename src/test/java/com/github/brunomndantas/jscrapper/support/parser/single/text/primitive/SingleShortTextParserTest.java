package com.github.brunomndantas.jscrapper.support.parser.single.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleShortTextParserTest {

    @Test
    public void constructorTest() {
        SingleShortTextParser parser = new SingleShortTextParser();
        assertEquals((short)0, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        SingleShortTextParser parser = new SingleShortTextParser();

        assertEquals((short)2, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }
}