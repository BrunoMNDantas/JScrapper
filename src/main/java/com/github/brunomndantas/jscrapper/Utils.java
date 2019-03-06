package com.github.brunomndantas.jscrapper;

import com.github.brunomndantas.jscrapper.core.ScrapperException;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;

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


    public static String getAbsolutePath(String path) throws ScrapperException {
        URL url = ClassLoader.getSystemResource(path);

        if(url != null)
            return url.getPath();

        File file = new File(path);

        if(!file.exists())
            throw new ScrapperException("Path not found:" + path + "!");

        return path;
    }

}
