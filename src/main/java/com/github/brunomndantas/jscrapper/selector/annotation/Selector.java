package com.github.brunomndantas.jscrapper.selector.annotation;

import com.github.brunomndantas.jscrapper.core.selector.ISelector;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Selector {

    Class<? extends ISelector> value();

}
