package com.github.brunomndantas.jscrapper.support.driverLoader;

import com.github.brunomndantas.jscrapper.DummyDriver;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class WaitDriverLoaderTest {

    @Test
    public void getTimeUnitTest() {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        WaitDriverLoader loader = new WaitDriverLoader(timeUnit, 0);

        assertSame(timeUnit, loader.getTimeUnit());
    }

    @Test
    public void getTimeTest() {
        long time = 100;
        WaitDriverLoader loader = new WaitDriverLoader(null, time);

        assertEquals(time, loader.getTime());
    }

    @Test
    public void constructorTest() {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        long time = 100;
        WaitDriverLoader loader = new WaitDriverLoader(timeUnit, time);

        assertSame(timeUnit, loader.getTimeUnit());
        assertEquals(time, loader.getTime());
    }

    @Test
    public void loadTest() throws Exception {
        WaitDriverLoader loader = new WaitDriverLoader(TimeUnit.MILLISECONDS, 1000);

        WebDriver driver = new DummyDriver();

        long time = System.currentTimeMillis();
        loader.load(driver);
        assertTrue(time <= System.currentTimeMillis());
    }

}
