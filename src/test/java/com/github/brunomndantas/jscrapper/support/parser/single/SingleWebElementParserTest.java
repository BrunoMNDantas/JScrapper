package com.github.brunomndantas.jscrapper.support.parser.single;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.Arrays;

import static org.junit.Assert.assertSame;

public class SingleWebElementParserTest {

    @Test
    public void parseElementTest() throws Exception {
        WebElement element = new DummyElement();
        SingleWebElementParser parser = new SingleWebElementParser();

        assertSame(element, parser.parse(null, Arrays.asList(element)));
    }

}
