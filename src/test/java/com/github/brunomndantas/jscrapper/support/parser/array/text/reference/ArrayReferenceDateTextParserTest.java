package com.github.brunomndantas.jscrapper.support.parser.array.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayReferenceDateTextParserTest {

    @Test
    public void getFormatTest() {
        String format = "";
        ArrayReferenceDateTextParser parser = new ArrayReferenceDateTextParser(format);
        assertSame(format, parser.getFormat());
    }

    @Test
    public void constructorTest() {
        ArrayReferenceDateTextParser parser = new ArrayReferenceDateTextParser("");
        assertSame(Date.class, parser.getKlass());
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        String format = "YYYY/mm/dd";
        ArrayReferenceDateTextParser parser = new ArrayReferenceDateTextParser(format);

        assertEquals(new SimpleDateFormat(format).parse("1111/01/01"), parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "1111/01/01";
            }
        }));
    }

}