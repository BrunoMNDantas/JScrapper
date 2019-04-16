package com.github.brunomndantas.jscrapper.support.driverLoader;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class DoubleClickDriverLoaderTest {

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
