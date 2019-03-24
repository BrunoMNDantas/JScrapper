package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.config.FieldConfig;
import com.github.brunomndantas.jscrapper.core.driverLoader.DriverLoaderException;
import com.github.brunomndantas.jscrapper.core.elementLoader.ElementLoaderException;
import com.github.brunomndantas.jscrapper.core.parser.ParserException;
import com.github.brunomndantas.jscrapper.core.property.PropertyException;
import com.github.brunomndantas.jscrapper.core.selector.SelectorException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public class FieldScrapper {

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
            this.config.getDriverLoader().load(driver);
        } catch (DriverLoaderException e) {
            throw new ScrapperException("Error loading driver for field:" + this.fieldName + " of class:" + this.className + "!", e);
        }
    }

    public Collection<WebElement> selectElements(WebDriver driver) throws ScrapperException {
        try {
            return this.config.getSelector().select(driver);
        } catch (SelectorException e) {
            throw new ScrapperException("Error selecting elements for field:" + this.fieldName + " of class:" + this.className + "!", e);
        }
    }

    public void loadElements(WebDriver driver, Collection<WebElement> elements) throws ScrapperException {
        try {
            this.config.getElementLoader().load(driver, elements);
        } catch (ElementLoaderException e) {
            throw new ScrapperException("Error loading elements for field:" + this.fieldName + " of class:" + this.className + "!", e);
        }
    }

    public Object parseElements(WebDriver driver, Collection<WebElement> elements) throws ScrapperException {
        try {
            return this.config.getParser().parse(driver, elements);
        } catch (ParserException e) {
            throw new ScrapperException("Error parsing elements for field:" + this.fieldName + " of class:" + this.className + "!", e);
        }
    }

    public Object getValue(Object instance) throws ScrapperException {
        try {
            return this.config.getProperty().get(instance);
        } catch (PropertyException e) {
            throw new ScrapperException("Error getting value of property for field:" + this.fieldName + " of class:" + this.className + "!", e);
        }
    }

    public void setValue(Object instance, Object value) throws ScrapperException {
        try {
            this.config.getProperty().set(instance, value);
        } catch (PropertyException e) {
            throw new ScrapperException("Error setting value of property for field:" + this.fieldName + " of class:" + this.className + "!", e);
        }
    }

}
