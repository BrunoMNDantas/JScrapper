package com.github.brunomndantas.jscrapper.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public class Scrapper {

    public void scrap(Page page) throws ScrapperException {
        WebDriver driver = scrapPage(page);

        for(Element element : page.getElements())
            scrapElement(page, driver, element);
    }

    protected WebDriver scrapPage(Page page) throws ScrapperException {
        try {
            WebDriver driver = page.getDriverSupplier().get();

            driver.get(page.getUrl());

            if(page.getPageLoader() != null)
                page.getPageLoader().load(driver);

            return driver;
        } catch (Exception e) {
            throw new ScrapperException("Error scrapping Page:" + page.getId() + "!", e);
        }
    }

    protected void scrapElement(Page page, WebDriver driver, Element element) throws ScrapperException {
        try {
            if(element.getPageLoader() != null)
                element.getPageLoader().load(driver);

            Collection<WebElement> elements = element.getSelector().select(driver);

            if(element.getElementLoader() != null)
                element.getElementLoader().load(driver, elements);

            Object value = element.getParser().parse(driver, elements);

            element.getProperty().set(value);
        } catch (Exception e) {
            throw new ScrapperException("Error scrapping Element:" + element.getId() + " if Page:" + page.getId() + "!", e);
        }
    }

}
