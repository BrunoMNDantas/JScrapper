package com.github.brunomndantas.jscrapper.selector;

import com.github.brunomndantas.jscrapper.Utils;
import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.selector.ISelector;
import com.github.brunomndantas.jscrapper.selector.annotation.Selector;

import java.lang.reflect.Field;

public class SelectorFactory {

    public static ISelector create(Class<?> klass, Field field) throws ScrapperException {
        Selector annotation = field.getAnnotation(Selector.class);

        if(annotation == null)
            throw new ScrapperException("No Selector found!");

        Class<? extends ISelector> selectorClass = annotation.value();
        return Utils.createInstance(selectorClass);
    }

}
