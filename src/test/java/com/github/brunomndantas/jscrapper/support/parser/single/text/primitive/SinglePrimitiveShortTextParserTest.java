package com.github.brunomndantas.jscrapper.support.parser.single.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SinglePrimitiveShortTextParserTest {

    @Test
    public void constructorTest() {
        SinglePrimitiveShortTextParser parser = new SinglePrimitiveShortTextParser();
        assertEquals((short)0, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        SinglePrimitiveShortTextParser parser = new SinglePrimitiveShortTextParser();

        assertEquals((short)2, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }
}