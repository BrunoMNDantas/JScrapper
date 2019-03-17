package com.github.brunomndantas.jscrapper.support.elementLoader;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class WaitVisibleElementLoaderTest {

    @Test
    public void getTimeUnitTest() {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        WaitVisibleElementLoader loader = new WaitVisibleElementLoader(timeUnit, 0);

        assertSame(timeUnit, loader.getTimeUnit());
    }

    @Test
    public void getTimeTest() {
        long time = 100;
        WaitVisibleElementLoader loader = new WaitVisibleElementLoader(null, time);

        assertEquals(time, loader.getTime());
    }

    @Test
    public void constructorTest() {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        long time = 100;
        WaitVisibleElementLoader loader = new WaitVisibleElementLoader(timeUnit, time);

        assertSame(timeUnit, loader.getTimeUnit());
        assertEquals(time, loader.getTime());
    }

    @Test
    public void loadTest() throws Exception {
        WaitVisibleElementLoader loader = new WaitVisibleElementLoader(TimeUnit.MILLISECONDS, 1000*10);
        boolean[] visibleA = new boolean[1];
        boolean[] visibleB = new boolean[1];

        WebDriver driver = new DummyDriver();
        WebElement elementA = new DummyElement(){
            @Override public boolean isDisplayed() { return visibleA[0]; }
            @Override public boolean isEnabled() { return visibleA[0]; }
        };
        WebElement elementB = new DummyElement(){
            @Override public boolean isDisplayed() { return visibleB[0]; }
            @Override public boolean isEnabled() { return visibleB[0]; }
        };

        Collection<WebElement> elements = Arrays.asList(elementA, elementB);

        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            visibleA[0] = true;
            visibleB[0] = true;
        }).start();

        long time = System.currentTimeMillis();
        loader.load(driver, elements);
        assertTrue(time + 5000 <= System.currentTimeMillis());
    }

    @Test
    public void loadBeforeVisibleTest() throws Exception {
        WaitVisibleElementLoader loader = new WaitVisibleElementLoader(TimeUnit.MILLISECONDS, 1000);
        boolean[] visibleA = new boolean[1];
        boolean[] visibleB = new boolean[1];

        WebDriver driver = new DummyDriver();
        WebElement elementA = new DummyElement(){
            @Override public boolean isDisplayed() { return visibleA[0]; }
            @Override public boolean isEnabled() { return visibleA[0]; }
        };
        WebElement elementB = new DummyElement(){
            @Override public boolean isDisplayed() { return visibleB[0]; }
            @Override public boolean isEnabled() { return visibleB[0]; }
        };

        Collection<WebElement> elements = Arrays.asList(elementA, elementB);

        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            visibleA[0] = true;
            visibleB[0] = true;
        }).start();

        long time = System.currentTimeMillis();
        loader.load(driver, elements);
        assertTrue(time + loader.getTimeUnit().toMillis(loader.getTime())*elements.size() <= System.currentTimeMillis());
        assertFalse(visibleA[0]);
        assertFalse(visibleB[0]);
    }

}
