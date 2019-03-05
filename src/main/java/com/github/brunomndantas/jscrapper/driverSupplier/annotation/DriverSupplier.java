package com.github.brunomndantas.jscrapper.driverSupplier.annotation;

import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface DriverSupplier {

    Class<? extends IDriverSupplier> value();

}
