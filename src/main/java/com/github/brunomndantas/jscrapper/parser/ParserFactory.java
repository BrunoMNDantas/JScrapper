package com.github.brunomndantas.jscrapper.parser;

import com.github.brunomndantas.jscrapper.Utils;
import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.parser.IParser;
import com.github.brunomndantas.jscrapper.parser.annotation.Parser;

import java.lang.reflect.Field;

public class ParserFactory {

    public static IParser create(Class<?> klass, Field field) throws ScrapperException {
        Parser annotation = field.getAnnotation(Parser.class);

        if(annotation == null)
            throw new ScrapperException("No Parser found!");

        Class<? extends IParser> parserClass = annotation.value();
        return Utils.createInstance(parserClass);
    }

}
