package com.github.brunomndantas.jscrapper.scrapper.annotation.element;

import com.github.brunomndantas.jscrapper.core.property.IProperty;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Property {

    Class<? extends IProperty> value() default IProperty.class;

    /*Ignore this property. This property it is just to distinguish between userDefined annotation or default value annotation*/
    @Deprecated boolean isUserDefined() default true;

}
