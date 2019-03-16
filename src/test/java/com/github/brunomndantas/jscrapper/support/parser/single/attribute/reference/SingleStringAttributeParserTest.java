package com.github.brunomndantas.jscrapper.support.parser.single.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class SingleStringAttributeParserTest{

    @Test
    public void constructorTest() {
        String attribute = "";
        SingleStringAttributeParser parser = new SingleStringAttributeParser(attribute);
        assertEquals(null, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        SingleStringAttributeParser parser = new SingleStringAttributeParser("");

        assertEquals("text", parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "text";
            }
        }));
    }

}
