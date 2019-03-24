package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.config.ScrapperConfig;
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

        Object instance = classScrapper.createInstance();

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

    private WebDriver scrapClass(ClassScrapper classScrapper) throws ScrapperException {
        WebDriver driver = classScrapper.getDriver();

        classScrapper.loadDriver(driver);

        return driver;
    }

    private void scrapField(FieldScrapper fieldScrapper, WebDriver driver, Object instance) throws ScrapperException {
        fieldScrapper.loadDriver(driver);

        Collection<WebElement> elements = fieldScrapper.selectElements(driver);

        fieldScrapper.loadElements(driver, elements);

        Object value = fieldScrapper.parseElements(driver, elements);

        fieldScrapper.setValue(instance, value);
    }

}
