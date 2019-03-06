package com.github.brunomndantas.jscrapper.driverSupplier.chrome;

import com.github.brunomndantas.jscrapper.Utils;
import com.github.brunomndantas.jscrapper.driverSupplier.DriverSupplier;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverSupplier extends DriverSupplier {

    public static final boolean DEFAULT_SILENT = true;
    public static final boolean DEFAULT_HEADLESS = true;



    private boolean headless;
    public boolean getHeadless() { return this.headless; }

    private boolean silent;
    public boolean getSilent () { return this.silent; }

    private String driverPath;
    public String getDriverPath() { return this.driverPath; }



    public ChromeDriverSupplier(String driverPath, boolean silent, boolean headless) {
        this.driverPath = driverPath;
        this.silent = silent;
        this.headless = headless;
    }

    public ChromeDriverSupplier(String driverPath) {
        this(driverPath, DEFAULT_SILENT, DEFAULT_HEADLESS);
    }



    @Override
    public WebDriver getDriver() throws Exception {
        ChromeOptions options = new ChromeOptions();

        String path = Utils.getAbsolutePath(driverPath);
        if(driverPath != null)
            System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, path);

        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, Boolean.toString(silent));

        if(headless)
            options.addArguments("--headless");

        return new ChromeDriver(options);
    }

}
