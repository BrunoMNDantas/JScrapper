package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.config.ClassConfig;
import com.github.brunomndantas.jscrapper.core.driverLoader.DriverLoaderException;
import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class ClassScrapper {

    private final Logger LOGGER = LogManager.getLogger(ClassScrapper.class);



    private ClassConfig config;
    public ClassConfig getConfig() { return this.config; }

    private String className;



    public ClassScrapper(ClassConfig config) throws ScrapperException {
        this.config = config;
        this.className = config.getKlass().getName();

        if(config.getInstanceFactory() == null)
            throw new ScrapperException("No InstanceFactory found for class:" + className + "!");

        if(config.getDriverSupplier() == null)
            throw new ScrapperException("No DriverSupplier found for class:" + className + "!");

        if(config.getDriverLoader() == null)
            throw new ScrapperException("No DriverLoader found for class:" + className + "!");
    }



    public Object createInstance() throws ScrapperException {
        try {
            LOGGER.info("Creating instance of class:" + this.className + "!");

            Object instance = this.config.getInstanceFactory().create();

            LOGGER.info("Instance of class:" + this.className + " created [" + instance + "]!");

            return instance;
        } catch (InstanceFactoryException e) {
            throw new ScrapperException("Error creating instance of class:" + this.className + "!", e);
        }
    }

    public WebDriver getDriver() throws ScrapperException {
        try {
            LOGGER.info("Getting driver from class:" + this.className + "!");

            WebDriver driver = this.config.getDriverSupplier().get();

            LOGGER.info("Driver for class:" + this.className + " received[" + driver + "]!");

            return driver;
        } catch (DriverSupplierException e) {
            throw new ScrapperException("Error getting driver for class:" + this.className + "!", e);
        }
    }

    public void loadDriver(WebDriver driver) throws ScrapperException {
        try {
            LOGGER.info("Loading driver for class:" + this.className + "!");

            this.config.getDriverLoader().load(driver);

            LOGGER.info("Driver for class:" + this.className + " loaded!");
        } catch (DriverLoaderException e) {
            throw new ScrapperException("Error loading driver of class:" + this.className + "!", e);
        }
    }

}
