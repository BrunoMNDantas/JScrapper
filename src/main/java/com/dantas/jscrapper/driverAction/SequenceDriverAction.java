package com.dantas.jscrapper.driverAction;

import org.openqa.selenium.WebDriver;

import java.util.Collection;

public class SequenceDriverAction extends DriverAction {

    private Collection<IDriverAction> actions;
    public Collection<IDriverAction> getActions() { return this.actions; }



    public SequenceDriverAction(Collection<IDriverAction> actions, String name) {
        super(name);
        this.actions = actions;
    }

    public SequenceDriverAction(Collection<IDriverAction> actions) {
        this(actions, "");
    }



    @Override
    public void internalAct(WebDriver driver) throws DriverActionException {
        for(IDriverAction action : actions) {
            try {
                action.act(driver);
            } catch (DriverActionException e) {
                throw new DriverActionException("Error loading with Action " + action.getName() + " of Sequence " + super.getName() + "!", e);
            }
        }
    }

}
