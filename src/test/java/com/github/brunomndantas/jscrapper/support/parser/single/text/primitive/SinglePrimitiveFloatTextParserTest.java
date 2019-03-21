package com.github.brunomndantas.jscrapper.support.parser.single.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SinglePrimitiveFloatTextParserTest {

    @Test
    public void constructorTest() {
        SinglePrimitiveFloatTextParser parser = new SinglePrimitiveFloatTextParser();
        assertEquals(0.0f, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        SinglePrimitiveFloatTextParser parser = new SinglePrimitiveFloatTextParser();

        assertEquals(2f, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}