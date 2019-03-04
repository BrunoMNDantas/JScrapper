package com.github.brunomndantas.jscrapper.core.driverLoader;

import org.openqa.selenium.WebDriver;

public interface IDriverLoader {

    void load(WebDriver driver) throws DriverLoaderException;

}
