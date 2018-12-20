package com.dantas.jscrapper.driverAction.ifDriverAction;

import com.dantas.jscrapper.driverAction.DriverAction;
import com.dantas.jscrapper.driverAction.DriverActionException;
import com.dantas.jscrapper.driverAction.IDriverAction;
import org.openqa.selenium.WebDriver;

public abstract class IfDriverAction extends DriverAction {

    private IDriverAction action;
    public IDriverAction getAction() { return this.action; }



    public IfDriverAction(IDriverAction action, String name) {
        super(name);
        this.action = action;
    }

    public IfDriverAction(IDriverAction action) {
        this(action, "");
    }



    @Override
    public void internalAct(WebDriver driver) throws DriverActionException {
        if(this.check(driver))
            this.action.act(driver);
    }


    protected abstract boolean check(WebDriver driver) throws DriverActionException;

}
