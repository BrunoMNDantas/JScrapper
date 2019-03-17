package com.github.brunomndantas.jscrapper.support.elementLoader;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class DoubleClickElementLoaderTest {

    private static final String DRIVER_PATH = "phantomjs/phantomjs.exe";



    private static WebDriver getDriver() {
        String driverPath = ClassLoader.getSystemResource(DRIVER_PATH).getPath();

        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();

        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, driverPath);

        return new PhantomJSDriver(capabilities);
    }



    @Test
    public void loadTest() throws Exception {
        WebDriver driver = getDriver();
        String htmlFile = "file://" + ClassLoader.getSystemResource("elementLoader/document.html").getPath();
        driver.get(htmlFile);

        WebElement element = driver.findElement(By.id("double"));
        Collection<WebElement> elements = Arrays.asList(element);

        DoubleClickElementLoader loader = new DoubleClickElementLoader();
        loader.load(driver, elements);

        Thread.sleep(3000);

        assertEquals(1, driver.findElements(By.className("doubleClick")).size());
    }

}
