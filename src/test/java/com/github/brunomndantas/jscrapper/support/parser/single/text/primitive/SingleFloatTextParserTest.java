package com.github.brunomndantas.jscrapper.support.parser.single.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleFloatTextParserTest {

    @Test
    public void constructorTest() {
        SingleFloatTextParser parser = new SingleFloatTextParser();
        assertEquals(0.0f, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        SingleFloatTextParser parser = new SingleFloatTextParser();

        assertEquals(2f, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}