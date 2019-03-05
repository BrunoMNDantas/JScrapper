package com.github.brunomndantas.jscrapper.instanceFactory.annotation;

import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface InstanceFactory {

    Class<? extends IInstanceFactory> value();

}
