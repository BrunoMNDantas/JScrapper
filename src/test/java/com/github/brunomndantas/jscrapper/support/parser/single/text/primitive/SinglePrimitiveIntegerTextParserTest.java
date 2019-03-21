package com.github.brunomndantas.jscrapper.support.parser.single.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SinglePrimitiveIntegerTextParserTest {

    @Test
    public void constructorTest() {
        SinglePrimitiveIntegerTextParser parser = new SinglePrimitiveIntegerTextParser();
        assertEquals(0, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        SinglePrimitiveIntegerTextParser parser = new SinglePrimitiveIntegerTextParser();

        assertEquals(2, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}