package com.github.brunomndantas.jscrapper.scrapper.config;

import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.config.ClassConfig;
import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.support.instanceFactory.EmptyConstructorInstanceFactory;

public class DefaultClassConfig {

    public static ClassConfig getClassConfig(Class<?> klass) throws ScrapperException {
        ClassConfig config = new ClassConfig(klass);

        config.setInstanceFactory(getInstanceFactory(klass));
        config.setDriverSupplier(getDriverSupplier(klass));
        config.setDriverLoader(getDriverLoader(klass));

        return config;
    }

    public static IInstanceFactory getInstanceFactory(Class klass) throws ScrapperException {
        return new EmptyConstructorInstanceFactory(klass);
    }

    public static IDriverSupplier getDriverSupplier(Class klass) throws ScrapperException {
        return null;
    }

    public static IDriverLoader getDriverLoader(Class klass) throws ScrapperException {
        return (driver) -> { };
    }

}
