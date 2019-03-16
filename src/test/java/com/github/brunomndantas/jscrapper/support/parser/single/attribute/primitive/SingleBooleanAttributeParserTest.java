package com.github.brunomndantas.jscrapper.support.parser.single.attribute.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class SingleBooleanAttributeParserTest{

    @Test
    public void constructorTest() {
        String attribute = "";
        SingleBooleanAttributeParser parser = new SingleBooleanAttributeParser(attribute);
        assertEquals(false, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        SingleBooleanAttributeParser parser = new SingleBooleanAttributeParser("");

        assertEquals(true, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "true";
            }
        }));
    }

}
