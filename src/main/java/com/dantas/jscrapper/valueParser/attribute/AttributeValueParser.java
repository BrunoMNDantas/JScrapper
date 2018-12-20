package com.dantas.jscrapper.valueParser.attribute;

import com.dantas.jscrapper.valueParser.ValueParser;
import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class AttributeValueParser<K> extends ValueParser<K> {

    private String attribute;
    public String getAttribute() { return this.attribute; }

    private By selector;
    public By getSelector() { return this.selector; }



    public AttributeValueParser(By selector, String attribute, String name) {
        super(name);

        this.selector = selector;
        this.attribute = attribute;
    }

    public AttributeValueParser(By selector, String attribute) {
        this(selector, attribute, "");
    }



    @Override
    protected K internalParse(WebDriver driver) throws ValueParserException {
        List<WebElement> elements = driver.findElements(this.selector);

        if(elements.isEmpty())
            throw new ValueParserException("Could not find element with selector:" + this.selector + " to parse attribute:" + this.attribute + "!");

        if(elements.size() > 1)
            throw new ValueParserException("More than one element matched selector:" + this.selector + " to parse attribute:" + this.attribute + "!");

        WebElement element = elements.get(0);

        String attributeValue = element.getAttribute(this.attribute);

        if(attributeValue == null || attributeValue.isEmpty())
            throw new ValueParserException("Element with selector:" + this.selector + " doesn't have attribute:" + this.attribute + "!");

        return this.parseAttribute(attributeValue);
    }

    protected abstract K parseAttribute(String value) throws ValueParserException;

}
