package com.github.brunomndantas.jscrapper.support.parser.array;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.Arrays;

import static org.junit.Assert.assertSame;

public class ArrayWebElementParserTest {

    @Test
    public void parseElementTest() throws Exception {
        WebElement element = new DummyElement();
        ArrayWebElementParser parser = new ArrayWebElementParser();

        Object[] result = (Object[])parser.parse(null, Arrays.asList(element));
        assertSame(element, result[0]);
    }

}
