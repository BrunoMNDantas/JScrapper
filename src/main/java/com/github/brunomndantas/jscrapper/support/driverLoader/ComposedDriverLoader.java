package com.github.brunomndantas.jscrapper.support.driverLoader;

import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import org.openqa.selenium.WebDriver;

import java.util.Collection;

public class ComposedDriverLoader extends DriverLoader {

    private Collection<IDriverLoader> loaders;
    public Collection<IDriverLoader> getLoaders() { return this.loaders; }



    public ComposedDriverLoader(Collection<IDriverLoader> loaders) {
        this.loaders = loaders;

    }



    @Override
    protected void loadDriver(WebDriver driver) throws Exception {
        for(IDriverLoader loader : this.loaders)
            loader.load(driver);
    }

}
