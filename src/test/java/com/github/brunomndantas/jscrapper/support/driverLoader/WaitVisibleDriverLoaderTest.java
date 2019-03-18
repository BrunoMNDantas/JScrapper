package com.github.brunomndantas.jscrapper.support.driverLoader;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class WaitVisibleDriverLoaderTest {

    @Test
    public void getTimeUnitTest() {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        WaitVisibleDriverLoader loader = new WaitVisibleDriverLoader(timeUnit, 0, null);

        assertSame(timeUnit, loader.getTimeUnit());
    }

    @Test
    public void getTimeTest() {
        long time = 100;
        WaitVisibleDriverLoader loader = new WaitVisibleDriverLoader(null, time, null);

        assertEquals(time, loader.getTime());
    }
    
    @Test
    public void getByTest() {
        By by = By.tagName("div");
        WaitVisibleDriverLoader loader = new WaitVisibleDriverLoader(null, 0, by);

        assertSame(by, loader.getBy());
    }

    @Test
    public void constructorTest() {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        long time = 100;
        By by = By.tagName("div");
        WaitVisibleDriverLoader loader = new WaitVisibleDriverLoader(timeUnit, time, by);

        assertSame(timeUnit, loader.getTimeUnit());
        assertEquals(time, loader.getTime());
        assertSame(by, loader.getBy());
    }

    @Test
    public void loadTest() throws Exception {
        WaitVisibleDriverLoader loader = new WaitVisibleDriverLoader(TimeUnit.MILLISECONDS, 1000*10, By.tagName("div"));
        boolean[] visibleA = new boolean[1];
        boolean[] visibleB = new boolean[1];
        boolean[] passed = new boolean[1];

        List<WebElement> elements = Arrays.asList(
            new DummyElement(){
                @Override public boolean isDisplayed() { return visibleA[0]; }
                @Override public boolean isEnabled() { return visibleA[0]; }
            },
            new DummyElement(){
                @Override public boolean isDisplayed() { return visibleB[0]; }
                @Override public boolean isEnabled() { return visibleB[0]; }
            }
        );
        WebDriver driver = new DummyDriver() {
            @Override
            public List<WebElement> findElements(By b) {
                passed[0] = b == loader.getBy();
                return elements;
            }
        };

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
        loader.load(driver);
        assertTrue(time + 5000 <= System.currentTimeMillis());
        assertTrue(passed[0]);
    }

    @Test
    public void loadBeforeVisibleTest() throws Exception {
        WaitVisibleDriverLoader loader = new WaitVisibleDriverLoader(TimeUnit.MILLISECONDS, 1000, By.tagName("div"));
        boolean[] visibleA = new boolean[1];
        boolean[] visibleB = new boolean[1];

        List<WebElement> elements = Arrays.asList(
            new DummyElement(){
                @Override public boolean isDisplayed() { return visibleA[0]; }
                @Override public boolean isEnabled() { return visibleA[0]; }
            },
            new DummyElement(){
                @Override public boolean isDisplayed() { return visibleB[0]; }
                @Override public boolean isEnabled() { return visibleB[0]; }
            }
        );
        WebDriver driver = new DummyDriver() {
            @Override
            public List<WebElement> findElements(By by) {
                return elements;
            }
        };

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
        loader.load(driver);
        assertTrue(time + loader.getTimeUnit().toMillis(loader.getTime())*elements.size() <= System.currentTimeMillis());
        assertFalse(visibleA[0]);
        assertFalse(visibleB[0]);
    }

}
