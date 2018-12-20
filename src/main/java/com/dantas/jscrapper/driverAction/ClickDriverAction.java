package com.dantas.jscrapper.driverAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClickDriverAction extends DriverAction {

    private final By selector;
    public By getSelector() { return this.selector; }



    public ClickDriverAction(By selector, String name) {
        super(name);
        this.selector = selector;
    }

    public ClickDriverAction(By selector) {
        this(selector, "");
    }



    @Override
    public void internalAct(WebDriver driver) throws DriverActionException {
        if(driver.findElements(selector).size() == 0)
            throw new DriverActionException("Could not find element with selector:" + selector + "!");

        WebElement element = driver.findElement(selector);

        if(!element.isDisplayed())
            throw new DriverActionException("Element with selector:" + selector + " is not displayed!");

        if(!element.isEnabled())
            throw new DriverActionException("Element with selector:" + selector + " is not enabled!");

        element.click();
    }

}
