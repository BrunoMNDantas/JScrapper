package com.dantas.jscrapper.driverSupplier;

import org.openqa.selenium.WebDriver;

public class DriverSupplier implements IDriverSupplier {

    private WebDriver driver;



    public DriverSupplier(WebDriver driver) {
        this.driver = driver;
    }



    @Override
    public WebDriver get() throws DriverSupplierException {
        return driver;
    }

}
