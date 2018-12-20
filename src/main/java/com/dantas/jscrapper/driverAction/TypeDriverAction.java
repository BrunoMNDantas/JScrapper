package com.dantas.jscrapper.driverAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TypeDriverAction extends DriverAction {

    private final By selector;
    public By getSelector() { return this.selector; }

    private final String text;
    public String getText() { return text; }



    public TypeDriverAction(By selector, String text, String name) {
        super(name);
        this.selector = selector;
        this.text = text;
    }

    public TypeDriverAction(By selector, String text) {
        this(selector, text, "");
    }



    @Override
    public void internalAct(WebDriver driver) throws DriverActionException {
        if(driver.findElements(selector).size() == 0)
            throw new DriverActionException("Could not find element with selector:" + selector + "!");

        WebElement element = driver.findElement(selector);

        if(!element.isDisplayed())
            throw new DriverActionException("Element with selector:" + selector + " is not displayed!");

        element.sendKeys(text);
    }

}
