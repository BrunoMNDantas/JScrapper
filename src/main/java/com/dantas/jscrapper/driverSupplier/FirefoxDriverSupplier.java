package com.dantas.jscrapper.driverSupplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverSupplier implements IDriverSupplier {


    public static final String DEFAULT_DRIVER_PATH = null;
    public static final boolean DEFAULT_SILENT = true;
    public static final boolean DEFAULT_HEADLESS = true;



    private FirefoxOptions options;



    public FirefoxDriverSupplier(FirefoxOptions options, String driverPath, boolean silent) {
        this.options = options;

        if(driverPath != null)
            System.setProperty("webdriver.gecko.driver", driverPath);

        if(silent)
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
    }

    public FirefoxDriverSupplier(String driverPath, boolean silent, boolean headless) {
        this(new FirefoxOptions(), driverPath, silent);

        if(headless)
            this.options.addArguments("--headless");
    }

    public FirefoxDriverSupplier(){
        this(DEFAULT_DRIVER_PATH, DEFAULT_SILENT, DEFAULT_HEADLESS);
    }



    @Override
    public WebDriver get() throws DriverSupplierException {
        return new FirefoxDriver(this.options);
    }

}
