package com.github.brunomndantas.jscrapper.property.annotation;

import com.github.brunomndantas.jscrapper.core.property.IProperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Property {

    Class<? extends IProperty> value();

}
