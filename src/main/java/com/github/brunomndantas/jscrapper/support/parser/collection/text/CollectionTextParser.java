package com.github.brunomndantas.jscrapper.support.parser.collection.text;

import com.github.brunomndantas.jscrapper.support.parser.collection.CollectionParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class CollectionTextParser extends CollectionParser {

    protected Object defaultValue;
    public Object getDefaultValue() { return this.defaultValue; }



    protected CollectionTextParser(Object defaultValue) {
        this.defaultValue = defaultValue;
    }



    @Override
    protected Object parseElement(WebDriver driver, WebElement element) throws Exception {
        if(element == null || element.getText() == null)
            return this.defaultValue;

        return parseElement(element);
    }



    protected abstract Object parseElement(WebElement element) throws Exception;

}
