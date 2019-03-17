package com.github.brunomndantas.jscrapper.support.elementLoader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public class ClickElementLoader extends ElementLoader {

    @Override
    protected void loadElements(WebDriver driver, Collection<WebElement> elements) throws Exception {
        for(WebElement element : elements)
            element.click();
    }

}
