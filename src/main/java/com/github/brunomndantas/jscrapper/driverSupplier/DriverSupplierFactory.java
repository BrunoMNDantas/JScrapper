package com.github.brunomndantas.jscrapper.driverSupplier;

import com.github.brunomndantas.jscrapper.Utils;
import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.driverSupplier.annotation.DriverSupplier;
import com.github.brunomndantas.jscrapper.driverSupplier.chrome.ChromeDriver;
import com.github.brunomndantas.jscrapper.driverSupplier.chrome.ChromeDriverSupplier;
import com.github.brunomndantas.jscrapper.driverSupplier.firefox.FirefoxDriver;
import com.github.brunomndantas.jscrapper.driverSupplier.firefox.FirefoxDriverSupplier;
import com.github.brunomndantas.jscrapper.driverSupplier.phantom.PhantomDriver;
import com.github.brunomndantas.jscrapper.driverSupplier.phantom.PhantomDriverSupplier;

public class DriverSupplierFactory {

    public static IDriverSupplier create(Class<?> klass) throws ScrapperException {
        IDriverSupplier supplier = createUserDefinedSupplier(klass);

        if(supplier != null)
            return supplier;

        supplier = createDefaultSupplier(klass);

        if(supplier != null)
            return supplier;

        throw new ScrapperException("No DriverSupplier found!");
    }

    private static IDriverSupplier createUserDefinedSupplier(Class<?> klass) throws ScrapperException {
        DriverSupplier annotation = klass.getAnnotation(DriverSupplier.class);

        if(annotation == null)
            return null;

        Class<? extends IDriverSupplier> driverSupplierClass = annotation.value();
        return Utils.createInstance(driverSupplierClass);
    }

    private static IDriverSupplier createDefaultSupplier(Class<?> klass) {
        IDriverSupplier supplier = createChromeSupplier(klass);

        if(supplier != null)
            return supplier;

        supplier = createFirefoxSupplier(klass);

        if(supplier != null)
            return supplier;

        return createPhantomSupplier(klass);
    }

    private static ChromeDriverSupplier createChromeSupplier(Class<?> klass) {
        ChromeDriver annotation = klass.getDeclaredAnnotation(ChromeDriver.class);

        if(annotation == null)
            return null;

        return new ChromeDriverSupplier(annotation.path(), annotation.silent(), annotation.headless());
    }

    private static FirefoxDriverSupplier createFirefoxSupplier(Class<?> klass) {
        FirefoxDriver annotation = klass.getDeclaredAnnotation(FirefoxDriver.class);

        if(annotation == null)
            return null;

        return new FirefoxDriverSupplier(annotation.path(), annotation.silent(), annotation.headless());
    }

    private static PhantomDriverSupplier createPhantomSupplier(Class<?> klass) {
        PhantomDriver annotation = klass.getDeclaredAnnotation(PhantomDriver.class);

        if(annotation == null)
            return null;

        return new PhantomDriverSupplier(annotation.path(), annotation.silent());
    }

}
