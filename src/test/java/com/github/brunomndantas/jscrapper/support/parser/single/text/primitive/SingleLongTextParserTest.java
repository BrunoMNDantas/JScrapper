package com.github.brunomndantas.jscrapper.support.parser.single.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleLongTextParserTest {

    @Test
    public void constructorTest() {
        SingleLongTextParser parser = new SingleLongTextParser();
        assertEquals(0L, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        SingleLongTextParser parser = new SingleLongTextParser();

        assertEquals(2L, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}