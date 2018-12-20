package com.dantas.jscrapper.driverAction.ifDriverAction;

import com.dantas.jscrapper.driverAction.DriverActionException;
import com.dantas.jscrapper.driverAction.IDriverAction;
import org.openqa.selenium.WebDriver;

public class IfUrlDriverAction extends IfDriverAction {

    public enum Policy {
        EQUALS,
        CONTAINS,
        STARTS,
        ENDS,
        REGEX
    }



    private String url;
    public String getUrl() { return this.url; }

    private Policy policy;
    public Policy getPolicy() { return this.policy; }



    public IfUrlDriverAction(String url, Policy policy, IDriverAction action, String name) {
        super(action, name);

        this.url = url;
        this.policy = policy;
    }

    public IfUrlDriverAction(String url, Policy policy, IDriverAction action) {
        this(url, policy, action, "");
    }



    @Override
    protected boolean check(WebDriver driver) throws DriverActionException {
        String url = driver.getCurrentUrl();

        switch (this.policy) {
            case EQUALS: return url.equals(this.url);
            case CONTAINS: return url.contains(this.url);
            case STARTS: return url.startsWith(this.url);
            case ENDS: return url.endsWith(this.url);
            case REGEX: return url.matches(this.url);
        }

        throw new DriverActionException("Unknown Policy " + this.policy);
    }

}
