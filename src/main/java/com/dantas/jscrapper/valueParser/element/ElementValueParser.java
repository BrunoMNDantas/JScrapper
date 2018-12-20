package com.dantas.jscrapper.valueParser.element;

import com.dantas.jscrapper.valueParser.ValueParser;
import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class ElementValueParser<K> extends ValueParser<K> {

    private By selector;
    public By getSelector() { return this.selector; }



    public ElementValueParser(By selector, String name) {
        super(name);

        this.selector = selector;
    }

    public ElementValueParser(By selector) {
        this(selector, "");
    }



    @Override
    protected K internalParse(WebDriver driver) throws ValueParserException {
        List<WebElement> elements = driver.findElements(this.selector);

        if(elements.isEmpty())
            throw new ValueParserException("Could not find element with selector:" + this.selector + " to parse its value!");

        if(elements.size() > 1)
            throw new ValueParserException("More than one element matched selector:" + this.selector + " to parse value!");

        WebElement element = elements.get(0);

        String elementValue = element.getText();

        if(elementValue == null || elementValue.isEmpty())
            throw new ValueParserException("Element with selector:" + this.selector + " doesn't have value!");

        return this.parseElement(elementValue);
    }

    protected abstract K parseElement(String value) throws ValueParserException;

}
