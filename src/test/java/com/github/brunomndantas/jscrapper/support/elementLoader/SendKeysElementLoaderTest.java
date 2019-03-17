package com.github.brunomndantas.jscrapper.support.elementLoader;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SendKeysElementLoaderTest {

    @Test
    public void getKeysTest() {
        String keys = "keys";
        SendKeysElementLoader loader = new SendKeysElementLoader(keys);

        assertSame(keys, loader.getKeys());
    }

    @Test
    public void constructorTest() {
        String keys = "keys";
        SendKeysElementLoader loader = new SendKeysElementLoader(keys);

        assertSame(keys, loader.getKeys());
    }

    @Test
    public void loadTest() throws Exception {
        String keys = "keys";
        SendKeysElementLoader loader = new SendKeysElementLoader(keys);
        boolean[] passedA = new boolean[1];
        boolean[] passedB = new boolean[1];

        WebDriver driver = new DummyDriver();
        WebElement elementA = new DummyElement() {
            @Override
            public void sendKeys(CharSequence... keysToSend) {
                passedA[0] = keysToSend.equals(keysToSend);
            }
        };
        WebElement elementB = new DummyElement() {
            @Override
            public void sendKeys(CharSequence... keysToSend) {
                passedB[0] = keysToSend.equals(keysToSend);
            }
        };
        Collection<WebElement> elements = Arrays.asList(elementA, elementB);

        loader.load(driver, elements);

        assertTrue(passedA[0]);
        assertTrue(passedB[0]);
    }

}
