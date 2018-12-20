package com.dantas.jscrapper.driverAction.waitDriverAction;

import com.dantas.jscrapper.driverAction.DriverActionException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class VisibleWaitDriverAction extends WaitDriverAction {

    private static final long SLEEP_TIME = 500;



    private By selector;
    public By getSelector() { return this.selector; }

    private long timeout;
    private long getTimeout() { return this.timeout; }



    public VisibleWaitDriverAction(By selector, long timeout, String name){
        super(name);

        this.selector = selector;
        this.timeout = timeout;
    }

    public VisibleWaitDriverAction(By selector, long timeout) {
        this(selector, timeout, "");
    }



    @Override
    protected void await(WebDriver driver) throws DriverActionException {
        long timeout = System.currentTimeMillis() + this.timeout;

        while(true) {
            if(this.isVisible(driver))
                return;

            if(timeout < System.currentTimeMillis())
                throw new DriverActionException("Element " + this.selector + " is not visible after " + this.timeout + " milliseconds!");

            this.sleep();
        }
    }

    private boolean isVisible(WebDriver driver) throws DriverActionException {
        List<WebElement> elements = driver.findElements(this.selector);
        WebElement element = elements == null || elements.isEmpty() ? null : elements.get(0);
        return  element != null && element.isEnabled() && element.isDisplayed();
    }

    private void sleep() throws DriverActionException {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            throw new DriverActionException("Error while sleeping!", e);
        }
    }

}
