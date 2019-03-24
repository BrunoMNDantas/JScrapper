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



    public FieldScrapper(FieldConfig config) throws ScrapperException {
        this.config = config;

        if(config.getDriverLoader() == null)
            throw new ScrapperException("No DriverLoader found for field:" + config.getField().getName() + " of class:" + config.getField().getDeclaringClass().getName() + "!");

        if(config.getSelector() == null)
            throw new ScrapperException("No Selector found for field:" + config.getField().getName() + " of class:" + config.getField().getDeclaringClass().getName() + "!");

        if(config.getElementLoader() == null)
            throw new ScrapperException("No ElementLoader found for field:" + config.getField().getName() + " of class:" + config.getField().getDeclaringClass().getName() + "!");

        if(config.getParser() == null)
            throw new ScrapperException("No Parser found for field:" + config.getField().getName() + " of class:" + config.getField().getDeclaringClass().getName() + "!");

        if(config.getProperty() == null)
            throw new ScrapperException("No Property found for field:" + config.getField().getName() + " of class:" + config.getField().getDeclaringClass().getName() + "!");
    }



    public void loadDriver(WebDriver driver) throws DriverLoaderException {
        this.config.getDriverLoader().load(driver);
    }

    public Collection<WebElement> selectElements(WebDriver driver) throws SelectorException {
        return this.config.getSelector().select(driver);
    }

    public void loadElements(WebDriver driver, Collection<WebElement> elements) throws ElementLoaderException {
        this.config.getElementLoader().load(driver, elements);
    }

    public Object parseElements(WebDriver driver, Collection<WebElement> elements) throws ParserException {
        return this.config.getParser().parse(driver, elements);
    }

    public Object getValue(Object instance) throws PropertyException {
        return this.config.getProperty().get(instance);
    }

    public void setValue(Object instance, Object value) throws PropertyException {
        this.config.getProperty().set(instance, value);
    }

}
