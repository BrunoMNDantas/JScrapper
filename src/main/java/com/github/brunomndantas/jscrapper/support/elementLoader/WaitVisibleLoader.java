package com.github.brunomndantas.jscrapper.support.elementLoader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class WaitVisibleLoader extends ElementLoader {

    public static final long SLEEP_TIME = 1000;



    private TimeUnit timeUnit;
    public TimeUnit getTimeUnit() { return this.timeUnit; }

    private long time;
    public long getTime() { return time; }



    public WaitVisibleLoader(TimeUnit timeUnit, long time) {
        this.timeUnit = timeUnit;
        this.time = time;
    }



    @Override
    protected void loadElements(WebDriver driver, Collection<WebElement> elements) throws Exception {
        for(WebElement element : elements)
            waitFor(element);
    }

    private void waitFor(WebElement element) throws Exception {
        long endTime = System.currentTimeMillis() + this.timeUnit.toMillis(this.time);

        while(true) {
            if(element.isEnabled() && element.isDisplayed())
                return;

            if(System.currentTimeMillis() >= endTime)
                return;

            Thread.sleep(SLEEP_TIME);
        }
    }

}
