package com.dantas.jscrapper.driverSupplier;

import org.openqa.selenium.WebDriver;

public class SingletonDriverSupplier implements IDriverSupplier {

    private IDriverSupplier driverSupplier;
    public IDriverSupplier getDriverSupplier() { return this.driverSupplier; }

    private WebDriver driver;
    public WebDriver getDriver() { return this.driver; }



    public SingletonDriverSupplier(IDriverSupplier driverSupplier) {
        this.driverSupplier = driverSupplier;
    }



    @Override
    public WebDriver get() throws DriverSupplierException {
        if(this.driver == null)
            this.driver = this.driverSupplier.get();

        return this.driver;
    }

}
