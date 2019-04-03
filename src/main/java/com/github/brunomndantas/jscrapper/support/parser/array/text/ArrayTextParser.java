package com.github.brunomndantas.jscrapper.support.parser.array.text;

import com.github.brunomndantas.jscrapper.support.parser.array.ArrayParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class ArrayTextParser extends ArrayParser {

    protected Object defaultValue;
    public Object getDefaultValue() { return this.defaultValue; }



    protected ArrayTextParser(Class<?> klass, Object defaultValue) {
        super(klass);
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
