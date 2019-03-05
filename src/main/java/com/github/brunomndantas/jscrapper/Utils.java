package com.github.brunomndantas.jscrapper;

import com.github.brunomndantas.jscrapper.core.ScrapperException;

import java.lang.reflect.Constructor;

public class Utils {

    public static <T> T createInstance(Class<T> klass) throws ScrapperException {
        Constructor<T> constructor = getEmptyConstructor(klass);

        if(constructor == null)
            throw new ScrapperException("Class:" + klass.getName() + " has no empty constructor!");

        constructor.setAccessible(true);

        try {
            return constructor.newInstance();
        } catch (Exception e) {
            throw new ScrapperException("Error creating instance of class:" + klass.getName(), e);
        }
    }

    public static <T> Constructor<T> getEmptyConstructor(Class<T> klass) {
        for(Constructor<?> constructor : klass.getDeclaredConstructors())
            if(constructor.getParameterCount() == 0)
                return (Constructor<T>) constructor;

        return null;
    }

}
