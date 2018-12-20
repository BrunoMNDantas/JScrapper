package com.dantas.jscrapper.driverAction.waitDriverAction;

import com.dantas.jscrapper.driverAction.DriverActionException;
import org.openqa.selenium.WebDriver;

public class UrlWaitDriverAction extends WaitDriverAction {

    public enum Policy {
        EQUALS,
        CONTAINS,
        STARTS,
        ENDS,
        REGEX
    }



    private static final long SLEEP_TIME = 500;



    private String url;
    public String getUrl() { return this.url; }

    private Policy policy;
    public Policy getPolicy() { return this.policy; }

    private long timeout;
    private long getTimeout() { return this.timeout; }



    public UrlWaitDriverAction(String url, Policy policy, long timeout, String name) {
        super(name);

        this.url = url;
        this.policy = policy;
        this.timeout = timeout;
    }

    public UrlWaitDriverAction(String url, Policy policy, long timeout) {
        this(url, policy, timeout, "");
    }



    @Override
    protected void await(WebDriver driver) throws DriverActionException {
        long timeout = System.currentTimeMillis() + this.timeout;

        String url;
        while(true) {
            url = driver.getCurrentUrl();

            switch (this.policy) {
                case EQUALS: if(url.equals(this.url)) return;
                case CONTAINS: if(url.contains(this.url)) return;
                case STARTS: if(url.startsWith(this.url)) return;
                case ENDS: if(url.endsWith(this.url)) return;
                case REGEX: if(url.matches(this.url)) return;
            }

            if(timeout < System.currentTimeMillis())
                throw new DriverActionException("Url is not " + this.url + " after " + this.timeout + " milliseconds!");

            this.sleep();
        }
    }

    private void sleep() throws DriverActionException {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            throw new DriverActionException("Error while sleeping!", e);
        }
    }

}
