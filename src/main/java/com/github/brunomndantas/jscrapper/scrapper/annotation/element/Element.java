package com.github.brunomndantas.jscrapper.scrapper.annotation.element;

import com.github.brunomndantas.jscrapper.scrapper.annotation.page.DriverLoader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Element {

    DriverLoader driverLoader() default @DriverLoader(isUserDefined = false);

    Selector selector() default @Selector(isUserDefined = false);

    ElementLoader elementLoader() default @ElementLoader(isUserDefined = false);

    Parser parser() default @Parser(isUserDefined = false);

    Property property() default @Property(isUserDefined = false);

}
