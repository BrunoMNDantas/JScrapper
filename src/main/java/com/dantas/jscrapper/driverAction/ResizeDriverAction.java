package com.dantas.jscrapper.driverAction;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class ResizeDriverAction extends DriverAction {

    private int width;
    public int getWidth() { return this.width; }

    private int height;
    public int getHeight() { return this.height; }



    public ResizeDriverAction(int width, int height, String name) {
        super(name);
        this.width = width;
        this.height = height;
    }

    public ResizeDriverAction(int width, int height) {
        this(width, height, "");
    }



    @Override
    public void internalAct(WebDriver driver) throws DriverActionException {
        driver.manage().window().setSize(new Dimension(this.width, this.height));
    }

}
