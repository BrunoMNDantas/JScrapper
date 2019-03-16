package com.github.brunomndantas.jscrapper.support.parser.single.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class SingleFloatAttributeParserTest{

    @Test
    public void constructorTest() {
        String attribute = "";
        SingleFloatAttributeParser parser = new SingleFloatAttributeParser(attribute);
        assertEquals(null, parser.getDefaultValue());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        SingleFloatAttributeParser parser = new SingleFloatAttributeParser("");

        assertEquals(2f, parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "2";
            }
        }));
    }

}
