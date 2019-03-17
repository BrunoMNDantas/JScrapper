package com.github.brunomndantas.jscrapper.support.elementLoader;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

public class SubmitElementLoaderTest {

    @Test
    public void loadTest() throws Exception {
        boolean[] passedA = new boolean[1];
        boolean[] passedB = new boolean[1];

        WebDriver driver = new DummyDriver();

        WebElement elementA = new DummyElement() {
            @Override
            public void submit() {
                passedA[0] = true;
            }
        };
        WebElement elementB = new DummyElement() {
            @Override
            public void submit() {
                passedB[0] = true;
            }
        };
        Collection<WebElement> elements = Arrays.asList(elementA, elementB);

        SubmitElementLoader loader = new SubmitElementLoader();
        loader.load(driver, elements);

        assertTrue(passedA[0]);
        assertTrue(passedB[0]);
    }

}
