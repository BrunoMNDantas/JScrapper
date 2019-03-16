package com.github.brunomndantas.jscrapper.support.parser.array.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayDateAttributeParserTest{

    @Test
    public void getFormatTest() {
        String format = "";
        ArrayDateAttributeParser parser = new ArrayDateAttributeParser(null, format);

        assertSame(format, parser.getFormat());
    }

    @Test
    public void constructorTest() {
        String attribute = "";
        String format = "";
        ArrayDateAttributeParser parser = new ArrayDateAttributeParser(attribute, format);
        assertEquals(null, parser.getDefaultValue());
        assertSame(format, parser.getFormat());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        String format = "YYYY/mm/dd";
        ArrayDateAttributeParser parser = new ArrayDateAttributeParser("", format);

        assertEquals(new SimpleDateFormat(format).parse("1111/01/01"), parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "1111/01/01";
            }
        }));
    }

}
