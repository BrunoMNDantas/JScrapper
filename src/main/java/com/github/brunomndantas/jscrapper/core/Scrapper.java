package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.config.ScrapperConfig;
import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;
import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;
import com.github.brunomndantas.jscrapper.core.parser.IParser;
import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.selector.ISelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.Collection;

public class Scrapper {

    public Object scrap(ScrapperConfig config, Class klass) throws ScrapperException {
        if(config.getConfigFor(klass) == null)
            throw new ScrapperException("No config found for class:" + klass.getName() + "!");

        Object instance = createInstance(config, klass);

        WebDriver driver = scrapClass(config, klass);

        for(Field field : klass.getDeclaredFields()) {
            if(config.getConfigFor(field) == null)
                throw new ScrapperException("No config found for field:" + field.getName() + " of class:" + klass.getName() + "!");

            scrapField(config, field, driver, instance);
        }

        return instance;
    }

    public Object createInstance(ScrapperConfig config, Class klass) throws ScrapperException {
        IInstanceFactory instanceFactory = config.getInstanceFactoryFor(klass);

        if(instanceFactory == null)
            throw new ScrapperException("No InstanceFactory found for class:" + klass.getName() + "!");

        try {
            return instanceFactory.create();
        } catch (InstanceFactoryException e) {
            throw new ScrapperException("Error creating instance of class:" + klass.getName() + "!", e);
        }
    }

    public WebDriver scrapClass(ScrapperConfig config, Class klass) throws ScrapperException {
        IDriverSupplier driverSupplier = config.getDriverSupplierFor(klass);
        IDriverLoader driverLoader = config.getDriverLoaderFor(klass);

        if(driverSupplier == null)
            throw new ScrapperException("No DriverSupplier found for class:" + klass.getName() + "!");

        if(driverLoader == null)
            throw new ScrapperException("No DriverLoader found for class:" + klass.getName() + "!");

        try {
            WebDriver driver = driverSupplier.get();

            driverLoader.load(driver);

            return driver;
        } catch (Exception e) {
            throw new ScrapperException("Error scrapping class:" + klass.getName() + "!", e);
        }
    }

    public void scrapField(ScrapperConfig config, Field field, WebDriver driver, Object instance) throws ScrapperException {
        IDriverLoader driverLoader = config.getDriverLoaderFor(field);
        ISelector selector = config.getSelectorFor(field);
        IElementLoader elementLoader = config.getElementLoaderFor(field);
        IParser parser = config.getParserFor(field);
        IProperty property = config.getPropertyFor(field);

        if(driverLoader == null)
            throw new ScrapperException("No DriverLoader found for field:" + field.getName() + " of class:" + instance.getClass().getName() + "!");

        if(selector == null)
            throw new ScrapperException("No Selector found for field:" + field.getName() + " of class:" + instance.getClass().getName() + "!");

        if(elementLoader == null)
            throw new ScrapperException("No ElementLoader found for field:" + field.getName() + " of class:" + instance.getClass().getName() + "!");

        if(parser == null)
            throw new ScrapperException("No Parser found for field:" + field.getName() + " of class:" + instance.getClass().getName() + "!");

        if(property == null)
            throw new ScrapperException("No Property found for field:" + field.getName() + " of class:" + instance.getClass().getName() + "!");

        try {
            driverLoader.load(driver);

            Collection<WebElement> elements = selector.select(driver);

            elementLoader.load(driver, elements);

            Object value = parser.parse(driver, elements);

            property.set(instance, value);
        } catch (Exception e) {
            throw new ScrapperException("Error scrapping field:" + field.getName() + " of class:" + instance.getClass().getName() + "!", e);
        }
    }

}
