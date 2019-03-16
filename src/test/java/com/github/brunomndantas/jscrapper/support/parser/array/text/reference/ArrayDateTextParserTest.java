package com.github.brunomndantas.jscrapper.support.parser.array.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ArrayDateTextParserTest{

    @Test
    public void getFormatTest() {
        String format = "";
        ArrayDateTextParser parser = new ArrayDateTextParser(format);

        assertSame(format, parser.getFormat());
    }

    @Test
    public void constructorTest() {
        ArrayDateTextParser parser = new ArrayDateTextParser("");
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        String format = "YYYY/mm/dd";
        ArrayDateTextParser parser = new ArrayDateTextParser(format);

        assertEquals(new SimpleDateFormat(format).parse("1111/01/01"), parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "1111/01/01";
            }
        }));
    }

}