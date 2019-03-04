package com.github.brunomndantas.jscrapper.core.driverSupplier;

import org.openqa.selenium.WebDriver;

public interface IDriverSupplier {

    WebDriver get() throws DriverSupplierException;

}
