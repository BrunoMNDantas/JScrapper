package com.github.brunomndantas.jscrapper.support.elementLoader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class WaitElementLoaderLoader extends ElementLoader {

    private TimeUnit timeUnit;
    public TimeUnit getTimeUnit() { return this.timeUnit; }

    private long time;
    public long getTime() { return time; }



    public WaitElementLoaderLoader(TimeUnit timeUnit, long time) {
        this.timeUnit = timeUnit;
        this.time = time;
    }



    @Override
    protected void loadElements(WebDriver driver, Collection<WebElement> elements) throws Exception {
        for(WebElement element : elements)
            Thread.sleep(this.timeUnit.toMillis(this.time));
    }

}
