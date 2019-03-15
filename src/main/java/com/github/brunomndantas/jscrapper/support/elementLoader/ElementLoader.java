package com.github.brunomndantas.jscrapper.support.elementLoader;

import com.github.brunomndantas.jscrapper.core.elementLoader.ElementLoaderException;
import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public abstract class ElementLoader implements IElementLoader {

    @Override
    public void load(WebDriver driver, Collection<WebElement> elements) throws ElementLoaderException {
        try {
            loadElements(driver, elements);
        } catch (Exception e) {
            String msg = "Error loading Elements on url:" + driver.getCurrentUrl() + "!";
            throw new ElementLoaderException(msg, e);
        }
    }



    protected abstract void loadElements(WebDriver driver, Collection<WebElement> elements) throws Exception;

}
