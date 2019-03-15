package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.config.ClassConfig;
import com.github.brunomndantas.jscrapper.core.driverLoader.DriverLoaderException;
import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;
import org.openqa.selenium.WebDriver;

public class ClassScrapper {

    private ClassConfig config;
    public ClassConfig getConfig() { return this.config; }



    public ClassScrapper(ClassConfig config) throws ScrapperException {
        this.config = config;

        if(config.getInstanceFactory() == null)
            throw new ScrapperException("No InstanceFactory found for class:" + config.getKlass().getName() + "!");

        if(config.getDriverSupplier() == null)
            throw new ScrapperException("No DriverSupplier found for class:" + config.getKlass().getName() + "!");

        if(config.getDriverLoader() == null)
            throw new ScrapperException("No DriverLoader found for class:" + config.getKlass().getName() + "!");
    }



    public Object createInstance() throws InstanceFactoryException {
        return this.config.getInstanceFactory().create();
    }

    public WebDriver getDriver() throws DriverSupplierException {
        return this.config.getDriverSupplier().get();
    }

    public void loadDriver(WebDriver driver) throws DriverLoaderException {
        this.config.getDriverLoader().load(driver);
    }

}
