package com.github.brunomndantas.jscrapper.support.parser.collection;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertSame;

public class CollectionWebElementParserTest {

    @Test
    public void parseElementTest() throws Exception {
        WebElement element = new DummyElement();
        CollectionWebElementParser parser = new CollectionWebElementParser();

        Collection<Object> result =(Collection<Object>) parser.parse(null, Arrays.asList(element));
        assertSame(element, result.stream().findFirst().get());
    }

}
