package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public class Scrapper {

    public Object scrap(Page page) throws ScrapperException {
        Object instance = createInstance(page);

        WebDriver driver = scrapPage(page);

        for(Element element : page.getElements())
            scrapElement(page, driver, instance, element);

        return instance;
    }

    protected Object createInstance(Page page) throws ScrapperException {
        try {
            return page.getInstanceFactory().create();
        } catch (InstanceFactoryException e) {
            throw new ScrapperException("Error creating instance of Page" + page.getId() + "!", e);
        }
    }

    protected WebDriver scrapPage(Page page) throws ScrapperException {
        try {
            WebDriver driver = page.getDriverSupplier().get();
            
            if(page.getDriverLoader() != null)
                page.getDriverLoader().load(driver);

            return driver;
        } catch (Exception e) {
            throw new ScrapperException("Error scrapping Page:" + page.getId() + "!", e);
        }
    }

    protected void scrapElement(Page page, WebDriver driver, Object instance, Element element) throws ScrapperException {
        try {
            if(element.getDriverLoader() != null)
                element.getDriverLoader().load(driver);

            Collection<WebElement> elements = element.getSelector().select(driver);

            if(element.getElementLoader() != null)
                element.getElementLoader().load(driver, elements);

            Object value = element.getParser().parse(driver, elements);

            element.getProperty().set(instance, value);
        } catch (Exception e) {
            throw new ScrapperException("Error scrapping Element:" + element.getId() + " of Page:" + page.getId() + "!", e);
        }
    }

}
