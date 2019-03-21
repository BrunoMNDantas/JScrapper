package com.github.brunomndantas.jscrapper.support.parser.single.text.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SinglePrimitiveCharacterTextParserTest {

    @Test
    public void constructorTest() {
        SinglePrimitiveCharacterTextParser parser = new SinglePrimitiveCharacterTextParser();
        assertEquals('\u0000', parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        SinglePrimitiveCharacterTextParser parser = new SinglePrimitiveCharacterTextParser();

        assertEquals('\u0001', parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return ((char)1) + "";
            }
        }));
    }

}
