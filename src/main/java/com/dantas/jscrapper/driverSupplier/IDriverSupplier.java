package com.dantas.jscrapper.driverSupplier;

import org.openqa.selenium.WebDriver;

public interface IDriverSupplier {

    WebDriver get() throws DriverSupplierException;

}
