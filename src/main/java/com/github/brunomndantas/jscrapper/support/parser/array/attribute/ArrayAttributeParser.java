package com.github.brunomndantas.jscrapper.support.parser.array.attribute;

import com.github.brunomndantas.jscrapper.support.parser.array.ArrayParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class ArrayAttributeParser extends ArrayParser {

    protected String attribute;
    public String getAttribute() { return this.attribute; }

    protected Object defaultValue;
    public Object getDefaultValue() { return this.defaultValue; }



    protected ArrayAttributeParser(String attribute, Object defaultValue) {
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
