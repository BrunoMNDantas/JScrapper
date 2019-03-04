package com.github.brunomndantas.jscrapper.core.elementLoader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public interface IElementLoader {

    void load(WebDriver driver, Collection<WebElement> elements) throws ElementLoaderException;

}
