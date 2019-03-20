package com.github.brunomndantas.jscrapper.support.driverLoader;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SendKeysDriverLoaderTest {

    @Test
    public void getKeysTest() {
        String keys = "keys";
        SendKeysDriverLoader loader = new SendKeysDriverLoader(keys, null);

        assertSame(keys, loader.getKeys());
    }

    @Test
    public void getByTest() {
        By by = By.id("id");
        SendKeysDriverLoader loader = new SendKeysDriverLoader(null, by);

        assertSame(by, loader.getBy());
    }

    @Test
    public void constructorTest() {
        By by = By.id("id");
        String keys = "keys";
        SendKeysDriverLoader loader = new SendKeysDriverLoader(keys, by);

        assertSame(keys, loader.getKeys());
        assertSame(by, loader.getBy());
    }

    @Test
    public void loadTest() throws Exception {
        String keys = "keys";
        By by = By.id("id");
        SendKeysDriverLoader loader = new SendKeysDriverLoader(keys, by);
        boolean[] passedA = new boolean[1];
        boolean[] passedB = new boolean[1];
        boolean[] passedC = new boolean[1];

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

        WebDriver driver = new DummyDriver(){
            @Override
            public List<WebElement> findElements(By b) {
                passedC[0] = b == by;
                return new LinkedList<>(elements);
            }
        };

        loader.load(driver);

        assertTrue(passedA[0]);
        assertTrue(passedB[0]);
        assertTrue(passedC[0]);
    }

}
