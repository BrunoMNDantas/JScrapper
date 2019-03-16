package com.github.brunomndantas.jscrapper.support.parser.single;

import com.github.brunomndantas.jscrapper.DummyDriver;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;

import static org.junit.Assert.assertSame;

public class SingleWebDriverParserTest {

    @Test
    public void parseElementTest() throws Exception {
        WebDriver driver = new DummyDriver();
        SingleWebDriverParser parser = new SingleWebDriverParser();

        assertSame(driver, parser.parse(driver, Arrays.asList()));
    }

}
