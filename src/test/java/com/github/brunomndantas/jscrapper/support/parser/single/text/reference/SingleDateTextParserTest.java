package com.github.brunomndantas.jscrapper.support.parser.single.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class SingleDateTextParserTest {

    @Test
    public void getFormatTest() {
        String format = "";
        SingleDateTextParser parser = new SingleDateTextParser(format);

        assertSame(format, parser.getFormat());
    }

    @Test
    public void constructorTest() {
        SingleDateTextParser parser = new SingleDateTextParser("");
        assertEquals(null, parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        String format = "YYYY/mm/dd";
        SingleDateTextParser parser = new SingleDateTextParser(format);

        assertEquals(new SimpleDateFormat(format).parse("1111/01/01"), parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "1111/01/01";
            }
        }));
    }

}