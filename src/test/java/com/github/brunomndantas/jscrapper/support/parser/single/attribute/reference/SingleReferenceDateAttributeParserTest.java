package com.github.brunomndantas.jscrapper.support.parser.single.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class SingleReferenceDateAttributeParserTest {

    @Test
    public void getFormatTest() {
        String format = "";
        SingleReferenceDateAttributeParser parser = new SingleReferenceDateAttributeParser(null, format);

        assertSame(format, parser.getFormat());
    }

    @Test
    public void constructorTest() {
        String attribute = "";
        String format = "";
        SingleReferenceDateAttributeParser parser = new SingleReferenceDateAttributeParser(attribute, format);
        assertNull( parser.getDefaultValue());
        assertSame(format, parser.getFormat());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        String format = "YYYY/mm/dd";
        SingleReferenceDateAttributeParser parser = new SingleReferenceDateAttributeParser("", format);

        assertEquals(new SimpleDateFormat(format).parse("1111/01/01"), parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "1111/01/01";
            }
        }));
    }

}
