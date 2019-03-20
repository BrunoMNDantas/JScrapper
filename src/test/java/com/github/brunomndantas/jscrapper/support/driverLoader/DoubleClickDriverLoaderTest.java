package com.github.brunomndantas.jscrapper.support.driverLoader;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class DoubleClickDriverLoaderTest {

    private static final String DRIVER_PATH = "phantomjs/phantomjs.exe";



    private static WebDriver getDriver() {
        String driverPath = ClassLoader.getSystemResource(DRIVER_PATH).getPath();

        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();

        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, driverPath);

        return new PhantomJSDriver(capabilities);
    }



    @Test
    public void getByTest() {
        By by = By.tagName("div");
        DoubleClickDriverLoader loader = new DoubleClickDriverLoader(by);

        assertSame(by, loader.getBy());
    }

    @Test
    public void constructorTest() {
        By by = By.tagName("div");
        DoubleClickDriverLoader loader = new DoubleClickDriverLoader(by);

        assertSame(by, loader.getBy());
    }

    @Test
    public void loadTest() throws Exception {
        WebDriver driver = getDriver();
        String htmlFile = "file://" + ClassLoader.getSystemResource("driverLoader/document.html").getPath();
        driver.get(htmlFile);

        DoubleClickDriverLoader loader = new DoubleClickDriverLoader(By.id("double"));
        loader.load(driver);

        Thread.sleep(3000);

        assertEquals(1, driver.findElements(By.className("doubleClick")).size());
    }

}
