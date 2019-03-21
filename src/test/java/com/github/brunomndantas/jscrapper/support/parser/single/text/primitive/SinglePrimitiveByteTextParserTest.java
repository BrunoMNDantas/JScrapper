package com.github.brunomndantas.jscrapper.support.parser.single.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SinglePrimitiveByteTextParserTest {

    @Test
    public void constructorTest() {
        SinglePrimitiveByteTextParser parser = new SinglePrimitiveByteTextParser();
        assertEquals((byte)0, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        SinglePrimitiveByteTextParser parser = new SinglePrimitiveByteTextParser();

        assertEquals((byte)2, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}
