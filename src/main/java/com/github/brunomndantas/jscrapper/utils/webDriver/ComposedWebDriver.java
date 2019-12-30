package com.github.brunomndantas.jscrapper.utils.webDriver;

import org.openqa.selenium.*;

import java.util.List;
import java.util.Set;

public class ComposedWebDriver implements WebDriver, TakesScreenshot, JavascriptExecutor {

    protected WebDriver driver;
    public WebDriver getDriver() { return this.driver; }



    public ComposedWebDriver(WebDriver driver) {
        this.driver = driver;
    }



    @Override
    public void get(String url) {
        this.driver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return this.driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return this.driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return this.driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return this.driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return this.driver.getPageSource();
    }

    @Override
    public void close() {
        this.driver.close();
    }

    @Override
    public void quit() {
        this.driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return this.driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return this.driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return this.driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return this.driver.navigate();
    }

    @Override
    public Options manage() {
        return this.driver.manage();
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return ((TakesScreenshot)this.driver).getScreenshotAs(target);
    }

    @Override
    public Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor)this.driver).executeScript(script, args);
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        return ((JavascriptExecutor)this.driver).executeAsyncScript(script, args);
    }

}
