package com.github.brunomndantas.jscrapper.support.parser.collection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CollectionWebElementParser extends CollectionParser {

    @Override
    protected Object parseElement(WebDriver driver, WebElement element) throws Exception {
        return element;
    }

}
