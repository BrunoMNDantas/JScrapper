package com.github.brunomndantas.jscrapper.support.parser.single.text;

import com.github.brunomndantas.jscrapper.support.parser.single.SingleParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class SingleTextParser extends SingleParser {

    protected Object defaultValue;
    public Object getDefaultValue() { return this.defaultValue; }



    protected SingleTextParser(Object defaultValue) {
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
