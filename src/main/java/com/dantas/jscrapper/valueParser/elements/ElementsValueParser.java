package com.dantas.jscrapper.valueParser.elements;

import com.dantas.jscrapper.valueParser.ValueParser;
import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class ElementsValueParser<K> extends ValueParser<Collection<K>> {

    private By selector;
    public By getSelector() { return this.selector; }



    public ElementsValueParser(By selector, String name) {
        super(name);

        this.selector = selector;
    }

    public ElementsValueParser(By selector) {
        this(selector, "");
    }



    @Override
    protected Collection<K> internalParse(WebDriver driver) throws ValueParserException {
        List<WebElement> elements = driver.findElements(this.selector);

        Collection<K> values = new LinkedList<>();
        String elementValue;
        for(WebElement element : elements) {
            elementValue = element.getText();

            if(elementValue == null || elementValue.isEmpty())
                throw new ValueParserException("One of the elements with selector:" + this.selector + " doesn't have value!");

            values.add(this.parseElement(elementValue));
        }

        return values;
    }

    protected abstract K parseElement(String value) throws ValueParserException;

}
