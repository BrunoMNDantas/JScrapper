package com.github.brunomndantas.jscrapper.scrapper.annotation.page;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Page {

    String url() default "";

    InstanceFactory instanceFactory() default @InstanceFactory(isUserDefined = false);

    DriverSupplier driverSupplier() default @DriverSupplier(isUserDefined = false);

    DriverLoader driverLoader() default @DriverLoader(isUserDefined = false);

}
