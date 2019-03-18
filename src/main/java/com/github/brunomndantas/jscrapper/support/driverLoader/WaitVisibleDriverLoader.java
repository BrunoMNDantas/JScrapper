package com.github.brunomndantas.jscrapper.support.driverLoader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class WaitVisibleDriverLoader extends DriverLoader {

    public static final long SLEEP_TIME = 1000;



    private TimeUnit timeUnit;
    public TimeUnit getTimeUnit() { return this.timeUnit; }

    private long time;
    public long getTime() { return time; }

    private By by;
    public By getBy() { return this.by; }



    public WaitVisibleDriverLoader(TimeUnit timeUnit, long time, By by) {
        this.timeUnit = timeUnit;
        this.time = time;
        this.by = by;
    }



    @Override
    protected void loadDriver(WebDriver driver) throws Exception {
        for(WebElement element : driver.findElements(this.by))
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
