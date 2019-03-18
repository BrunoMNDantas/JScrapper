package com.github.brunomndantas.jscrapper.support.driverLoader;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class WaitDriverLoader extends DriverLoader {

    private TimeUnit timeUnit;
    public TimeUnit getTimeUnit() { return this.timeUnit; }

    private long time;
    public long getTime() { return time; }



    public WaitDriverLoader(TimeUnit timeUnit, long time) {
        this.timeUnit = timeUnit;
        this.time = time;
    }



    @Override
    protected void loadDriver(WebDriver driver) throws Exception {
        Thread.sleep(this.timeUnit.toMillis(this.time));
    }

}
