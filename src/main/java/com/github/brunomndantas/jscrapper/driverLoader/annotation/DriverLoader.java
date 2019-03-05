package com.github.brunomndantas.jscrapper.driverLoader.annotation;

import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface DriverLoader {

    Class<? extends IDriverLoader> value();

}
