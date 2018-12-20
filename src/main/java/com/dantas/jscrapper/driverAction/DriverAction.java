package com.dantas.jscrapper.driverAction;

import org.openqa.selenium.WebDriver;

public abstract class DriverAction implements IDriverAction {

    private String name;
    @Override public String getName() {
        return this.name;
    }



    public DriverAction(String name) {
        this.name = name;
    }



    @Override
    public void act(WebDriver driver) throws DriverActionException {
        try {
            this.internalAct(driver);
        } catch (Exception e) {
            throw new DriverActionException("Error on Action " + this.name + "!", e);
        }
    }


    protected abstract void internalAct(WebDriver driver) throws DriverActionException;

}
