package com.github.brunomndantas.jscrapper.selector;

import com.github.brunomndantas.jscrapper.core.selector.SelectorException;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SelectorTest {

    private static final String DRIVER_PATH = "phantomjs/phantomjs.exe";



    private static WebDriver getDriver() {
        String driverPath = ClassLoader.getSystemResource(DRIVER_PATH).getPath();

        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();

        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, driverPath);

        return new PhantomJSDriver(capabilities);
    }



    @Test
    public void wrapExceptionTest() {
        Exception exception = new Exception();
        Selector selector = new Selector() {
            @Override
            protected Collection<WebElement> selectElements(WebDriver driver) throws Exception {
                throw exception;
            }
        };

        try {
            selector.select(getDriver());
        } catch (SelectorException e) {
            assertSame(exception, e.getCause());
        }
    }

    @Test
    public void selectTest() throws SelectorException {
        Collection<WebElement> elements = new LinkedList<>();
        WebDriver driver = getDriver();
        boolean[] passed = new boolean[1];

        Selector selector = new Selector() {
            @Override
            protected Collection<WebElement> selectElements(WebDriver d) throws Exception {
                passed[0] = d == driver;
                return elements;
            }
        };

        assertSame(elements, selector.select(driver));
        assertTrue(passed[0]);
    }

}
