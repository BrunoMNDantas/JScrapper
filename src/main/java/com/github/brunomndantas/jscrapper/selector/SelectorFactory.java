package com.github.brunomndantas.jscrapper.selector;

import com.github.brunomndantas.jscrapper.Utils;
import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.selector.ISelector;
import com.github.brunomndantas.jscrapper.selector.css.annotation.CSSSelector;
import com.github.brunomndantas.jscrapper.selector.annotation.Selector;
import com.github.brunomndantas.jscrapper.selector.xpath.annotation.XPathSelector;

import java.lang.reflect.Field;

public class SelectorFactory {

    public static ISelector create(Class<?> klass, Field field) throws ScrapperException {
        ISelector selector  = createUserDefinedSelector(klass, field);

        if(selector != null)
            return selector;

        selector  = createDefaultSelector(klass, field);

        if(selector != null)
            return selector;

        throw new ScrapperException("No Selector found!");
    }

    private static ISelector createUserDefinedSelector(Class<?> klass, Field field) throws ScrapperException {
        com.github.brunomndantas.jscrapper.selector.annotation.Selector annotation = field.getAnnotation(Selector.class);

        if(annotation == null)
            return null;

        Class<? extends ISelector> selectorClass = annotation.value();
        return Utils.createInstance(selectorClass);
    }

    private static ISelector createDefaultSelector(Class<?> klass, Field field) {
        ISelector selector = createCSSSelector(klass, field);

        if(selector != null)
            return selector;

        selector = createXPathSelector(klass, field);

        if(selector != null)
            return selector;

        return null;
    }

    private static ISelector createCSSSelector(Class<?> klass, Field field) {
         CSSSelector annotation = field.getAnnotation(CSSSelector.class);

         if(annotation == null)
             return null;

         return new com.github.brunomndantas.jscrapper.selector.css.CSSSelector(annotation.value());

    }

    private static ISelector createXPathSelector(Class<?> klass, Field field) {
         XPathSelector annotation = field.getAnnotation(XPathSelector.class);

        if(annotation == null)
            return null;

        return new com.github.brunomndantas.jscrapper.selector.xpath.XPathSelector(annotation.value());
    }

}
