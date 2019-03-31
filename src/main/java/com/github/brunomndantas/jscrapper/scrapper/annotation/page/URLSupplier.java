package com.github.brunomndantas.jscrapper.scrapper.annotation.page;

import com.github.brunomndantas.jscrapper.core.urlSupplier.IURLSupplier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface URLSupplier {

    String url() default "";

    Class<? extends IURLSupplier> value() default IURLSupplier.class;

}
