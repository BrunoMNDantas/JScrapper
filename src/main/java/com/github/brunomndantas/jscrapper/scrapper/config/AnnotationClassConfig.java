package com.github.brunomndantas.jscrapper.scrapper.config;

import com.github.brunomndantas.jscrapper.Utils;
import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.config.ClassConfig;
import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.scrapper.annotation.SelectorType;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.DriverLoader;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.DriverSupplier;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.InstanceFactory;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.Page;
import com.github.brunomndantas.jscrapper.support.driverLoader.*;
import com.github.brunomndantas.jscrapper.support.driverSupplier.ChromeDriverSupplier;
import com.github.brunomndantas.jscrapper.support.driverSupplier.FirefoxDriverSupplier;
import com.github.brunomndantas.jscrapper.support.driverSupplier.PhantomDriverSupplier;
import org.openqa.selenium.By;

import java.util.Collection;
import java.util.LinkedList;

public class AnnotationClassConfig {

    public static ClassConfig getClassConfig(Class<?> klass) throws ScrapperException {
        Page page = klass.getDeclaredAnnotation(Page.class);

        if(page == null)
            return new ClassConfig(klass);

        try {
            return getClassConfig(klass, page);
        } catch (ScrapperException e) {
            throw new ScrapperException("Error getting config for class:" + klass.getName() + "!");
        }
    }

    private static ClassConfig getClassConfig(Class<?> klass, Page annotation) throws ScrapperException {
        ClassConfig config = new ClassConfig(klass);

        config.setInstanceFactory(getInstanceFactory(klass));
        config.setDriverSupplier(getDriverSupplier(klass));
        config.setDriverLoader(getDriverLoader(klass));

        return config;
    }


    public static IInstanceFactory getInstanceFactory(Class<?> klass) throws ScrapperException {
        if(klass.getDeclaredAnnotation(Page.class) == null)
            return null;

        InstanceFactory annotation = klass.getDeclaredAnnotation(Page.class).instanceFactory();
        if(annotation.isUserDefined())
            if(annotation.value() != IInstanceFactory.class)
                return Utils.createInstance(annotation.value());

        return null;
    }


    public static IDriverSupplier getDriverSupplier(Class<?> klass) throws ScrapperException {
        if(klass.getDeclaredAnnotation(Page.class) == null)
            return null;

        DriverSupplier annotation = klass.getDeclaredAnnotation(Page.class).driverSupplier();
        if(annotation.isUserDefined()) {
            if(annotation.value() != IDriverSupplier.class)
                return Utils.createInstance(annotation.value());

            switch (annotation.driverType()) {
                case CHROME: return new ChromeDriverSupplier(annotation.driverLocation());
                case FIREFOX: return new FirefoxDriverSupplier(annotation.driverLocation());
                case PHANTOM: return new PhantomDriverSupplier(annotation.driverLocation());
                default: throw new ScrapperException("Unknown DriverType:" + annotation.driverType() + "!");
            }
        }

        return null;
    }


    public static IDriverLoader getDriverLoader(Class<?> klass) throws ScrapperException {
        if(klass.getDeclaredAnnotation(Page.class) == null)
            return null;

        DriverLoader annotation = klass.getDeclaredAnnotation(Page.class).driverLoader();
        return getDriverLoader(klass, annotation);
    }

    public static IDriverLoader getDriverLoader(Class<?> klass, DriverLoader annotation) throws ScrapperException {
        if(annotation.isUserDefined()) {
            if(annotation.value() != IDriverLoader.class)
                return Utils.createInstance(annotation.value());

            return getDriverLoader(klass, annotation.actions());
        }

        return null;
    }

    private static IDriverLoader getDriverLoader(Class<?> klass, DriverLoader.Action[] annotations) throws ScrapperException {
        Collection<IDriverLoader> loaders = new LinkedList<>();

        for(DriverLoader.Action actionAnnotation : annotations)
            loaders.add(getDriverLoader(klass, actionAnnotation));

        return new ComposedDriverLoader(loaders);
    }

    private static IDriverLoader getDriverLoader(Class<?> klass, DriverLoader.Action annotation) throws ScrapperException {
        if(annotation.clear().isUserDefined())
            return getDriverLoader(klass, annotation.clear());

        if(annotation.click().isUserDefined())
            return getDriverLoader(klass, annotation.click());

        if(annotation.doubleClick().isUserDefined())
            return getDriverLoader(klass, annotation.doubleClick());

        if(annotation.sendKeys().isUserDefined())
            return getDriverLoader(klass, annotation.sendKeys());

        if(annotation.submit().isUserDefined())
            return getDriverLoader(klass, annotation.submit());

        if(annotation.waitFor().isUserDefined())
            return getDriverLoader(klass, annotation.waitFor());

        if(annotation.waitVisible().isUserDefined())
            return getDriverLoader(klass, annotation.waitVisible());

        throw new ScrapperException("Unknown DriverLoader Action!");
    }

    private static ClearDriverLoader getDriverLoader(Class<?> klass, DriverLoader.Clear annotation) throws ScrapperException {
        By by = getBySelector(annotation.selectorType(), annotation.selector());
        return new ClearDriverLoader(by);
    }

    private static ClickDriverLoader getDriverLoader(Class<?> klass, DriverLoader.Click annotation) throws ScrapperException {
        By by = getBySelector(annotation.selectorType(), annotation.selector());
        return new ClickDriverLoader(by);
    }

    private static DoubleClickDriverLoader getDriverLoader(Class<?> klass, DriverLoader.DoubleClick annotation) throws ScrapperException {
        By by = getBySelector(annotation.selectorType(), annotation.selector());
        return new DoubleClickDriverLoader(by);
    }

    private static SendKeysDriverLoader getDriverLoader(Class<?> klass, DriverLoader.SendKeys annotation) throws ScrapperException {
        By by = getBySelector(annotation.selectorType(), annotation.selector());
        return new SendKeysDriverLoader(annotation.value(), by);
    }

    private static SubmitDriverLoader getDriverLoader(Class<?> klass, DriverLoader.Submit annotation) throws ScrapperException {
        By by = getBySelector(annotation.selectorType(), annotation.selector());
        return new SubmitDriverLoader(by);
    }

    private static WaitDriverLoader getDriverLoader(Class<?> klass, DriverLoader.Wait annotation) throws ScrapperException {
        return new WaitDriverLoader(annotation.unit(), annotation.value());
    }

    private static WaitVisibleDriverLoader getDriverLoader(Class<?> klass, DriverLoader.WaitVisible annotation) throws ScrapperException {
        By by = getBySelector(annotation.selectorType(), annotation.selector());
        return new WaitVisibleDriverLoader(annotation.unit(), annotation.value(), by);
    }


    public static By getBySelector(SelectorType type, String selector) throws ScrapperException {
        switch (type) {
            case ID: return By.id(selector);
            case NAME: return By.name(selector);
            case CLASS: return By.className(selector);
            case TAG: return By.tagName(selector);
            case LINK_TEXT: return By.linkText(selector);
            case PARTIAL_LINK_TEXT: return By.partialLinkText(selector);
            case CSS: return By.cssSelector(selector);
            case XPATH: return By.xpath(selector);
            default: throw new ScrapperException("Unknown SelectorType:" + type + "!");
        }
    }

}
