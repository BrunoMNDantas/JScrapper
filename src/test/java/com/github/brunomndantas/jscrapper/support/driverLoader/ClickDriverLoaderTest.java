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

public class ClickDriverLoaderTest {

    @Test
    public void getByTest() {
        By by = By.tagName("div");
        ClickDriverLoader loader = new ClickDriverLoader(by);

        assertSame(by, loader.getBy());
    }

    @Test
    public void constructorTest() {
        By by = By.tagName("div");
        ClickDriverLoader loader = new ClickDriverLoader(by);

        assertSame(by, loader.getBy());
    }

    @Test
    public void loadTest() throws Exception {
        By by = By.tagName("div");
        boolean[] passedA = new boolean[1];
        boolean[] passedB = new boolean[1];
        boolean[] passedC = new boolean[1];


        WebElement elementA = new DummyElement() {
            @Override
            public void click() {
                passedA[0] = true;
            }
        };
        WebElement elementB = new DummyElement() {
            @Override
            public void click() {
                passedB[0] = true;
            }
        };
        Collection<WebElement> elements = Arrays.asList(elementA, elementB);

        WebDriver driver = new DummyDriver(){
            @Override
            public List<WebElement> findElements(By b) {
                passedC[0] = b==by;
                return new LinkedList<>(elements);
            }
        };

        ClickDriverLoader loader = new ClickDriverLoader(by);
        loader.load(driver);

        assertTrue(passedA[0]);
        assertTrue(passedB[0]);
        assertTrue(passedC[0]);
    }

}
