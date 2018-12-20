package com.dantas.jscrapper;

import com.dantas.jscrapper.driverAction.DriverActionException;
import com.dantas.jscrapper.driverAction.IDriverAction;
import com.dantas.jscrapper.driverSupplier.DriverSupplierException;
import com.dantas.jscrapper.driverSupplier.IDriverSupplier;
import org.openqa.selenium.WebDriver;

import java.util.Collection;
import java.util.LinkedList;

public class Scrapper {

    private IDriverSupplier driverSupplier;



    public Scrapper(IDriverSupplier driverSupplier) {
        this.driverSupplier = driverSupplier;
    }



    public WebDriver get(String url) throws ScrapperException {
        return get(url, new LinkedList<>());
    }

    public WebDriver get(String url, Collection<IDriverAction> actions) throws ScrapperException {
        WebDriver driver = null;
        try {
            driver = driverSupplier.get();

            driver.get(url);

            load(actions, driver);

            return driver;
        } catch (DriverSupplierException e) {
            throw new ScrapperException("Error getting Driver Supplier!", e);
        } catch (ScrapperException e) {
            driver.quit();
            throw e;
        }
    }

    private void load(Collection<IDriverAction> actions, WebDriver driver) throws ScrapperException {
        for(IDriverAction action : actions)
            load(action, driver);
    }

    private void load(IDriverAction action, WebDriver driver) throws ScrapperException {
        try {
            action.act(driver);
        } catch (DriverActionException e) {
            throw new ScrapperException("Error loading Driver with Action " + action.getName() + "!", e);
        }
    }

}
