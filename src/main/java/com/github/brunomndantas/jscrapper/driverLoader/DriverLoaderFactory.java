package com.github.brunomndantas.jscrapper.driverLoader;

import com.github.brunomndantas.jscrapper.Utils;
import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.driverLoader.annotation.DriverLoader;

import java.lang.reflect.Field;

public class DriverLoaderFactory {

    public static IDriverLoader create(Class<?> klass) throws ScrapperException {
        DriverLoader annotation = klass.getAnnotation(DriverLoader.class);

        if(annotation == null)
            throw new ScrapperException("No DriverLoader found!");

        Class<? extends IDriverLoader> driverLoaderClass = annotation.value();
        return Utils.createInstance(driverLoaderClass);
    }

    public static IDriverLoader create(Class<?> klass, Field field) throws ScrapperException {
        DriverLoader annotation = field.getAnnotation(DriverLoader.class);

        if(annotation == null)
            throw new ScrapperException("No DriverLoader found!");

        Class<? extends IDriverLoader> driverLoaderClass = annotation.value();
        return Utils.createInstance(driverLoaderClass);
    }

}
