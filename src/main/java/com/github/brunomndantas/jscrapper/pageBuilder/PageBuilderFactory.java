package com.github.brunomndantas.jscrapper.pageBuilder;

import com.github.brunomndantas.jscrapper.Utils;
import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.pageBuilder.IPageBuilder;
import com.github.brunomndantas.jscrapper.pageBuilder.annotation.PageBuilder;

public class PageBuilderFactory {

    public static IPageBuilder create(Class<?> klass) throws ScrapperException {
        PageBuilder annotation = klass.getAnnotation(PageBuilder.class);

        if(annotation == null)
            throw new ScrapperException("No PageBuilder found!");

        Class<? extends IPageBuilder> pageBuilderClass = annotation.value();
        return Utils.createInstance(pageBuilderClass);
    }

}
