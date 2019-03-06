package com.github.brunomndantas.jscrapper.driverSupplier.firefox;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface FirefoxDriver {

    String path();

    boolean headless() default FirefoxDriverSupplier.DEFAULT_HEADLESS;

    boolean silent() default FirefoxDriverSupplier.DEFAULT_SILENT;

}
