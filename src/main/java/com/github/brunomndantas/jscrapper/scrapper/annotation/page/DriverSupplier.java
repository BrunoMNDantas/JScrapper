package com.github.brunomndantas.jscrapper.scrapper.annotation.page;

import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface DriverSupplier {

    enum DriverType {
        CHROME,
        FIREFOX,
        PHANTOM
    }



    String driverLocation() default "";

    DriverType driverType() default DriverType.CHROME;

    Class<? extends IDriverSupplier> value() default IDriverSupplier.class;

}
