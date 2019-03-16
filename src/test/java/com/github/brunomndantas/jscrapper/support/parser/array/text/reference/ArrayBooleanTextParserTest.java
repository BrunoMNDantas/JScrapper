package com.github.brunomndantas.jscrapper.support.parser.array.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayBooleanTextParserTest{

    @Test
    public void constructorTest() {
        ArrayBooleanTextParser parser = new ArrayBooleanTextParser();
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayBooleanTextParser parser = new ArrayBooleanTextParser();

        assertEquals(true, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "true";
            }
        }));
    }

}
