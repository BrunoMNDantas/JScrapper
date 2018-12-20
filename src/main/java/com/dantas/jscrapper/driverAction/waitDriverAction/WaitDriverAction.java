package com.dantas.jscrapper.driverAction.waitDriverAction;

import com.dantas.jscrapper.driverAction.DriverAction;
import com.dantas.jscrapper.driverAction.DriverActionException;
import org.openqa.selenium.WebDriver;

public abstract class WaitDriverAction extends DriverAction {

    public WaitDriverAction(String name) {
        super(name);
    }

    public WaitDriverAction() {
        this("");
    }



    @Override
    public void internalAct(WebDriver driver) throws DriverActionException {
        try {
            await(driver);
        } catch (Exception e) {
            throw new DriverActionException("Error while waiting!", e);
        }
    }



    protected abstract void await(WebDriver driver) throws DriverActionException;

}
