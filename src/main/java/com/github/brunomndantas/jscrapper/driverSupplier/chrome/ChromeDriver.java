package com.github.brunomndantas.jscrapper.driverSupplier.chrome;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ChromeDriver {

    String path();

    boolean headless() default ChromeDriverSupplier.DEFAULT_HEADLESS;

    boolean silent() default ChromeDriverSupplier.DEFAULT_SILENT;

}
