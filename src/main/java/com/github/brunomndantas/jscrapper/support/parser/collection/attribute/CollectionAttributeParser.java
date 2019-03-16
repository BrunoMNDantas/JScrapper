package com.github.brunomndantas.jscrapper.support.parser.collection.attribute;

import com.github.brunomndantas.jscrapper.support.parser.collection.CollectionParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class CollectionAttributeParser extends CollectionParser {

    protected String attribute;
    public String getAttribute() { return this.attribute; }

    protected Object defaultValue;
    public Object getDefaultValue() { return this.defaultValue; }



    protected CollectionAttributeParser(String attribute, Object defaultValue) {
        this.attribute = attribute;
        this.defaultValue = defaultValue;
    }



    @Override
    protected Object parseElement(WebDriver driver, WebElement element) throws Exception {
        if(element == null || element.getAttribute(this.attribute) == null)
            return this.defaultValue;

        return parseElement(element);
    }



    protected abstract Object parseElement(WebElement element) throws Exception;

}
