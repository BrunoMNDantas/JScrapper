package com.github.brunomndantas.jscrapper.driverSupplier.phantom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface PhantomDriver {

    String path();

    boolean silent() default PhantomDriverSupplier.DEFAULT_SILENT;

}
