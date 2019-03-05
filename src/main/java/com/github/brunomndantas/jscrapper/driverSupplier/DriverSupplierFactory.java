package com.github.brunomndantas.jscrapper.driverSupplier;

import com.github.brunomndantas.jscrapper.Utils;
import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.driverSupplier.annotation.DriverSupplier;

public class DriverSupplierFactory {

    public static IDriverSupplier create(Class<?> klass) throws ScrapperException {
        DriverSupplier annotation = klass.getAnnotation(DriverSupplier.class);

        if(annotation == null)
            throw new ScrapperException("No DriverSupplier found!");

        Class<? extends IDriverSupplier> driverSupplierClass = annotation.value();
        return Utils.createInstance(driverSupplierClass);
    }

}
