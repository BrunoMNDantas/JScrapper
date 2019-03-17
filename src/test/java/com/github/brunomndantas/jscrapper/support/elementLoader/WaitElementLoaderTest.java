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

public class WaitElementLoaderTest {

    @Test
    public void getTimeUnitTest() {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        WaitElementLoader loader = new WaitElementLoader(timeUnit, 0);

        assertSame(timeUnit, loader.getTimeUnit());
    }

    @Test
    public void getTimeTest() {
        long time = 100;
        WaitElementLoader loader = new WaitElementLoader(null, time);

        assertEquals(time, loader.getTime());
    }

    @Test
    public void constructorTest() {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        long time = 100;
        WaitElementLoader loader = new WaitElementLoader(timeUnit, time);

        assertSame(timeUnit, loader.getTimeUnit());
        assertEquals(time, loader.getTime());
    }

    @Test
    public void loadTest() throws Exception {
        WaitElementLoader loader = new WaitElementLoader(TimeUnit.MILLISECONDS, 1000);

        WebDriver driver = new DummyDriver();
        Collection<WebElement> elements = Arrays.asList(new DummyElement(), new DummyElement(), new DummyElement());

        long time = System.currentTimeMillis();
        loader.load(driver, elements);
        assertTrue(time + loader.getTimeUnit().toMillis(loader.getTime())*elements.size() <= System.currentTimeMillis());
    }

}
