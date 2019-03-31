package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.config.ClassConfig;
import com.github.brunomndantas.jscrapper.core.config.FieldConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public class CoreScrapper {

    private final Logger LOGGER = LogManager.getLogger(CoreScrapper.class);



    public Object scrap(ClassConfig classConfig) throws ScrapperException {
        LOGGER.info("Scrapping class:" + classConfig.getKlass().getName() + "!");

        try {
            ClassScrapper classScrapper = new ClassScrapper(classConfig);
            return scrap(classScrapper);
        } catch (Exception e) {
            throw new ScrapperException("Error scrapping class:" + classConfig.getKlass().getName() + "!", e);
        }
    }

    private Object scrap(ClassScrapper classScrapper) throws ScrapperException {
        Object instance = classScrapper.createInstance();

        WebDriver driver = scrapClass(classScrapper);

        FieldScrapper fieldScrapper;
        for(FieldConfig fieldConfig : classScrapper.getConfig().getFieldsConfig()) {
            fieldScrapper = new FieldScrapper(fieldConfig);
            scrapField(fieldScrapper, driver, instance);
        }

        return instance;
    }

    private WebDriver scrapClass(ClassScrapper classScrapper) throws ScrapperException {
        WebDriver driver = classScrapper.getDriver();

        String url = classScrapper.getURL();
        if(url != null)
            driver.get(url);

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
