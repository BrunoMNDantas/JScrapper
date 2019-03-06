package com.github.brunomndantas.jscrapper.selector.xpath;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collection;

import static org.junit.Assert.*;

public class XPathSelectorTest {

    private static final String DRIVER_PATH = "phantomjs/phantomjs.exe";



    private static WebDriver getDriver() {
        String driverPath = ClassLoader.getSystemResource(DRIVER_PATH).getPath();

        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();

        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, driverPath);

        return new PhantomJSDriver(capabilities);
    }



    @Test
    public void getSelectorTest() {
        String s = "";
        XPathSelector selector = new XPathSelector(s);

        assertSame(s, selector.getSelector());
    }

    @Test
    public void constructorTest() {
        String s = "";
        XPathSelector selector = new XPathSelector(s);

        assertSame(s, selector.getSelector());
    }


    @Test
    public void selectEmptyResultTest() throws Exception {
        WebDriver driver = getDriver();
        String htmlFile = "file://" + ClassLoader.getSystemResource("selector/empty_result.html").getPath();
        driver.get(htmlFile);
        XPathSelector selector = new XPathSelector("//*[@id=\"id\"]");

        Collection<WebElement> elements = selector.select(driver);
        assertNotNull(elements);
        assertEquals(0, elements.size());
    }

    @Test
    public void selectNonEmptyResultTest() throws Exception {
        WebDriver driver = getDriver();
        String htmlFile = "file://" + ClassLoader.getSystemResource("selector/non_empty_result.html").getPath();
        driver.get(htmlFile);
        XPathSelector selector = new XPathSelector("//*[@id=\"id\"]");

        Collection<WebElement> elements = selector.select(driver);
        assertNotNull(elements);
        assertEquals(1, elements.size());
    }

}
