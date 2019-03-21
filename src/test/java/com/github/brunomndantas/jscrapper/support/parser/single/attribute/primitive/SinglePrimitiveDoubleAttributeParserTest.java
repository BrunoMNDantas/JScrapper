package com.github.brunomndantas.jscrapper.support.parser.single.attribute.primitive;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class SinglePrimitiveDoubleAttributeParserTest {

    @Test
    public void constructorTest() {
        String attribute = "";
        SinglePrimitiveDoubleAttributeParser parser = new SinglePrimitiveDoubleAttributeParser(attribute);
        assertEquals(0.0d, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        SinglePrimitiveDoubleAttributeParser parser = new SinglePrimitiveDoubleAttributeParser("");

        assertEquals(2d, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}
