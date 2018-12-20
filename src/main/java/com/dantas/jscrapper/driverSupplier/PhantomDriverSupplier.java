package com.dantas.jscrapper.driverSupplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhantomDriverSupplier implements IDriverSupplier {

    public static final String DEFAULT_CHROME_PATH = null;
    public static final boolean DEFAULT_SILENT = true;



    private DesiredCapabilities capabilities;



    public PhantomDriverSupplier(DesiredCapabilities capabilities) {
        this.capabilities = capabilities;
    }

    public PhantomDriverSupplier(String driverPath, boolean silent) {
        this(DesiredCapabilities.phantomjs());

        if(driverPath != null)
            this.capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, driverPath);

        if(silent) {
            this.capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, Collections.singletonList("--webdriver-loglevel=NONE"));
            Logger.getLogger(PhantomJSDriverService.class.getName()).setLevel(Level.OFF);
        }
    }

    public PhantomDriverSupplier(){
        this(DEFAULT_CHROME_PATH, DEFAULT_SILENT);
    }



    @Override
    public WebDriver get() throws DriverSupplierException {
        return new PhantomJSDriver(this.capabilities);
    }

}
