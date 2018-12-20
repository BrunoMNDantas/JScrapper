package com.dantas.jscrapper.valueParser.attributes;

import com.dantas.jscrapper.valueParser.ValueParser;
import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class AttributesValueParser<K> extends ValueParser<Collection<K>> {

    private String attribute;
    public String getAttribute() { return this.attribute; }

    private By selector;
    public By getSelector() { return this.selector; }



    public AttributesValueParser(By selector, String attribute, String name) {
        super(name);

        this.selector = selector;
        this.attribute = attribute;
    }

    public AttributesValueParser(By selector, String attribute) {
        this(selector, attribute, "");
    }



    @Override
    protected Collection<K> internalParse(WebDriver driver) throws ValueParserException {
        List<WebElement> elements = driver.findElements(this.selector);

        Collection<K> values = new LinkedList<>();
        String attributeValue;
        for(WebElement element : elements) {
            attributeValue = element.getAttribute(this.attribute);

            if(attributeValue == null || attributeValue.isEmpty())
                throw new ValueParserException("One of the elements with selector:" + this.selector + " doesn't have attribute:" + this.attribute + "!");

            values.add(this.parseAttribute(attributeValue));
        }

        return values;
    }

    protected abstract K parseAttribute(String value) throws ValueParserException;

}
