package com.github.brunomndantas.jscrapper.support.elementLoader;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class DoubleClickElementLoaderTest {

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
