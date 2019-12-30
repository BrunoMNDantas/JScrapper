package com.github.brunomndantas.jscrapper.utils.webDriver;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.DummyElement;
import com.github.brunomndantas.jscrapper.utils.webElement.WaitWebElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class WaitWebDriverTest {

    @Test
    public void getSleepTimeTest() {
        long time = 1000;
        WaitWebDriver driver = new WaitWebDriver(null, 1000);
        assertEquals(time, driver.getSleepTime());
    }

    @Test
    public void constructorTest() {
        WebDriver d = new DummyDriver();
        long time = 1000;
        WaitWebDriver driver = new WaitWebDriver(d, 1000);
        assertSame(d, driver.getDriver());
        assertEquals(time, driver.getSleepTime());
    }
    
    @Test
    public void getTest() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;

        WaitWebDriver driver = new WaitWebDriver(new DummyDriver(), sleepTime);

        driver.get("");

        assertTrue(finishTime<=System.currentTimeMillis());
    }

    @Test
    public void findElementsTest() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;
        List<WebElement> elements = Arrays.asList(new DummyElement());

        WebDriver d = new DummyDriver() {
            private boolean firstTime = true;
            @Override
            public List<WebElement> findElements(By by) {
                if(firstTime){
                    firstTime = false;
                    return new LinkedList<>();
                }

                return elements;
            }
        };
        WaitWebDriver driver = new WaitWebDriver(d, sleepTime);

        List<WebElement> elems = driver.findElements(null);

        assertEquals(1, elems.size());
        assertTrue(elems.stream().findFirst().get() instanceof WaitWebElement);
        assertEquals(sleepTime, ((WaitWebElement)elems.get(0)).getSleepTime());
        assertSame(elements.get(0), ((WaitWebElement)elems.get(0)).getElement());
        assertTrue(finishTime <= System.currentTimeMillis());
    }

    @Test
    public void findElementTest() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;
        WebElement element = new DummyElement();

        WebDriver d = new DummyDriver() {
            private boolean firstTime = true;
            @Override
            public WebElement findElement(By by) {
                if(firstTime){
                    firstTime = false;
                    return null;
                }

                return element;
            }
        };
        WaitWebDriver driver = new WaitWebDriver(d, sleepTime);

        WebElement elem = driver.findElement(null);

        assertTrue(elem  instanceof WaitWebElement);
        assertEquals(sleepTime, ((WaitWebElement)elem).getSleepTime());
        assertSame(element, ((WaitWebElement)elem).getElement());
        assertTrue(finishTime <= System.currentTimeMillis());
    }

}
