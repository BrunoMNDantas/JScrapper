package com.github.brunomndantas.jscrapper.elementLoader.annotation;

import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ElementLoader {

    Class<? extends IElementLoader> value();

}
