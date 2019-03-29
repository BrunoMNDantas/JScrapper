package com.github.brunomndantas.jscrapper.scrapper.annotation.page;

import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DriverSupplier {

    enum DriverType {
        CHROME,
        FIREFOX,
        PHANTOM
    }



    String driverLocation() default "";

    DriverType driverType() default DriverType.CHROME;

    Class<? extends IDriverSupplier> value() default IDriverSupplier.class;

    /*Ignore this property. This property it is just to distinguish between userDefined annotation or default value annotation*/
    @Deprecated boolean isUserDefined() default true;

}
