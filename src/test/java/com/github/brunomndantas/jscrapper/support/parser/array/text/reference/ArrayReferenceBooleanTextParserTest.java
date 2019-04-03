package com.github.brunomndantas.jscrapper.support.parser.array.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayReferenceBooleanTextParserTest {

    @Test
    public void constructorTest() {
        ArrayReferenceBooleanTextParser parser = new ArrayReferenceBooleanTextParser();
        assertSame(Boolean.class, parser.getKlass());
        assertNull( parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayReferenceBooleanTextParser parser = new ArrayReferenceBooleanTextParser();

        assertEquals(true, parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return "true";
            }
        }));
    }

}
