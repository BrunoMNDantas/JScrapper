package com.dantas.jscrapper.driverAction;

import org.openqa.selenium.WebDriver;

public interface IDriverAction {

    String getName();
    void act(WebDriver driver) throws DriverActionException;

}
