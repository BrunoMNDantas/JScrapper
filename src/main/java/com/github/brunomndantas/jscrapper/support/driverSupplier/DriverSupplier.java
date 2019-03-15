package com.github.brunomndantas.jscrapper.support.driverSupplier;

import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import org.openqa.selenium.WebDriver;

public abstract class DriverSupplier implements IDriverSupplier {

    @Override
    public WebDriver get() throws DriverSupplierException {
        try {
          return getDriver();
        } catch (Exception e) {
            String msg = "Error getting Driver!";
            throw new DriverSupplierException(msg, e);
        }
    }



    protected abstract WebDriver getDriver() throws Exception;

}
