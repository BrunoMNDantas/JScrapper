package com.github.brunomndantas.jscrapper.scrapper.config;

import com.github.brunomndantas.jscrapper.Utils;
import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.config.ClassConfig;
import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.core.urlSupplier.IURLSupplier;
import com.github.brunomndantas.jscrapper.scrapper.annotation.SelectorType;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.DriverLoader;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.DriverSupplier;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.InstanceFactory;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.URLSupplier;
import com.github.brunomndantas.jscrapper.support.driverLoader.*;
import com.github.brunomndantas.jscrapper.support.driverSupplier.ChromeDriverSupplier;
import com.github.brunomndantas.jscrapper.support.driverSupplier.FirefoxDriverSupplier;
import com.github.brunomndantas.jscrapper.support.driverSupplier.PhantomDriverSupplier;
import com.github.brunomndantas.jscrapper.support.urlSupplier.FixedURLSupplier;
import org.openqa.selenium.By;

import java.util.Collection;
import java.util.LinkedList;

public class AnnotationClassConfig {

    public static ClassConfig getClassConfig(Class<?> klass) throws ScrapperException {
        try {
            ClassConfig config = new ClassConfig(klass);

            config.setInstanceFactory(getInstanceFactory(klass));
            config.setURLSupplier(getURLSupplier(klass));
            config.setDriverSupplier(getDriverSupplier(klass));
            config.setDriverLoader(getDriverLoader(klass));

            return config;
        } catch (ScrapperException e) {
            throw new ScrapperException("Error getting config for class:" + klass.getName() + "!");
        }
    }


    public static IInstanceFactory getInstanceFactory(Class<?> klass) throws ScrapperException {
        InstanceFactory annotation = klass.getDeclaredAnnotation(InstanceFactory.class);
        return getInstanceFactory(klass, annotation);
    }

    private static IInstanceFactory getInstanceFactory(Class<?> klass, InstanceFactory annotation) throws ScrapperException {
        if(annotation != null) {
            if(annotation.value() != IInstanceFactory.class)
                return Utils.createInstance(annotation.value());
        }

        return null;
    }


    public static IURLSupplier getURLSupplier(Class<?> klass) throws ScrapperException {
        URLSupplier annotation = klass.getDeclaredAnnotation(URLSupplier.class);
        return  getURLSupplier(klass, annotation);
    }

    private static IURLSupplier getURLSupplier(Class<?> klass, URLSupplier annotation) throws ScrapperException {
        if(annotation != null) {
            if(annotation.value() != IURLSupplier.class)
                return Utils.createInstance(annotation.value());

            return new FixedURLSupplier(annotation.url());
        }

        return null;
    }


    public static IDriverSupplier getDriverSupplier(Class<?> klass) throws ScrapperException {
        DriverSupplier annotation = klass.getDeclaredAnnotation(DriverSupplier.class);
        return getDriverSupplier(klass, annotation);
    }

    private static IDriverSupplier getDriverSupplier(Class<?> klass, DriverSupplier annotation) throws ScrapperException {
        if(annotation != null) {
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
        DriverLoader annotation = klass.getDeclaredAnnotation(DriverLoader.class);
        return getDriverLoader(klass, annotation);
    }

    public static IDriverLoader getDriverLoader(Class<?> klass, DriverLoader annotation) throws ScrapperException {
        if(annotation != null) {
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
