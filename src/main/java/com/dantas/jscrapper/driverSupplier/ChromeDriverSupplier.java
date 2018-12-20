package com.dantas.jscrapper.driverSupplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverSupplier implements IDriverSupplier {

    public static final String DEFAULT_DRIVER_PATH = null;
    public static final boolean DEFAULT_SILENT = true;
    public static final boolean DEFAULT_HEADLESS = true;



    private ChromeOptions options;



    public ChromeDriverSupplier(ChromeOptions options, String driverPath, boolean silent) {
        this.options = options;

        if(driverPath != null)
            System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, driverPath);

        if(silent)
            System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
    }

    public ChromeDriverSupplier(String driverPath, boolean silent, boolean headless) {
        this(new ChromeOptions(), driverPath, silent);

        if(headless)
            this.options.addArguments("--headless");
    }

    public ChromeDriverSupplier(){
        this(DEFAULT_DRIVER_PATH, DEFAULT_SILENT, DEFAULT_HEADLESS);
    }



    @Override
    public WebDriver get() throws DriverSupplierException {
        return new ChromeDriver(this.options);
    }

}
