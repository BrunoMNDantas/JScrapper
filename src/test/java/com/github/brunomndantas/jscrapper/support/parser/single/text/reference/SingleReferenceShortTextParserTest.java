package com.github.brunomndantas.jscrapper.support.parser.single.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleReferenceShortTextParserTest {

    @Test
    public void constructorTest() {
        SingleReferenceShortTextParser parser = new SingleReferenceShortTextParser();
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        SingleReferenceShortTextParser parser = new SingleReferenceShortTextParser();

        assertEquals((short)2, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "2";
            }
        }));
    }

}