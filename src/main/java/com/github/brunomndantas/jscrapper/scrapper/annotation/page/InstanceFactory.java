package com.github.brunomndantas.jscrapper.scrapper.annotation.page;

import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface InstanceFactory {

    Class<? extends IInstanceFactory> value() default IInstanceFactory.class;

    /*Ignore this property. This property it is just to distinguish between userDefined annotation or default value annotation*/
    @Deprecated boolean isUserDefined() default true;

}
