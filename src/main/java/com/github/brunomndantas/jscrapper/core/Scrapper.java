package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;
import com.github.brunomndantas.jscrapper.core.pageBuilder.IPageBuilder;
import com.github.brunomndantas.jscrapper.core.pageBuilder.PageBuilderException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public class Scrapper {

    private IInstanceFactory instanceFactory;
    public IInstanceFactory getInstanceFactory() { return this.instanceFactory; }

    private IPageBuilder pageBuilder;
    public IPageBuilder getPageBuilder() { return this.pageBuilder; }



    public Scrapper(IInstanceFactory instanceFactory, IPageBuilder pageBuilder) {
        this.instanceFactory = instanceFactory;
        this.pageBuilder = pageBuilder;
    }



    public <T> T scrap(Class<T> klass) throws ScrapperException {
        T instance = createInstance(klass);

        Page page = buildPage(instance);

        WebDriver driver = scrapPage(page);

        for(Element element : page.getElements())
            scrapElement(page, driver, element);

        return instance;
    }

    protected <T> T createInstance(Class<T> klass) throws ScrapperException {
        try {
            return this.instanceFactory.create(klass);
        } catch (InstanceFactoryException e) {
            throw new ScrapperException("Error creating instance of " + klass.getName() + "!", e);
        }
    }

    protected Page buildPage(Object instance) throws ScrapperException {
        try {
            return this.pageBuilder.build(instance);
        } catch (PageBuilderException e) {
            throw new ScrapperException("Error building Page for " +  instance.getClass().getName() + "!", e);
        }
    }

    protected WebDriver scrapPage(Page page) throws ScrapperException {
        try {
            WebDriver driver = page.getDriverSupplier().get();

            driver.get(page.getUrl());

            if(page.getDriverLoader() != null)
                page.getDriverLoader().load(driver);

            return driver;
        } catch (Exception e) {
            throw new ScrapperException("Error scrapping Page:" + page.getId() + "!", e);
        }
    }

    protected void scrapElement(Page page, WebDriver driver, Element element) throws ScrapperException {
        try {
            if(element.getDriverLoader() != null)
                element.getDriverLoader().load(driver);

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
