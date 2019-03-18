package com.github.brunomndantas.jscrapper.support.elementLoader;

import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public class ComposedElementLoader extends ElementLoader {

    private Collection<IElementLoader> loaders;
    public Collection<IElementLoader> getLoaders() { return this.loaders; }



    public ComposedElementLoader(Collection<IElementLoader> loaders) {
        this.loaders = loaders;

    }



    @Override
    protected void loadElements(WebDriver driver, Collection<WebElement> elements) throws Exception {
        for(IElementLoader loader : this.loaders)
            loader.load(driver, elements);
    }

}
