package com.github.brunomndantas.jscrapper.support.driverSupplier;

import com.github.brunomndantas.jscrapper.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverSupplier extends DriverSupplier {

    public static final boolean DEFAULT_SILENT = true;
    public static final boolean DEFAULT_HEADLESS = true;



    private boolean headless;
    public boolean getHeadless() { return this.headless; }

    private boolean silent;
    public boolean getSilent () { return this.silent; }

    private String driverPath;
    public String getDriverPath() { return this.driverPath; }



    public FirefoxDriverSupplier(String driverPath, boolean silent, boolean headless) {
        this.driverPath = driverPath;
        this.silent = silent;
        this.headless = headless;
    }

    public FirefoxDriverSupplier(String driverPAth) {
        this(driverPAth, DEFAULT_SILENT, DEFAULT_HEADLESS);
    }



    @Override
    public WebDriver getDriver() throws Exception {
        FirefoxOptions options = new FirefoxOptions();

        String path = Utils.getAbsolutePath(driverPath);
        if(driverPath != null)
            System.setProperty("webdriver.gecko.driver", path);

        if(silent)
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

        if(headless)
            options.addArguments("--headless");

        return new FirefoxDriver(options);
    }

}
