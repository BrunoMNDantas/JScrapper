package com.github.brunomndantas.jscrapper.utils.webElement;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.remote.FileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ComposedWebElementTest {

    @Test
    public void getElementTest() {
        WebElement e = new DummyElement();
        ComposedWebElement element = new ComposedWebElement(e);
        assertSame(e, element.getElement());
    }

    @Test
    public void constructorTest() {
        WebElement e = new DummyElement();
        ComposedWebElement element = new ComposedWebElement(e);
        assertSame(e, element.getElement());
    }

    @Test
    public void click() {
        boolean[] passed = new boolean[1];
        WebElement e = new DummyElement() {
            @Override
            public void click() {
                passed[0] = true;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        element.click();

        assertTrue(passed[0]);
    }

    @Test
    public void submit() {
        boolean[] passed = new boolean[1];
        WebElement e = new DummyElement() {
            @Override
            public void submit() {
                passed[0] = true;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        element.submit();

        assertTrue(passed[0]);
    }

    @Test
    public void sendKeys() {
        boolean[] passed = new boolean[1];
        CharSequence[] keysToSend = new CharSequence[1];

        WebElement e = new DummyElement() {
            @Override
            public void sendKeys(CharSequence... keys) {
                passed[0] = keys == keysToSend;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        element.sendKeys(keysToSend);

        assertTrue(passed[0]);
    }

    @Test
    public void clear() {
        boolean[] passed = new boolean[1];
        WebElement e = new DummyElement() {
            @Override
            public void clear() {
                passed[0] = true;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        element.clear();

        assertTrue(passed[0]);
    }

    @Test
    public void getTagName() {
        String name = "";

        WebElement e = new DummyElement() {
            @Override
            public String getTagName() {
                return name;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(name, element.getTagName());
    }

    @Test
    public void getAttribute() {
        boolean[] passed = new boolean[1];
        String name = "";
        String attribute = "";

        WebElement e = new DummyElement() {
            @Override
            public String getAttribute(String n) {
                passed[0] = n == name;
                return attribute;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(attribute, element.getAttribute(name));
        assertTrue(passed[0]);
    }

    @Test
    public void isSelected() {
        boolean selected = true;

        WebElement e = new DummyElement() {
            @Override
            public boolean isSelected() {
                return selected;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertEquals(selected, element.isSelected());
    }

    @Test
    public void isEnabled() {
        boolean enabled = true;

        WebElement e = new DummyElement() {
            @Override
            public boolean isEnabled() {
                return enabled;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertEquals(enabled, element.isEnabled());
    }

    @Test
    public void getText() {
        String text = "";

        WebElement e = new DummyElement() {
            @Override
            public String getText() {
                return text;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(text, element.getText());
    }

    @Test
    public void findElements() {
        boolean[] passed = new boolean[1];
        List<WebElement> elements = new LinkedList<>();
        By by = By.cssSelector("");

        WebElement e = new DummyElement() {
            @Override
            public List<WebElement> findElements(By b) {
                passed[0] = b == by;
                return elements;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(elements, element.findElements(by));

        assertTrue(passed[0]);
    }

    @Test
    public void findElement() {
        boolean[] passed = new boolean[1];
        WebElement elem = new DummyElement();
        By by = By.cssSelector("");

        WebElement e = new DummyElement() {
            @Override
            public WebElement findElement(By b) {
                passed[0] = b == by;
                return  elem;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(elem, element.findElement(by));
        assertTrue(passed[0]);

    }

    @Test
    public void isDisplayed() {
        boolean displayed = true;

        WebElement e = new DummyElement() {
            @Override
            public boolean isDisplayed() {
                return displayed;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertEquals(displayed, element.isDisplayed());
    }

    @Test
    public void getLocation() {
        Point point = new Point(1,2);

        WebElement e = new DummyElement() {
            @Override
            public Point getLocation() {
                return point;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(point, element.getLocation());
    }

    @Test
    public void getSize() {
        Dimension dimension = new Dimension(1,2);

        WebElement e = new DummyElement() {
            @Override
            public Dimension getSize() {
                return dimension;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(dimension, element.getSize());
    }

    @Test
    public void getRect() {
        Rectangle rectangle = new Rectangle(1,2,3,4);

        WebElement e = new DummyElement() {
            @Override
            public Rectangle getRect() {
                return rectangle;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(rectangle, element.getRect());
    }

    @Test
    public void getCssValue() {
        boolean[] passed = new boolean[1];
        String propertyName = "";
        String value = "";

        WebElement e = new DummyElement() {
            @Override
            public String getCssValue(String name) {
                passed[0] = name == propertyName;
                return value;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(value, element.getCssValue(propertyName));
        assertTrue(passed[0]);
    }

    @Test
    public void getScreenshotAs() throws WebDriverException {
        boolean[] passed = new boolean[1];
        String x = "";
        OutputType<String> outputType = new OutputType<String>() {
            @Override
            public String convertFromBase64Png(String base64Png) {
                return x;
            }

            @Override
            public String convertFromPngBytes(byte[] png) {
                return x;
            }
        };

        WebElement e = new DummyElement() {
            @Override
            public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
                passed[0] = target == outputType;
                return (X) x;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(x, element.getScreenshotAs(outputType));
        assertTrue(passed[0]);
    }

    @Test
    public void setParentTest() {
        boolean[] passed = new boolean[1];
        RemoteWebDriver param = null;

        WebElement e = new DummyElement() {
            @Override
            public void setParent(RemoteWebDriver p) {
                passed[0] = p == param;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        element.setParent(param);

        assertTrue(passed[0]);
    }

    @Test
    public void getIdTest() {
        String param = "id";

        WebElement e = new DummyElement() {
            @Override
            public String getId() {
                return param;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(param, element.getId());
    }

    @Test
    public void setIdTest() {
        boolean[] passed = new boolean[1];
        String param = "id";

        WebElement e = new DummyElement() {
            @Override
            public void setId(String i) {
                passed[0] = i == param;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        element.setId(param);

        assertTrue(passed[0]);
    }

    @Test
    public void setFileDetectorTest() {
        boolean[] passed = new boolean[1];
        FileDetector param = keys -> null;

        WebElement e = new DummyElement() {
            @Override
            public void setFileDetector(FileDetector d) {
                passed[0] = d == param;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        element.setFileDetector(param);

        assertTrue(passed[0]);
    }

    @Test
    public void findElementByIdTest() {
        boolean[] passed = new boolean[1];
        WebElement elem = new DummyElement();
        String using = "";

        WebElement e = new DummyElement() {
            @Override
            public WebElement findElementById(String u) {
                passed[0] = u == using;
                return  elem;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(elem, element.findElementById(using));
        assertTrue(passed[0]);
    }

    @Test
    public void findElementsByIdTest() {
        boolean[] passed = new boolean[1];
        List<WebElement> elements = new LinkedList<>();
        String using = "";

        WebElement e = new DummyElement() {
            @Override
            public List<WebElement> findElementsById(String u) {
                passed[0] = u == using;
                return elements;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(elements, element.findElementsById(using));

        assertTrue(passed[0]);
    }

    @Test
    public void findElementByLinkTextTest() {
        boolean[] passed = new boolean[1];
        WebElement elem = new DummyElement();
        String using = "";

        WebElement e = new DummyElement() {
            @Override
            public WebElement findElementByLinkText(String u) {
                passed[0] = u == using;
                return  elem;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(elem, element.findElementByLinkText(using));
        assertTrue(passed[0]);
    }

    @Test
    public void findElementsByLinkTextTest() {
        boolean[] passed = new boolean[1];
        List<WebElement> elements = new LinkedList<>();
        String using = "";

        WebElement e = new DummyElement() {
            @Override
            public List<WebElement> findElementsByLinkText(String u) {
                passed[0] = u == using;
                return elements;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(elements, element.findElementsByLinkText(using));

        assertTrue(passed[0]);
    }

    @Test
    public void findElementByNameTest() {
        boolean[] passed = new boolean[1];
        WebElement elem = new DummyElement();
        String using = "";

        WebElement e = new DummyElement() {
            @Override
            public WebElement findElementByName(String u) {
                passed[0] = u == using;
                return  elem;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(elem, element.findElementByName(using));
        assertTrue(passed[0]);
    }

    @Test
    public void findElementsByNameTest() {
        boolean[] passed = new boolean[1];
        List<WebElement> elements = new LinkedList<>();
        String using = "";

        WebElement e = new DummyElement() {
            @Override
            public List<WebElement> findElementsByName(String u) {
                passed[0] = u == using;
                return elements;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(elements, element.findElementsByName(using));

        assertTrue(passed[0]);
    }

    @Test
    public void findElementByClassNameTest() {
        boolean[] passed = new boolean[1];
        WebElement elem = new DummyElement();
        String using = "";

        WebElement e = new DummyElement() {
            @Override
            public WebElement findElementByClassName(String u) {
                passed[0] = u == using;
                return  elem;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(elem, element.findElementByClassName(using));
        assertTrue(passed[0]);
    }

    @Test
    public void findElementsByClassNameTest() {
        boolean[] passed = new boolean[1];
        List<WebElement> elements = new LinkedList<>();
        String using = "";

        WebElement e = new DummyElement() {
            @Override
            public List<WebElement> findElementsByClassName(String u) {
                passed[0] = u == using;
                return elements;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(elements, element.findElementsByClassName(using));

        assertTrue(passed[0]);
    }

    @Test
    public void findElementByCssSelectorTest() {
        boolean[] passed = new boolean[1];
        WebElement elem = new DummyElement();
        String using = "";

        WebElement e = new DummyElement() {
            @Override
            public WebElement findElementByCssSelector(String u) {
                passed[0] = u == using;
                return  elem;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(elem, element.findElementByCssSelector(using));
        assertTrue(passed[0]);
    }

    @Test
    public void findElementsByCssSelectorTest() {
        boolean[] passed = new boolean[1];
        List<WebElement> elements = new LinkedList<>();
        String using = "";

        WebElement e = new DummyElement() {
            @Override
            public List<WebElement> findElementsByCssSelector(String u) {
                passed[0] = u == using;
                return elements;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(elements, element.findElementsByCssSelector(using));

        assertTrue(passed[0]);
    }

    @Test
    public void findElementByXPathTest() {
        boolean[] passed = new boolean[1];
        WebElement elem = new DummyElement();
        String using = "";

        WebElement e = new DummyElement() {
            @Override
            public WebElement findElementByXPath(String u) {
                passed[0] = u == using;
                return  elem;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(elem, element.findElementByXPath(using));
        assertTrue(passed[0]);
    }

    @Test
    public void findElementsByXPathTest() {
        boolean[] passed = new boolean[1];
        List<WebElement> elements = new LinkedList<>();
        String using = "";

        WebElement e = new DummyElement() {
            @Override
            public List<WebElement> findElementsByXPath(String u) {
                passed[0] = u == using;
                return elements;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(elements, element.findElementsByXPath(using));

        assertTrue(passed[0]);
    }

    @Test
    public void findElementByPartialLinkTextTest() {
        boolean[] passed = new boolean[1];
        WebElement elem = new DummyElement();
        String using = "";

        WebElement e = new DummyElement() {
            @Override
            public WebElement findElementByPartialLinkText(String u) {
                passed[0] = u == using;
                return  elem;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(elem, element.findElementByPartialLinkText(using));
        assertTrue(passed[0]);
    }

    @Test
    public void findElementsByPartialLinkTextTest() {
        boolean[] passed = new boolean[1];
        List<WebElement> elements = new LinkedList<>();
        String using = "";

        WebElement e = new DummyElement() {
            @Override
            public List<WebElement> findElementsByPartialLinkText(String u) {
                passed[0] = u == using;
                return elements;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(elements, element.findElementsByPartialLinkText(using));

        assertTrue(passed[0]);
    }

    @Test
    public void findElementByTagNameTest() {
        boolean[] passed = new boolean[1];
        WebElement elem = new DummyElement();
        String using = "";

        WebElement e = new DummyElement() {
            @Override
            public WebElement findElementByTagName(String u) {
                passed[0] = u == using;
                return  elem;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(elem, element.findElementByTagName(using));
        assertTrue(passed[0]);
    }

    @Test
    public void findElementsByTagNameTest() {
        boolean[] passed = new boolean[1];
        List<WebElement> elements = new LinkedList<>();
        String using = "";

        WebElement e = new DummyElement() {
            @Override
            public List<WebElement> findElementsByTagName(String u) {
                passed[0] = u == using;
                return elements;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(elements, element.findElementsByTagName(using));

        assertTrue(passed[0]);
    }

    @Test
    public void equalsTest() {
        boolean[] passed = new boolean[1];
        boolean ret = true;
        Object obj = "";
        RemoteWebElement e = new DummyElement() {
            @Override
            public boolean equals(Object o) {
                passed[0] = o==obj;
                return ret;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertEquals(ret, element.equals(obj));
        assertTrue(passed[0]);
    }

    @Test
    public void hashCodeTest() {
        int ret = 8;
        RemoteWebElement e = new DummyElement() {
            @Override
            public int hashCode() {
                return ret;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertEquals(ret, element.hashCode());
    }

    @Test
    public void getWrappedDriverTest() {
        WebDriver ret = new DummyDriver();
        RemoteWebElement e = new DummyElement() {
            @Override
            public WebDriver getWrappedDriver() {
                return ret;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(ret, element.getWrappedDriver());
    }

    @Test
    public void getCoordinatesTest() {
        Coordinates ret = new Coordinates() {
            @Override
            public Point onScreen() {
                return null;
            }

            @Override
            public Point inViewPort() {
                return null;
            }

            @Override
            public Point onPage() {
                return null;
            }

            @Override
            public Object getAuxiliary() {
                return null;
            }
        };
        RemoteWebElement e = new DummyElement() {
            @Override
            public Coordinates getCoordinates() {
                return ret;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(ret, element.getCoordinates());
    }

    @Test
    public void toStringTest() {
        String ret = "";
        RemoteWebElement e = new DummyElement() {
            @Override
            public String toString() {
                return ret;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(ret, element.toString());
    }

    @Test
    public void toJsonTest() {
        Map<String, Object> ret = new HashMap<>();
        RemoteWebElement e = new DummyElement() {
            @Override
            public Map<String, Object> toJson() {
                return ret;
            }
        };
        ComposedWebElement element = new ComposedWebElement(e);

        assertSame(ret, element.toJson());
    }

}
