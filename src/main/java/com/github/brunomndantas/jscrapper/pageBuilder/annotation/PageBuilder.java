package com.github.brunomndantas.jscrapper.pageBuilder.annotation;

import com.github.brunomndantas.jscrapper.core.pageBuilder.IPageBuilder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface PageBuilder {

    Class<? extends IPageBuilder> value();

}
