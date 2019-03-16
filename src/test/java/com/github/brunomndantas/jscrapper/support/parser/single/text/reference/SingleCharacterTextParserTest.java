package com.github.brunomndantas.jscrapper.support.parser.single.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleCharacterTextParserTest {

    @Test
    public void constructorTest() {
        SingleCharacterTextParser parser = new SingleCharacterTextParser();
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        SingleCharacterTextParser parser = new SingleCharacterTextParser();

        assertEquals('\u0001', parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return ((char)1) + "";
            }
        }));
    }

}
