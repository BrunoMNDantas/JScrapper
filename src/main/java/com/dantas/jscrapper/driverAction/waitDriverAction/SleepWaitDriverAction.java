package com.dantas.jscrapper.driverAction.waitDriverAction;

import com.dantas.jscrapper.driverAction.DriverActionException;
import org.openqa.selenium.WebDriver;

public class SleepWaitDriverAction extends WaitDriverAction {

    private long time;
    public long getTime() { return this.time; }



    public SleepWaitDriverAction(long time, String name) {
        super(name);
        this.time = time;
    }

    public SleepWaitDriverAction(long time) {
        this(time, "");
    }



    @Override
    protected void await(WebDriver driver) throws DriverActionException {
        try {
            Thread.sleep(this.time);
        } catch (Exception e) {
            throw new DriverActionException("Error while waiting!", e);
        }
    }

}
