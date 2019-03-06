package com.github.brunomndantas.jscrapper.driverSupplier.phantom;

import com.github.brunomndantas.jscrapper.Utils;
import com.github.brunomndantas.jscrapper.driverSupplier.DriverSupplier;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhantomDriverSupplier extends DriverSupplier {

    public static final boolean DEFAULT_SILENT = true;



    private boolean silent;
    public boolean getSilent () { return this.silent; }

    private String driverPath;
    public String getDriverPath() { return this.driverPath; }



    public PhantomDriverSupplier(String driverPath, boolean silent) {
        this.driverPath = driverPath;
        this.silent = silent;
    }

    public PhantomDriverSupplier(String driverPath){
        this(driverPath, DEFAULT_SILENT);
    }



    @Override
    public WebDriver getDriver() throws Exception {
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();

        String path = Utils.getAbsolutePath(driverPath);
        if(driverPath != null)
            capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, path);

        if(silent) {
            capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, Collections.singletonList("--webdriver-loglevel=NONE"));
            Logger.getLogger(PhantomJSDriverService.class.getName()).setLevel(Level.OFF);
        }

        return new PhantomJSDriver(capabilities);
    }

}
