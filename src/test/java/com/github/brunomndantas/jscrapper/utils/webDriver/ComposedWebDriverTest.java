package com.github.brunomndantas.jscrapper.utils.webDriver;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.DummyElement;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.Logs;

import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class ComposedWebDriverTest {

    @Test
    public void getDriverTest() {
        WebDriver d = new DummyDriver();
        ComposedWebDriver driver = new ComposedWebDriver(d);
        assertSame(d, driver.getDriver());
    }

    @Test
    public void constructorTest() {
        WebDriver d = new DummyDriver();
        ComposedWebDriver driver = new ComposedWebDriver(d);
        assertSame(d, driver.getDriver());
    }

    @Test
    public void getTest() {
        boolean[] passed = new boolean[1];
        String url = "";

        WebDriver d = new DummyDriver(){
            @Override
            public void get(String u) {
                passed[0] = u.equals(url);
                super.get(url);
            }
        };
        ComposedWebDriver driver = new ComposedWebDriver(d);

        driver.get(url);

        assertTrue(passed[0]);
    }

    @Test
    public void getCurrentUrlTest() {
        String url = "";

        WebDriver d = new DummyDriver(){
            @Override
            public String getCurrentUrl() {
                return url;
            }
        };
        ComposedWebDriver driver = new ComposedWebDriver(d);

        assertSame(url, driver.getCurrentUrl());
    }

    @Test
    public void getTitleTest() {
        String title = "";

        WebDriver d = new DummyDriver(){
            @Override
            public String getTitle() {
                return title;
            }
        };
        ComposedWebDriver driver = new ComposedWebDriver(d);

        assertSame(title, driver.getTitle());
    }

    @Test
    public void findElementsTest() {
        boolean[] passed = new boolean[1];
        List<WebElement> elements = new LinkedList<>();
        By by = By.cssSelector("");

        WebDriver d = new DummyDriver(){
            @Override
            public List<WebElement> findElements(By b) {
                passed[0] = b == by;
                return elements;
            }
        };
        ComposedWebDriver driver = new ComposedWebDriver(d);

        assertSame(elements, driver.findElements(by));

        assertTrue(passed[0]);
    }

    @Test
    public void findElementTest() {
        boolean[] passed = new boolean[1];
        WebElement element = new DummyElement();
        By by = By.cssSelector("");

        WebDriver d = new DummyDriver(){
            @Override
            public WebElement findElement(By b) {
                passed[0] = b == by;
                return  element;
            }
        };
        ComposedWebDriver driver = new ComposedWebDriver(d);

        assertSame(element, driver.findElement(by));
        assertTrue(passed[0]);
    }

    @Test
    public void getPageSourceTest() {
        String source = "";

        WebDriver d = new DummyDriver(){
            @Override
            public String getPageSource() {
                return source;
            }
        };
        ComposedWebDriver driver = new ComposedWebDriver(d);

        assertSame(source, driver.getPageSource());
    }

    @Test
    public void closeTest() {
        boolean[] passed = new boolean[1];

        WebDriver d = new DummyDriver(){
            @Override
            public void close() {
                passed[0] = true;
            }
        };
        ComposedWebDriver driver = new ComposedWebDriver(d);

        driver.close();

        assertTrue(passed[0]);
    }

    @Test
    public void quitTest() {
        boolean[] passed = new boolean[1];

        WebDriver d = new DummyDriver(){
            @Override
            public void quit() {
                passed[0] = true;
            }
        };
        ComposedWebDriver driver = new ComposedWebDriver(d);

        driver.quit();

        assertTrue(passed[0]);
    }

    @Test
    public void getWindowHandlesTest() {
        Set<String> handles = new HashSet<>();

        WebDriver d = new DummyDriver(){
            @Override
            public Set<String> getWindowHandles() {
                return handles;
            }
        };
        ComposedWebDriver driver = new ComposedWebDriver(d);

        assertSame(handles, driver.getWindowHandles());
    }

    @Test
    public void getWindowHandleTest() {
        String handle = "";

        WebDriver d = new DummyDriver(){
            @Override
            public String getWindowHandle() {
                return handle;
            }
        };
        ComposedWebDriver driver = new ComposedWebDriver(d);

        assertSame(handle, driver.getWindowHandle());
    }

    @Test
    public void switchToTest() {
        WebDriver.TargetLocator locator = new WebDriver.TargetLocator() {
            @Override
            public WebDriver frame(int index) {
                return null;
            }

            @Override
            public WebDriver frame(String nameOrId) {
                return null;
            }

            @Override
            public WebDriver frame(WebElement frameElement) {
                return null;
            }

            @Override
            public WebDriver parentFrame() {
                return null;
            }

            @Override
            public WebDriver window(String nameOrHandle) {
                return null;
            }

            @Override
            public WebDriver defaultContent() {
                return null;
            }

            @Override
            public WebElement activeElement() {
                return null;
            }

            @Override
            public Alert alert() {
                return null;
            }
        };

        WebDriver d = new DummyDriver(){
            @Override
            public WebDriver.TargetLocator switchTo() {
                return locator;
            }
        };
        ComposedWebDriver driver = new ComposedWebDriver(d);

        assertSame(locator, driver.switchTo());
    }

    @Test
    public void navigateTest() {
        WebDriver.Navigation navigation = new WebDriver.Navigation() {
            @Override
            public void back() {

            }

            @Override
            public void forward() {

            }

            @Override
            public void to(String url) {

            }

            @Override
            public void to(URL url) {

            }

            @Override
            public void refresh() {

            }
        };

        WebDriver d = new DummyDriver(){
            @Override
            public WebDriver.Navigation navigate() {
                return navigation;
            }
        };
        ComposedWebDriver driver = new ComposedWebDriver(d);

        assertSame(navigation, driver.navigate());
    }

    @Test
    public void manageTest() {
        WebDriver.Options options = new WebDriver.Options() {
            @Override
            public void addCookie(Cookie cookie) {

            }

            @Override
            public void deleteCookieNamed(String name) {

            }

            @Override
            public void deleteCookie(Cookie cookie) {

            }

            @Override
            public void deleteAllCookies() {

            }

            @Override
            public Set<Cookie> getCookies() {
                return null;
            }

            @Override
            public Cookie getCookieNamed(String name) {
                return null;
            }

            @Override
            public WebDriver.Timeouts timeouts() {
                return null;
            }

            @Override
            public WebDriver.ImeHandler ime() {
                return null;
            }

            @Override
            public WebDriver.Window window() {
                return null;
            }

            @Override
            public Logs logs() {
                return null;
            }
        };

        WebDriver d = new DummyDriver(){
            @Override
            public WebDriver.Options manage() {
                return options;
            }
        };
        ComposedWebDriver driver = new ComposedWebDriver(d);

        assertSame(options, driver.manage());
    }

    @Test
    public void getScreenshotAsTest() throws WebDriverException {
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

        WebDriver d = new DummyDriver() {
            @Override
            public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
                passed[0] = target == outputType;
                return (X) x;
            }
        };
        ComposedWebDriver driver = new ComposedWebDriver(d);

        assertSame(x, driver.getScreenshotAs(outputType));
        assertTrue(passed[0]);
    }

    @Test
    public void executeScriptTest() {
        boolean[] passed = new boolean[1];
        Object result = "";
        String script = "";
        Object[] args = new Object[0];

        WebDriver d = new DummyDriver() {
            @Override
            public Object executeScript(String s, Object... a) {
                passed[0] = s==script && a==args;
                return result;
            }
        };
        ComposedWebDriver driver = new ComposedWebDriver(d);

        assertSame(result, driver.executeScript(script, args));
        assertTrue(passed[0]);
    }

    @Test
    public void executeAsyncScriptTest() {
        boolean[] passed = new boolean[1];
        Object result = "";
        String script = "";
        Object[] args = new Object[0];

        WebDriver d = new DummyDriver() {
            @Override
            public Object executeAsyncScript(String s, Object... a) {
                passed[0] = s==script && a==args;
                return result;
            }
        };
        ComposedWebDriver driver = new ComposedWebDriver(d);

        assertSame(result, driver.executeAsyncScript(script, args));
        assertTrue(passed[0]);
    }

}
