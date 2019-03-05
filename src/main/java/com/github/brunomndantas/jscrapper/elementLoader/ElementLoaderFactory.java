package com.github.brunomndantas.jscrapper.elementLoader;

import com.github.brunomndantas.jscrapper.Utils;
import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;
import com.github.brunomndantas.jscrapper.elementLoader.annotation.ElementLoader;

import java.lang.reflect.Field;

public class ElementLoaderFactory {

    public static IElementLoader create(Class<?> klass, Field field) throws ScrapperException {
        ElementLoader annotation = field.getAnnotation(ElementLoader.class);

        if(annotation == null)
            throw new ScrapperException("No ElementLoader found!");

        Class<? extends IElementLoader> elementLoaderClass = annotation.value();
        return Utils.createInstance(elementLoaderClass);
    }

}
