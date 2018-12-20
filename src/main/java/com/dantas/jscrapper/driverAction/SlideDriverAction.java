package com.dantas.jscrapper.driverAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class SlideDriverAction extends DriverAction {

    private By selector;
    public By getSelector() { return this.selector; }

    private int xOffset;
    public int getXOffset(){ return this.xOffset; }

    private int yOffset;
    public int getYOffset() { return this.yOffset; }



    public SlideDriverAction(By selector, int xOffset, int yOffset, String name) {
        super(name);
        this.selector = selector;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public SlideDriverAction(By selector, int xOffset, int yOffset) {
        this(selector, xOffset, yOffset, "");
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

        Actions move = new Actions(driver);
        Action action = move.dragAndDropBy(element, this.xOffset, this.yOffset).build();
        action.perform();
    }

}
