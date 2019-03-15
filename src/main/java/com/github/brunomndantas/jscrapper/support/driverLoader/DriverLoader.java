package com.github.brunomndantas.jscrapper.support.driverLoader;

import com.github.brunomndantas.jscrapper.core.driverLoader.DriverLoaderException;
import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import org.openqa.selenium.WebDriver;

public abstract class DriverLoader implements IDriverLoader {

    @Override
    public void load(WebDriver driver) throws DriverLoaderException {
        try {
            loadDriver(driver);
        } catch (Exception e) {
            String msg = "Error loading Driver on url:" + driver.getCurrentUrl() + "!";
            throw new DriverLoaderException(msg, e);
        }
    }



    protected abstract void loadDriver(WebDriver driver) throws Exception;

}
