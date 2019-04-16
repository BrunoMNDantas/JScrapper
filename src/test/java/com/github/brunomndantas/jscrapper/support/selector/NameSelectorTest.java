package com.github.brunomndantas.jscrapper.support.selector;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collection;

import static org.junit.Assert.*;

public class NameSelectorTest {

    private static final String DRIVER_PATH = "chrome/chromedriver.exe";



    private static WebDriver getDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        String path = ClassLoader.getSystemResource(DRIVER_PATH).getPath();
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, path);

        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, Boolean.toString(true));

        return new ChromeDriver(options);
    }



    @Test
    public void getSelectorTest() {
        String s = "";
        NameSelector selector = new NameSelector(s);

        assertSame(s, selector.getSelector());
    }

    @Test
    public void constructorTest() {
        String s = "";
        NameSelector selector = new NameSelector(s);

        assertSame(s, selector.getSelector());
    }

    @Test
    public void selectEmptyResultTest() throws Exception {
        WebDriver driver = getDriver();
        String htmlFile = "file://" + ClassLoader.getSystemResource("selector/empty_result.html").getPath();
        driver.get(htmlFile);
        NameSelector selector = new NameSelector("name");

        Collection<WebElement> elements = selector.select(driver);
        assertNotNull(elements);
        assertEquals(0, elements.size());
    }

    @Test
    public void selectNonEmptyResultTest() throws Exception {
        WebDriver driver = getDriver();
        String htmlFile = "file://" + ClassLoader.getSystemResource("selector/non_empty_result.html").getPath();
        driver.get(htmlFile);
        NameSelector selector = new NameSelector("name");

        Collection<WebElement> elements = selector.select(driver);
        assertNotNull(elements);
        assertEquals(2, elements.size());
    }

}