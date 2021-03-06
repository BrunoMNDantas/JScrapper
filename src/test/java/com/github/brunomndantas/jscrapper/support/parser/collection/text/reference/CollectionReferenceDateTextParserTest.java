package com.github.brunomndantas.jscrapper.support.parser.collection.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class CollectionReferenceDateTextParserTest {

    @Test
    public void getFormatTest() {
        String format = "";
         CollectionReferenceDateTextParser parser = new CollectionReferenceDateTextParser(format);

        assertSame(format, parser.getFormat());
    }

    @Test
    public void constructorTest() {
        CollectionReferenceDateTextParser parser = new CollectionReferenceDateTextParser("");
        assertNull( parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        String format = "YYYY/mm/dd";
         CollectionReferenceDateTextParser parser = new CollectionReferenceDateTextParser(format);

        assertEquals(new SimpleDateFormat(format).parse("1111/01/01"), parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "1111/01/01";
            }
        }));
    }

}