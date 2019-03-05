package com.github.brunomndantas.jscrapper.parser.annotation;

import com.github.brunomndantas.jscrapper.core.parser.IParser;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Parser {

    Class<? extends IParser> value();

}
