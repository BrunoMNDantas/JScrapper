package com.github.brunomndantas.jscrapper.support.parser.array.attribute.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class ArrayReferenceDateAttributeParserTest {

    @Test
    public void getFormatTest() {
        String format = "";
        ArrayReferenceDateAttributeParser parser = new ArrayReferenceDateAttributeParser(null, format);
        assertSame(format, parser.getFormat());
    }

    @Test
    public void constructorTest() {
        String attribute = "";
        String format = "";
        ArrayReferenceDateAttributeParser parser = new ArrayReferenceDateAttributeParser(attribute, format);
        assertSame(Date.class, parser.getKlass());
        assertNull( parser.getDefaultValue());
        assertSame(format, parser.getFormat());
        assertSame(attribute, parser.getAttribute());
    }

    @Test
    public void parseElement() throws Exception {
        String format = "YYYY/mm/dd";
        ArrayReferenceDateAttributeParser parser = new ArrayReferenceDateAttributeParser("", format);

        assertEquals(new SimpleDateFormat(format).parse("1111/01/01"), parser.parseElement(new DummyElement(){
            @Override
            public String getAttribute(String a) {
                return "1111/01/01";
            }
        }));
    }

}
