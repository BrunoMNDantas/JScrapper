package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.config.FieldConfig;
import com.github.brunomndantas.jscrapper.core.driverLoader.DriverLoaderException;
import com.github.brunomndantas.jscrapper.core.elementLoader.ElementLoaderException;
import com.github.brunomndantas.jscrapper.core.parser.ParserException;
import com.github.brunomndantas.jscrapper.core.property.PropertyException;
import com.github.brunomndantas.jscrapper.core.selector.SelectorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public class FieldScrapper {

    private final Logger LOGGER = LogManager.getLogger(FieldScrapper.class);



    private FieldConfig config;
    public FieldConfig getConfig() { return this.config; }

    private String fieldName;
    private String className;



    public FieldScrapper(FieldConfig config) throws ScrapperException {
        this.config = config;
        this.fieldName = config.getField().getName();
        this.className = config.getField().getDeclaringClass().getName();

        if(config.getDriverLoader() == null)
            throw new ScrapperException("No DriverLoader found for field:" + fieldName + " of class:" + className + "!");

        if(config.getSelector() == null)
            throw new ScrapperException("No Selector found for field:" + fieldName + " of class:" + className + "!");

        if(config.getElementLoader() == null)
            throw new ScrapperException("No ElementLoader found for field:" + fieldName + " of class:" + className + "!");

        if(config.getParser() == null)
            throw new ScrapperException("No Parser found for field:" + fieldName + " of class:" + className + "!");

        if(config.getProperty() == null)
            throw new ScrapperException("No Property found for field:" + fieldName + " of class:" + className + "!");
    }



    public void loadDriver(WebDriver driver) throws ScrapperException {
        try {
            LOGGER.info("Loading driver for field:" + this.fieldName + " of class:" + this.className + "!");

            this.config.getDriverLoader().load(driver);

            LOGGER.info("Driver for field:" + this.fieldName + " of class:" + this.className + " loaded!");
        } catch (DriverLoaderException e) {
            throw new ScrapperException("Error loading driver for field:" + this.fieldName + " of class:" + this.className + "!", e);
        }
    }

    public Collection<WebElement> selectElements(WebDriver driver) throws ScrapperException {
        try {
            LOGGER.info("Selecting elements for field:" + this.fieldName + " of class:" + this.className + "!");

            Collection<WebElement> elements = this.config.getSelector().select(driver);

            LOGGER.info("Elements for field:" + this.fieldName + " of class:" + this.className + " selected[" + (elements==null? 0 : elements.size())+ "]!");

            return elements;
        } catch (SelectorException e) {
            throw new ScrapperException("Error selecting elements for field:" + this.fieldName + " of class:" + this.className + "!", e);
        }
    }

    public void loadElements(WebDriver driver, Collection<WebElement> elements) throws ScrapperException {
        try {
            LOGGER.info("Loading elements for field:" + this.fieldName + " of class:" + this.className + "!");

            this.config.getElementLoader().load(driver, elements);

            LOGGER.info("Element for field:" + this.fieldName + " of class:" + this.className + " loaded!");
        } catch (ElementLoaderException e) {
            throw new ScrapperException("Error loading elements for field:" + this.fieldName + " of class:" + this.className + "!", e);
        }
    }

    public Object parseElements(WebDriver driver, Collection<WebElement> elements) throws ScrapperException {
        try {
            LOGGER.info("Parsing elements for field:" + this.fieldName + " of class:" + this.className + "!");

            Object value = this.config.getParser().parse(driver, elements);

            LOGGER.info("Elements for field:" + this.fieldName + " of class:" + this.className + " parsed[" + value + "]!");

            return value;
        } catch (ParserException e) {
            throw new ScrapperException("Error parsing elements for field:" + this.fieldName + " of class:" + this.className + "!", e);
        }
    }

    public Object getValue(Object instance) throws ScrapperException {
        try {
            LOGGER.info("Getting value of property for field:" + this.fieldName + " of class:" + this.className + "!");

            Object value = this.config.getProperty().get(instance);

            LOGGER.info("Value of property for field:" + this.fieldName + " of class:" + this.className + " received[" + value + "]!");

            return value;
        } catch (PropertyException e) {
            throw new ScrapperException("Error getting value of property for field:" + this.fieldName + " of class:" + this.className + "!", e);
        }
    }

    public void setValue(Object instance, Object value) throws ScrapperException {
        try {
            LOGGER.info("Setting value[" + value + "] of property for field:" + this.fieldName + " of class:" + this.className + "!");

            this.config.getProperty().set(instance, value);

            LOGGER.info("Value of property for field:" + this.fieldName + " of class:" + this.className + " set!");
        } catch (PropertyException e) {
            throw new ScrapperException("Error setting value of property for field:" + this.fieldName + " of class:" + this.className + "!", e);
        }
    }

}
