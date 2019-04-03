package com.github.brunomndantas.jscrapper.support.parser.array.text.reference;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayReferenceCharacterTextParserTest {

    @Test
    public void constructorTest() {
        ArrayReferenceCharacterTextParser parser = new ArrayReferenceCharacterTextParser();
        assertSame(Character.class, parser.getKlass());
        assertNull( parser.getDefaultValue());
    }

    @Test
    public void parseElement() throws Exception {
        ArrayReferenceCharacterTextParser parser = new ArrayReferenceCharacterTextParser();

        assertEquals('\u0001', parser.parseElement(new DummyElement(){
            @Override
            public String getText() {
                return ((char)1) + "";
            }
        }));
    }

}