package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.config.ScrapperConfig;
import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.Collection;

public class Scrapper {

    private ScrapperConfig config;
    public ScrapperConfig getConfig() { return this.config; }



    public Scrapper(ScrapperConfig config) {
        this.config = config;
    }



    public Object scrap(Class klass) throws ScrapperException {
        if(config.getConfigFor(klass) == null)
            throw new ScrapperException("No config found for class:" + klass.getName());

        ClassScrapper classScrapper = new ClassScrapper(config.getConfigFor(klass));

        Object instance = createInstance(classScrapper);

        WebDriver driver = scrapClass(classScrapper);

        FieldScrapper fieldScrapper;
        for(Field field : klass.getDeclaredFields()) {
            if(config.getConfigFor(field) == null)
                throw new ScrapperException("No config found for field:" + field.getName() + " of class:" + klass.getName() + "!");

            fieldScrapper = new FieldScrapper(config.getConfigFor(field));
            scrapField(fieldScrapper, driver, instance);
        }

        return instance;
    }

    private Object createInstance(ClassScrapper classScrapper) throws ScrapperException {
        try {
            return classScrapper.createInstance();
        } catch (InstanceFactoryException e) {
            throw new ScrapperException("Error creating instance of class:" + classScrapper.getConfig().getKlass().getName() + "!", e);
        }
    }

    private WebDriver scrapClass(ClassScrapper classScrapper) throws ScrapperException {
        try {
            WebDriver driver = classScrapper.getDriver();

            classScrapper.loadDriver(driver);

            return driver;
        } catch (Exception e) {
            throw new ScrapperException("Error scrapping class:" + classScrapper.getConfig().getKlass().getName() + "!", e);
        }
    }

    private void scrapField(FieldScrapper fieldScrapper, WebDriver driver, Object instance) throws ScrapperException {
        try {
            fieldScrapper.loadDriver(driver);

            Collection<WebElement> elements = fieldScrapper.selectElements(driver);

            fieldScrapper.loadElements(driver, elements);

            Object value = fieldScrapper.parseElements(driver, elements);

            fieldScrapper.setValue(instance, value);
        } catch (Exception e) {
            throw new ScrapperException("Error scrapping field:" + fieldScrapper.getConfig().getField().getName() + " of class:" + instance.getClass().getName() + "!", e);
        }
    }

}
