package com.github.brunomndantas.jscrapper.scrapper.annotation.page;

import com.github.brunomndantas.jscrapper.core.urlSupplier.IURLSupplier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface URLSupplier {

    String url() default "";

    Class<? extends IURLSupplier> value() default IURLSupplier.class;

    /*Ignore this property. This property it is just to distinguish between userDefined annotation or default value annotation*/
    @Deprecated boolean isUserDefined() default true;

}
