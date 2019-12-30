package com.github.brunomndantas.jscrapper.utils.webElement;

import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class WaitWebElementTest {

    @Test
    public void getSleepTimeTest() {
        long sleepTime = 1000;
        WaitWebElement element = new WaitWebElement(null, sleepTime);
        assertEquals(sleepTime, element.getSleepTime());
    }

    @Test
    public void constructorTest() {
        WebElement e = new DummyElement();
        long sleepTime = 1000;
        WaitWebElement element = new WaitWebElement(e, sleepTime);
        assertEquals(sleepTime, element.getSleepTime());
        assertSame(e, element.getElement());
    }

    @Test
    public void click() {
        long time = 3000;
        long finishedTime = System.currentTimeMillis() + time;

        WebElement e= new DummyElement() {
            private boolean firstTime = true;

            @Override
            public boolean isDisplayed() {
                if(firstTime) {
                    firstTime = false;
                    return false;
                }

                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public void click() { }
        };
        WaitWebElement element = new WaitWebElement(e, time);

        element.click();

        assertTrue(finishedTime <= System.currentTimeMillis());
    }

    @Test
    public void submit() {
        long time = 3000;
        long finishedTime = System.currentTimeMillis() + time;

        WebElement e= new DummyElement() {
            private boolean firstTime = true;

            @Override
            public boolean isDisplayed() {
                if(firstTime) {
                    firstTime = false;
                    return false;
                }

                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public void submit() { }
        };
        WaitWebElement element = new WaitWebElement(e, time);

        element.submit();

        assertTrue(finishedTime <= System.currentTimeMillis());
    }

    @Test
    public void sendKeys() {
        long time = 3000;
        long finishedTime = System.currentTimeMillis() + time;

        WebElement e= new DummyElement() {
            private boolean firstTime = true;

            @Override
            public boolean isDisplayed() {
                if(firstTime) {
                    firstTime = false;
                    return false;
                }

                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public void sendKeys(CharSequence... charSequence) { }
        };
        WaitWebElement element = new WaitWebElement(e, time);

        element.sendKeys();

        assertTrue(finishedTime <= System.currentTimeMillis());
    }

    @Test
    public void clear() {
        long time = 3000;
        long finishedTime = System.currentTimeMillis() + time;

        WebElement e= new DummyElement() {
            private boolean firstTime = true;

            @Override
            public boolean isDisplayed() {
                if(firstTime) {
                    firstTime = false;
                    return false;
                }

                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public void clear() { }
        };
        WaitWebElement element = new WaitWebElement(e, time);

        element.clear();

        assertTrue(finishedTime <= System.currentTimeMillis());
    }

    @Test
    public void findElements() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;
        List<WebElement> elements = Arrays.asList(new DummyElement());

        WebElement e = new DummyElement() {
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
        WaitWebElement element = new WaitWebElement(e, sleepTime);

        List<WebElement> elems = element.findElements(null);

        assertEquals(1, elems.size());
        assertTrue(elems.stream().findFirst().get() instanceof WaitWebElement);
        assertEquals(sleepTime, ((WaitWebElement)elems.get(0)).getSleepTime());
        assertSame(elements.get(0), ((WaitWebElement)elems.get(0)).getElement());
        assertTrue(finishTime <= System.currentTimeMillis());
    }

    @Test
    public void findElement() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;
        WebElement el = new DummyElement();

        WebElement e = new DummyElement() {
            private boolean firstTime = true;
            @Override
            public WebElement findElement(By by) {
                if(firstTime){
                    firstTime = false;
                    return null;
                }

                return el;
            }
        };
        WaitWebElement element = new WaitWebElement(e, sleepTime);

        WebElement elem = element.findElement(null);

        assertTrue(elem  instanceof WaitWebElement);
        assertEquals(sleepTime, ((WaitWebElement)elem).getSleepTime());
        assertSame(el, ((WaitWebElement)elem).getElement());
        assertTrue(finishTime <= System.currentTimeMillis());
    }

    @Test
    public void findElementByIdTest() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;
        WebElement el = new DummyElement();

        WebElement e = new DummyElement() {
            private boolean firstTime = true;
            @Override
            public WebElement findElementById(String using) {
                if(firstTime){
                    firstTime = false;
                    return null;
                }

                return el;
            }
        };
        WaitWebElement element = new WaitWebElement(e, sleepTime);

        WebElement elem = element.findElementById(null);

        assertTrue(elem  instanceof WaitWebElement);
        assertEquals(sleepTime, ((WaitWebElement)elem).getSleepTime());
        assertSame(el, ((WaitWebElement)elem).getElement());
        assertTrue(finishTime <= System.currentTimeMillis());
    }

    @Test
    public void findElementsByIdTest() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;
        List<WebElement> elements = Arrays.asList(new DummyElement());

        WebElement e = new DummyElement() {
            private boolean firstTime = true;
            @Override
            public List<WebElement> findElementsById(String using) {
                if(firstTime){
                    firstTime = false;
                    return new LinkedList<>();
                }

                return elements;
            }
        };
        WaitWebElement element = new WaitWebElement(e, sleepTime);

        List<WebElement> elems = element.findElementsById(null);

        assertEquals(1, elems.size());
        assertTrue(elems.stream().findFirst().get() instanceof WaitWebElement);
        assertEquals(sleepTime, ((WaitWebElement)elems.get(0)).getSleepTime());
        assertSame(elements.get(0), ((WaitWebElement)elems.get(0)).getElement());
        assertTrue(finishTime <= System.currentTimeMillis());
    }

    @Test
    public void findElementByLinkTextTest() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;
        WebElement el = new DummyElement();

        WebElement e = new DummyElement() {
            private boolean firstTime = true;
            @Override
            public WebElement findElementByLinkText(String using) {
                if(firstTime){
                    firstTime = false;
                    return null;
                }

                return el;
            }
        };
        WaitWebElement element = new WaitWebElement(e, sleepTime);

        WebElement elem = element.findElementByLinkText(null);

        assertTrue(elem  instanceof WaitWebElement);
        assertEquals(sleepTime, ((WaitWebElement)elem).getSleepTime());
        assertSame(el, ((WaitWebElement)elem).getElement());
        assertTrue(finishTime <= System.currentTimeMillis());
    }

    @Test
    public void findElementsByLinkTextTest() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;
        List<WebElement> elements = Arrays.asList(new DummyElement());

        WebElement e = new DummyElement() {
            private boolean firstTime = true;
            @Override
            public List<WebElement> findElementsByLinkText(String using) {
                if(firstTime){
                    firstTime = false;
                    return new LinkedList<>();
                }

                return elements;
            }
        };
        WaitWebElement element = new WaitWebElement(e, sleepTime);

        List<WebElement> elems = element.findElementsByLinkText(null);

        assertEquals(1, elems.size());
        assertTrue(elems.stream().findFirst().get() instanceof WaitWebElement);
        assertEquals(sleepTime, ((WaitWebElement)elems.get(0)).getSleepTime());
        assertSame(elements.get(0), ((WaitWebElement)elems.get(0)).getElement());
        assertTrue(finishTime <= System.currentTimeMillis());
    }

    @Test
    public void findElementByNameTest() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;
        WebElement el = new DummyElement();

        WebElement e = new DummyElement() {
            private boolean firstTime = true;
            @Override
            public WebElement findElementByName(String using) {
                if(firstTime){
                    firstTime = false;
                    return null;
                }

                return el;
            }
        };
        WaitWebElement element = new WaitWebElement(e, sleepTime);

        WebElement elem = element.findElementByName(null);

        assertTrue(elem  instanceof WaitWebElement);
        assertEquals(sleepTime, ((WaitWebElement)elem).getSleepTime());
        assertSame(el, ((WaitWebElement)elem).getElement());
        assertTrue(finishTime <= System.currentTimeMillis());
    }

    @Test
    public void findElementsByNameTest() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;
        List<WebElement> elements = Arrays.asList(new DummyElement());

        WebElement e = new DummyElement() {
            private boolean firstTime = true;
            @Override
            public List<WebElement> findElementsByName(String using) {
                if(firstTime){
                    firstTime = false;
                    return new LinkedList<>();
                }

                return elements;
            }
        };
        WaitWebElement element = new WaitWebElement(e, sleepTime);

        List<WebElement> elems = element.findElementsByName(null);

        assertEquals(1, elems.size());
        assertTrue(elems.stream().findFirst().get() instanceof WaitWebElement);
        assertEquals(sleepTime, ((WaitWebElement)elems.get(0)).getSleepTime());
        assertSame(elements.get(0), ((WaitWebElement)elems.get(0)).getElement());
        assertTrue(finishTime <= System.currentTimeMillis());
    }

    @Test
    public void findElementByClassNameTest() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;
        WebElement el = new DummyElement();

        WebElement e = new DummyElement() {
            private boolean firstTime = true;
            @Override
            public WebElement findElementByClassName(String using) {
                if(firstTime){
                    firstTime = false;
                    return null;
                }

                return el;
            }
        };
        WaitWebElement element = new WaitWebElement(e, sleepTime);

        WebElement elem = element.findElementByClassName(null);

        assertTrue(elem  instanceof WaitWebElement);
        assertEquals(sleepTime, ((WaitWebElement)elem).getSleepTime());
        assertSame(el, ((WaitWebElement)elem).getElement());
        assertTrue(finishTime <= System.currentTimeMillis());
    }

    @Test
    public void findElementsByClassNameTest() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;
        List<WebElement> elements = Arrays.asList(new DummyElement());

        WebElement e = new DummyElement() {
            private boolean firstTime = true;
            @Override
            public List<WebElement> findElementsByClassName(String using) {
                if(firstTime){
                    firstTime = false;
                    return new LinkedList<>();
                }

                return elements;
            }
        };
        WaitWebElement element = new WaitWebElement(e, sleepTime);

        List<WebElement> elems = element.findElementsByClassName(null);

        assertEquals(1, elems.size());
        assertTrue(elems.stream().findFirst().get() instanceof WaitWebElement);
        assertEquals(sleepTime, ((WaitWebElement)elems.get(0)).getSleepTime());
        assertSame(elements.get(0), ((WaitWebElement)elems.get(0)).getElement());
        assertTrue(finishTime <= System.currentTimeMillis());
    }

    @Test
    public void findElementByCssSelectorTest() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;
        WebElement el = new DummyElement();

        WebElement e = new DummyElement() {
            private boolean firstTime = true;
            @Override
            public WebElement findElementByCssSelector(String using) {
                if(firstTime){
                    firstTime = false;
                    return null;
                }

                return el;
            }
        };
        WaitWebElement element = new WaitWebElement(e, sleepTime);

        WebElement elem = element.findElementByCssSelector(null);

        assertTrue(elem  instanceof WaitWebElement);
        assertEquals(sleepTime, ((WaitWebElement)elem).getSleepTime());
        assertSame(el, ((WaitWebElement)elem).getElement());
        assertTrue(finishTime <= System.currentTimeMillis());
    }

    @Test
    public void findElementsByCssSelectorTest() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;
        List<WebElement> elements = Arrays.asList(new DummyElement());

        WebElement e = new DummyElement() {
            private boolean firstTime = true;
            @Override
            public List<WebElement> findElementsByCssSelector(String using) {
                if(firstTime){
                    firstTime = false;
                    return new LinkedList<>();
                }

                return elements;
            }
        };
        WaitWebElement element = new WaitWebElement(e, sleepTime);

        List<WebElement> elems = element.findElementsByCssSelector(null);

        assertEquals(1, elems.size());
        assertTrue(elems.stream().findFirst().get() instanceof WaitWebElement);
        assertEquals(sleepTime, ((WaitWebElement)elems.get(0)).getSleepTime());
        assertSame(elements.get(0), ((WaitWebElement)elems.get(0)).getElement());
        assertTrue(finishTime <= System.currentTimeMillis());
    }

    @Test
    public void findElementByXPathTest() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;
        WebElement el = new DummyElement();

        WebElement e = new DummyElement() {
            private boolean firstTime = true;
            @Override
            public WebElement findElementByXPath(String using) {
                if(firstTime){
                    firstTime = false;
                    return null;
                }

                return el;
            }
        };
        WaitWebElement element = new WaitWebElement(e, sleepTime);

        WebElement elem = element.findElementByXPath(null);

        assertTrue(elem  instanceof WaitWebElement);
        assertEquals(sleepTime, ((WaitWebElement)elem).getSleepTime());
        assertSame(el, ((WaitWebElement)elem).getElement());
        assertTrue(finishTime <= System.currentTimeMillis());
    }

    @Test
    public void findElementsByXPathTest() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;
        List<WebElement> elements = Arrays.asList(new DummyElement());

        WebElement e = new DummyElement() {
            private boolean firstTime = true;
            @Override
            public List<WebElement> findElementsByXPath(String using) {
                if(firstTime){
                    firstTime = false;
                    return new LinkedList<>();
                }

                return elements;
            }
        };
        WaitWebElement element = new WaitWebElement(e, sleepTime);

        List<WebElement> elems = element.findElementsByXPath(null);

        assertEquals(1, elems.size());
        assertTrue(elems.stream().findFirst().get() instanceof WaitWebElement);
        assertEquals(sleepTime, ((WaitWebElement)elems.get(0)).getSleepTime());
        assertSame(elements.get(0), ((WaitWebElement)elems.get(0)).getElement());
        assertTrue(finishTime <= System.currentTimeMillis());
    }

    @Test
    public void findElementByPartialLinkTextTest() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;
        WebElement el = new DummyElement();

        WebElement e = new DummyElement() {
            private boolean firstTime = true;
            @Override
            public WebElement findElementByPartialLinkText(String using) {
                if(firstTime){
                    firstTime = false;
                    return null;
                }

                return el;
            }
        };
        WaitWebElement element = new WaitWebElement(e, sleepTime);

        WebElement elem = element.findElementByPartialLinkText(null);

        assertTrue(elem  instanceof WaitWebElement);
        assertEquals(sleepTime, ((WaitWebElement)elem).getSleepTime());
        assertSame(el, ((WaitWebElement)elem).getElement());
        assertTrue(finishTime <= System.currentTimeMillis());
    }

    @Test
    public void findElementsByPartialLinkTextTest() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;
        List<WebElement> elements = Arrays.asList(new DummyElement());

        WebElement e = new DummyElement() {
            private boolean firstTime = true;
            @Override
            public List<WebElement> findElementsByPartialLinkText(String using) {
                if(firstTime){
                    firstTime = false;
                    return new LinkedList<>();
                }

                return elements;
            }
        };
        WaitWebElement element = new WaitWebElement(e, sleepTime);

        List<WebElement> elems = element.findElementsByPartialLinkText(null);

        assertEquals(1, elems.size());
        assertTrue(elems.stream().findFirst().get() instanceof WaitWebElement);
        assertEquals(sleepTime, ((WaitWebElement)elems.get(0)).getSleepTime());
        assertSame(elements.get(0), ((WaitWebElement)elems.get(0)).getElement());
        assertTrue(finishTime <= System.currentTimeMillis());
    }

    @Test
    public void findElementByTagNameTest() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;
        WebElement el = new DummyElement();

        WebElement e = new DummyElement() {
            private boolean firstTime = true;
            @Override
            public WebElement findElementByTagName(String using) {
                if(firstTime){
                    firstTime = false;
                    return null;
                }

                return el;
            }
        };
        WaitWebElement element = new WaitWebElement(e, sleepTime);

        WebElement elem = element.findElementByTagName(null);

        assertTrue(elem  instanceof WaitWebElement);
        assertEquals(sleepTime, ((WaitWebElement)elem).getSleepTime());
        assertSame(el, ((WaitWebElement)elem).getElement());
        assertTrue(finishTime <= System.currentTimeMillis());
    }

    @Test
    public void findElementsByTagNameTest() {
        long sleepTime = 3000;
        long finishTime = System.currentTimeMillis() + sleepTime;
        List<WebElement> elements = Arrays.asList(new DummyElement());

        WebElement e = new DummyElement() {
            private boolean firstTime = true;
            @Override
            public List<WebElement> findElementsByTagName(String using) {
                if(firstTime){
                    firstTime = false;
                    return new LinkedList<>();
                }

                return elements;
            }
        };
        WaitWebElement element = new WaitWebElement(e, sleepTime);

        List<WebElement> elems = element.findElementsByTagName(null);

        assertEquals(1, elems.size());
        assertTrue(elems.stream().findFirst().get() instanceof WaitWebElement);
        assertEquals(sleepTime, ((WaitWebElement)elems.get(0)).getSleepTime());
        assertSame(elements.get(0), ((WaitWebElement)elems.get(0)).getElement());
        assertTrue(finishTime <= System.currentTimeMillis());
    }

}
